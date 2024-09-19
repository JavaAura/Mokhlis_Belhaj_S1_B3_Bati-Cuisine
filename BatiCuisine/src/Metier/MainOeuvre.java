package Metier;

public class MainOeuvre extends Composants {

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

	protected Double tauxHoraire;
    protected Double heuresTravail;
    protected Double productiviteOuvrier;

    public MainOeuvre(String nom, String typeComposant, Double TauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier) {
        super(nom, typeComposant, TauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    

}
