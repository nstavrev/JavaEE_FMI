package bg.uni_sofia.fmi.javaee.event;


import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Notifier {

	private static final String SENDER_ADDRESS = "n.stavrev28@gmail.com";
	
	private final static Logger LOGGER = Logger.getLogger(Notifier.class.getName()); 
	
	@Resource(name = "mail")
	private Session session;
	
	protected void notify(String to, String subject, String text) {
		new Thread(() -> {
			try {
				Message message = createMessage(to, subject, text); 
				Transport.send(message);
				LOGGER.info("Email with text " + text + " was sent to " + to);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private Message createMessage(String to, String subject, String text)
			throws MessagingException {
		Message message = new MimeMessage(session);
    	message.setFrom(new InternetAddress(SENDER_ADDRESS));
    	message.setRecipients(Message.RecipientType.TO,
    			InternetAddress.parse(to)); 
		message.setSubject(subject);
		message.setText(text);
		return message;
	}
	
}
