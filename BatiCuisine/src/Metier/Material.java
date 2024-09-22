package Metier;

public class Material extends Composants {
	public enum UniteDeMesure {
		METRE, METRE_CARRE, KILOGRAMME, LITRE, PIECE
	}

	private Double coutUnitaire;
	private Double quantite;
	private Double coutTransport;
	private Double coefficientQualite;
	private UniteDeMesure unite;

	public Material(String nom, String typeComposant, Double TauxTVA, Double coutUnitaire, Double quantite, Double coutTransport, Double coefficientQualite, UniteDeMesure unite) {
		super(nom, typeComposant, TauxTVA);
		this.coutUnitaire = coutUnitaire;
		this.quantite = quantite;
		this.coutTransport = coutTransport;
		this.coefficientQualite = coefficientQualite;
		this.unite = unite;
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

	public UniteDeMesure getUnite() {
		return unite;
	}

	public void setUnite(UniteDeMesure unite) {
		this.unite = unite;
	}
	
}
