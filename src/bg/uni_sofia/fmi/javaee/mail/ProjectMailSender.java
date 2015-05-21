package bg.uni_sofia.fmi.javaee.mail;

import bg.uni_sofia.fmi.javaee.model.Project;

public class ProjectMailSender extends MailSender<Project> {

	@Override
	protected String getMailTextForPersist(Project t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getMailTextForUpdate(Project t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void appendDetails(Project t, StringBuilder sb) {
		// TODO Auto-generated method stub
		
	}

}
