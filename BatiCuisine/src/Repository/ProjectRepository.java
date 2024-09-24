package Repository;



import Metier.Project;
import java.util.List;

public class ProjectRepository {
    // Method to add a new project
    public Project addProject(Project project) {
        Project pro = new Project(); 
    	return pro ;
    }

    // Method to retrieve a project by ID
    public Project getProjectById(int id) {
        // Implementation to get project from database
        return null; // Replace with actual project
    }
    public List<Project> getProjectsByName(String name){
        // Implementation to get project from database
        return null; // Replace with actual project
    }

    // Method to get all projects
    public List<Project> getAllProjects() {
        // Implementation to retrieve all projects
        return null; // Replace with actual list of projects
    }

    // Method to update a project
    public void updateProject(Project project) {
        // Implementation to update project in database
    }

    // Method to delete a project
    public void deleteProject(int id) {
        // Implementation to delete project from database
    }
}

