package Metier;

public class Material extends Composants {
	private Double coutUnitaire;
	private Double quantite;
	private Double coutTransport;
	private Double coefficientQualite;

   public Material(String nom, String typeComposant, Double TauxTVA, Double coutUnitaire, Double quantite, Double coutTransport, Double coefficientQualite) {
    super(nom, typeComposant, TauxTVA);
    this.coutUnitaire = coutUnitaire;
    this.quantite = quantite;
    this.coutTransport = coutTransport;
    this.coefficientQualite = coefficientQualite;
   }

public Double getCoutUnitaire() {
	return coutUnitaire;
}

public void setCoutUnitaire(Double coutUnitaire) {
	this.coutUnitaire = coutUnitaire;
}

public Double getQuantite() {
	return quantite;
}

public void setQuantite(Double quantite) {
	this.quantite = quantite;
}

public Double getCoutTransport() {
	return coutTransport;
}

public void setCoutTransport(Double coutTransport) {
	this.coutTransport = coutTransport;
}

public Double getCoefficientQualite() {
	return coefficientQualite;
}

public void setCoefficientQualite(Double coefficientQualite) {
	this.coefficientQualite = coefficientQualite;
}

	
	
	
	

}
