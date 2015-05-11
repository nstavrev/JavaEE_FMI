package bg.uni_sofia.fmi.javaee.mail;


import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Singleton
public class MailSender {

	private static final String USERNAME = "n.stavrev28@gmail.com";
	
	@Resource(name = "mail")
	private Session session;
	
	public void sendMail(String to) throws MessagingException {
		Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to)); 
        message.setSubject("Testing Subject");
        message.setText("Dear Mail Crawler,"
                + "\n\n No spam to my email, please!");
//        Transport.send(message); 
	} 
}
