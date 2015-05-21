package bg.uni_sofia.fmi.javaee.mail;


import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class MailSender<T> {

	private static final String USERNAME = "n.stavrev28@gmail.com";
	
	@Resource(name = "mail")
	private Session session;
	
	public void sendMailForPersist(String to, String subject, T t) throws AddressException, MessagingException {
		String text = this.getMailTextForPersist(t);
		send(to, subject, text);
	}
	
	public void sendMailForUpdate(String to, String subject, T t) throws AddressException, MessagingException {
		String text = this.getMailTextForUpdate(t);
		send(to, subject, text);
	}
	
	private void send(String to, String subject, String text)
			throws MessagingException, AddressException {
		Message message = createMessage(to, subject, text); 
		System.out.println("hopa abss");
		Transport.send(message);
	}

	private Message createMessage(String to, String subject, String text)
			throws MessagingException, AddressException {
		Message message = new MimeMessage(session);
    	message.setFrom(new InternetAddress(USERNAME));
    	message.setRecipients(Message.RecipientType.TO,
    			InternetAddress.parse(to)); 
		message.setSubject(subject);
		message.setText(text);
		return message;
	}
	
	protected abstract String getMailTextForPersist(T t);
	
	protected abstract String getMailTextForUpdate(T t);
	
	protected abstract void appendDetails(T t, StringBuilder sb);
}
