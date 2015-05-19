package bg.uni_sofia.fmi.javaee.mail;


import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Singleton
public class MailSender {

	private static final String USERNAME = "n.stavrev28@gmail.com";
	
	@Resource(name = "mail")
	private Session session;
	
	public void sendMail(String to, String subject, String text) throws MessagingException {
		Message message = new MimeMessage(session);
    	message.setFrom(new InternetAddress(USERNAME));
    	message.setRecipients(Message.RecipientType.TO,
    			InternetAddress.parse(to)); 
		message.setSubject(subject);
		message.setText(text); 
		Transport.send(message); 
		System.out.println("E-mail sent to address " + to);
		
	} 
}
