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

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }
    public List<Project>  getProjectsByName(String name){
        return projectRepository.getProjectsByName(name);
    }
    public Project getProjectById(int id){
        return projectRepository.getProjectById(id);
    }
    public void updateProject(Project project){
        projectRepository.updateProject(project);
    }
    

  
}
