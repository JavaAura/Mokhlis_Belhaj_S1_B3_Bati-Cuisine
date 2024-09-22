package Service;

import Metier.Project;
import Repository.ProjectRepository;
import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();

    public void addProject(Project project) {
        projectRepository.addProject(project);
    }

    public Project getProjectById(int id) {
        return projectRepository.getProjectById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }

    public void deleteProject(int id) {
        projectRepository.deleteProject(id);
    }
}
