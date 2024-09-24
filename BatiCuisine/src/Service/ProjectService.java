package Service;

import Metier.Project;
import Repository.ProjectRepository;
import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();

    public Project addProject(Project project) {
       Project pro = projectRepository.addProject(project);
        return pro;
    }
    public Double calculCoutTotal(double coutMainOeuvre,double coutMaterial,double margeBeneficiaire){
        double coutTotal = coutMainOeuvre + coutMaterial;
        System.out.println("le cout total avant marge est de : "+coutTotal);
        double marge = coutTotal * margeBeneficiaire / 100;
        System.out.println("la marge bénéficiaire est de : "+marge);
        double coutTotalFinal = coutTotal + marge;
        System.out.println("le cout total final du projet est de : "+coutTotalFinal);
        return coutTotalFinal;
    }
   

    public Project getProjectById(int id) {
        return projectRepository.getProjectById(id);
    }
    public List<Project>  getProjectsByName(String name){
        return projectRepository.getProjectsByName(name);
    }
    public void afficherProject(Project project) {
    	System.out.println("hello ");
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
