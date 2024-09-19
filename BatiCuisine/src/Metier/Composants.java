package Metier;

public abstract class Composants {
	protected String nom;
	protected String typeComposant;
	protected Double TauxTVA;
    public Composants(String nom, String typeComposant, Double TauxTVA) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.TauxTVA = TauxTVA;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getTypeComposant() {
        return typeComposant;
    }
    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
    }
    public Double getTauxTVA() {
        return TauxTVA;
    }
    public void setTauxTVA(Double tauxTVA) {
        TauxTVA = tauxTVA;
    }
}
