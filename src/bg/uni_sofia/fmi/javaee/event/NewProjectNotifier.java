package bg.uni_sofia.fmi.javaee.event;

import javax.enterprise.event.Observes;

import bg.uni_sofia.fmi.javaee.model.Project;

public class NewProjectNotifier extends Notifier {
	
	public void sendMailToCreator(@Observes NewProjectEvent event){
		Project project = event.get();
		String textForPersist = this.getMailTextForPersist(project);
		notify(project.getCreator().getEmail(), "New Project", textForPersist);
	}
	
	private String getMailTextForPersist(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + project.getCreator().getFullName() + 
				" you have created new project " + project.getName() +  "\n");
		return sb.toString();
	}

}
