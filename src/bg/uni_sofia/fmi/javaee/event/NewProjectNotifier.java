package bg.uni_sofia.fmi.javaee.event;

import javax.enterprise.event.Observes;

import bg.uni_sofia.fmi.javaee.model.Project;
import bg.uni_sofia.fmi.javaee.model.User;

public class NewProjectNotifier extends Notifier {
	
	public void sendMailToMembers(@Observes NewProjectEvent event){
		Project project = event.get();
		for (User member : project.getMembers()) {
			this.sendMailToMember(member, project);
		}
	}
	
	public void sendMailCreator(@Observes NewProjectEvent event){
		Project project = event.get();
		String textForPersist = this.getMailTextForPersist(project);
		notify(project.getCreator().getEmail(), "New Project", textForPersist);
	}
	
	private void sendMailToMember(User member, Project project)  {
		String mailTextForMember = this.getMailTextForMember(member, project);
		notify(member.getEmail(), "Project Member", mailTextForMember);
	}
	
	private String getMailTextForMember(User member, Project project){
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + member.getFullName() + "\n");
		sb.append("Your were involved as a project member of project " + project.getName());
		return sb.toString();
	}
	
	private String getMailTextForPersist(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + project.getCreator().getFullName() + 
				" you have created new project " + project.getName() +  "\n");
		return sb.toString();
	}

}
