package bg.uni_sofia.fmi.javaee.event;

import javax.enterprise.event.Observes;

import bg.uni_sofia.fmi.javaee.model.User;

public class NewUserNotifier extends Notifier {
	
	public void sendMailToUser(@Observes NewUserEvent event) {
		User user = event.get();
		String text = this.getMailText(user);
		notify(user.getEmail(), "New Account", text);
	}
	
	private String getMailText(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("Details about your account \n");
		sb.append("User name : " + user.getUserName() + "\n");
		sb.append("Full Name : " + user.getFullName() + "\n");
		sb.append("E-mail : " + user.getEmail() + "\n");
		return sb.toString();
	}
	
}
