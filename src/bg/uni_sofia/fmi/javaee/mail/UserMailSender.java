/**
 * 
 */
package bg.uni_sofia.fmi.javaee.mail;

import bg.uni_sofia.fmi.javaee.model.User;

public class UserMailSender extends MailSender<User> {

	@Override
	protected String getMailTextForPersist(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getMailTextForUpdate(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void appendDetails(User t, StringBuilder sb) {
		// TODO Auto-generated method stub
		
	}

}
