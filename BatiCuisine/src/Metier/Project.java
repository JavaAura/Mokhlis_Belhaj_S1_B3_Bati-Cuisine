package Metier;

import java.util.List;

public class Project {
	
	private String nom;
	private Double margeBeneficiaire;
	private Double coutTotal;
	private EtatProjet etatProjet;
    private Client clientAssocie;  
    private List<Composants> composants; 
    private Devis devis;
    
  
   
    public enum EtatProjet {
        EN_COURS, TERMINE, ANNULE 
    }


    //getter
    public String getNom() {
        return nom;
    }
    public Double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }
    public Double getCoutTotal() {
        return coutTotal;
    }
    public EtatProjet getEtatProjet() {
        return etatProjet;
    }
    public Client getClientAssocie() {
        return clientAssocie;
    }
    public List<Composants> getComposants() {
        return composants;
    }
    public Devis getDevis() {
        return devis;
    }






    // setter
    public void setNom(String nom) {
        this.nom = nom;
    }                       
    public void setMargeBeneficiaire(Double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }
    public void setCoutTotal(Double coutTotal) {
        this.coutTotal = coutTotal;
    }
    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }
    public void setClientAssocie(Client clientAssocie) {
        this.clientAssocie = clientAssocie;
    }
    public void setComposants(List<Composants> composants) {
        this.composants = composants;
    }
    public void setDevis(Devis devis) {
        this.devis = devis;
    }
    
    
    
    
	

}
