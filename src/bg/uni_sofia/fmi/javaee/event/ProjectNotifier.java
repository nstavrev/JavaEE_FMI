package bg.uni_sofia.fmi.javaee.event;

import javax.enterprise.event.Observes;

import bg.uni_sofia.fmi.javaee.model.Project;
import bg.uni_sofia.fmi.javaee.model.User;

public class ProjectNotifier extends Notifier {
	
	public void sendMailToCreator(@Observes ProjectEvent event){
		Project project = event.get();
		String subject = "Project Change";
		String text = "Dear " + project.getCreator().getFullName() + " your project was changed";
		notify(project.getCreator().getEmail(), subject, text);
	}
	
	public void sendMailToMembers(@Observes ProjectEvent event){
		Project project = event.get();
		if(project.getMembers() != null){
			for (User member : project.getMembers()) {
				this.sendMailToMember(member, project);
			}
		}
	}

	private void sendMailToMember(User member, Project project)  {
		String subject = "Project Change";
		String mailTextForMember = this.getMailTextForMember(member, project);
		notify(member.getEmail(), subject, mailTextForMember);
	}

	private String getMailTextForMember(User member, Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + member.getFullName() + " project " + project.getName() + " was edited");
		return sb.toString();
	}
	
	
}
