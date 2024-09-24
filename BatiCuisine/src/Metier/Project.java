package Metier;

import Util.enums.EtatProjet;

public class Project {
    private int id;
    private String nomProjet;
    private int surface;
    private double margeBeneficiaire;
    private double coutTotal;
    private EtatProjet etatProjet;
    private Client client;

    // Constructors, getters, and setters
    public Project() {}

    public Project(String nomProjet, int surface, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet, Client client) {
        this.nomProjet = nomProjet;
        this.surface = surface;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}
}
