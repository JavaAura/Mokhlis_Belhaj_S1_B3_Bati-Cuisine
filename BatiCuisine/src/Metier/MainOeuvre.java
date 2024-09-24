package Metier;

import Util.enums.TypeMainOeuvre;

public class MainOeuvre extends Composants {

	
	protected Double tauxHoraire;
    protected Double heuresTravail;
    protected Double productiviteOuvrier;
    protected TypeMainOeuvre typeMainOeuvre;
  

    public MainOeuvre(String nom, Double TauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier, TypeMainOeuvre typeMainOeuvre, Project projectAssocie) {
        super(nom, "MainOeuvre", TauxTVA, projectAssocie);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.typeMainOeuvre = typeMainOeuvre;
    }
    public MainOeuvre(String nom, Double TauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier, TypeMainOeuvre typeMainOeuvre) {
		super(nom, "MainOeuvre", TauxTVA, null);
		this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.typeMainOeuvre = typeMainOeuvre;
    }
	

    
    public Double getTauxHoraire() {
		return tauxHoraire;
	}

	public void setTauxHoraire(Double tauxHoraire) {
		this.tauxHoraire = tauxHoraire;
	}

	public Double getHeuresTravail() {
		return heuresTravail;
	}

	public void setHeuresTravail(Double heuresTravail) {
		this.heuresTravail = heuresTravail;
	}

	public Double getProductiviteOuvrier() {
		return productiviteOuvrier;
	}

	public void setProductiviteOuvrier(Double productiviteOuvrier) {
		this.productiviteOuvrier = productiviteOuvrier;
	}

	public TypeMainOeuvre getTypeMainOeuvre() {
		return typeMainOeuvre;
	}

	public void setTypeMainOeuvre(TypeMainOeuvre typeMainOeuvre) {
		this.typeMainOeuvre = typeMainOeuvre;
	}

	
    

}
