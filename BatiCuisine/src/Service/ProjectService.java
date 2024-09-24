package Service;

import Metier.Project;
import Repository.ProjectRepository;
import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();

    /**
     * Adds a new project to the repository.
     *
     * @param project The project to be added
     * @return The added project
     */
    public Project addProject(Project project) {
       Project pro = projectRepository.addProject(project);
        return pro;
    }

    /**
     * Calculates the total cost of a project including labor, materials, and profit margin.
     *
     * @param coutMainOeuvre The labor cost
     * @param coutMaterial The material cost
     * @param margeBeneficiaire The profit margin percentage
     * @return The total cost of the project
     */
    public Double calculCoutTotal(double coutMainOeuvre, double coutMaterial, double margeBeneficiaire) {
        double coutTotal = coutMainOeuvre + coutMaterial;
        System.out.println("le cout total avant marge est de : "+coutTotal);
        double marge = coutTotal * margeBeneficiaire / 100;
        System.out.println("la marge bénéficiaire est de : "+marge);
        double coutTotalFinal = coutTotal + marge;
        System.out.println("le cout total final du projet est de : "+coutTotalFinal);
        return coutTotalFinal;
    }
    
    /**
     * Displays the details of a project.
     *
     * @param project The project to display
     */
    public void afficherProject(Project project) {
        System.out.println("id du projet:" + project.getId());
    	System.out.println( "nom de projet:" + project.getNomProjet());
    	System.out.println( "client:" + project.getClient().getNom());
    	System.out.println( "surface de projet:" + project.getSurface());
    	System.out.println( "marge bénéficiaire:" + project.getMargeBeneficiaire());
    	System.out.println( "cout total:" + project.getCoutTotal());
        System.out.println( "etat du projet:" + project.getEtatProjet());
        System.out.println("======================================");
    }

    /**
     * Retrieves all projects from the repository.
     *
     * @return A list of all projects
     */
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    /**
     * Retrieves projects by name from the repository.
     *
     * @param name The name to search for
     * @return A list of projects matching the given name
     */
    public List<Project> getProjectsByName(String name) {
        return projectRepository.getProjectsByName(name);
    }

    /**
     * Retrieves a project by its ID from the repository.
     *
     * @param id The ID of the project to retrieve
     * @return The project with the given ID
     */
    public Project getProjectById(int id) {
        return projectRepository.getProjectById(id);
    }

    /**
     * Updates an existing project in the repository.
     *
     * @param project The project to update
     */
    public void updateProject(Project project) {
        projectRepository.updateProject(project);
    }
}
