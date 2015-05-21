package bg.uni_sofia.fmi.javaee.mail;

import javax.ejb.Singleton;

import bg.uni_sofia.fmi.javaee.model.Issue;

@Singleton
public class IssueMailSender extends MailSender<Issue> {

	@Override
	protected String getMailTextForPersist(Issue t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getMailTextForUpdate(Issue t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void appendDetails(Issue t, StringBuilder sb) {
		// TODO Auto-generated method stub
		
	}

}
