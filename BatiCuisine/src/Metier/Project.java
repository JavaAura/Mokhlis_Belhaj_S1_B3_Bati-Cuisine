package Metier;

import Util.enums.EtatProjet;

public class Project {
    private int id;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private EtatProjet etatProjet;
    private int clientId;

    // Constructors, getters, and setters
    public Project() {}

    public Project(String nomProjet, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet, int clientId) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.clientId = clientId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomProjet() {
		return nomProjet;
	}

	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

	public double getMargeBeneficiaire() {
		return margeBeneficiaire;
	}

	public void setMargeBeneficiaire(double margeBeneficiaire) {
		this.margeBeneficiaire = margeBeneficiaire;
	}

	public double getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(double coutTotal) {
		this.coutTotal = coutTotal;
	}

	public EtatProjet getEtatProjet() {
		return etatProjet;
	}

	public void setEtatProjet(EtatProjet etatProjet) {
		this.etatProjet = etatProjet;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

    
}
