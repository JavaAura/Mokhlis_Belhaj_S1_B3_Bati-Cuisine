package Metier;

public abstract class Composants {
	protected String nom;
	protected String typeComposant;
	protected Double TauxTVA;
    protected Project projectAssocie;
    public Composants(String nom, String typeComposant, Double TauxTVA ,Project projectAssocie) {
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.TauxTVA = TauxTVA;
        this.projectAssocie = projectAssocie;
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
