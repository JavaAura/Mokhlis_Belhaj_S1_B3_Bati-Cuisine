package Metier;

import java.util.Date;
public class Devis {
    private int id;
    private double montantEstime;
    private Date dateEmission;
    private Date dateValidite;
    private boolean accepte;
    private Project projetAssocie;

    // Constructors, getters, and setters
    public Devis() {}

    public Devis(double montantEstime, Date dateEmission, Date dateValidite, boolean accepte,Project projetAssocie) {
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projetAssocie = projetAssocie;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMontantEstime() {
		return montantEstime;
	}

	public void setMontantEstime(double montantEstime) {
		this.montantEstime = montantEstime;
	}

	public Date getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Date dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Date getDateValidite() {
		return dateValidite;
	}

	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
	}

	public boolean isAccepte() {
		return accepte;
	}

	public void setAccepte(boolean accepte) {
		this.accepte = accepte;
	}




	public void setProjetAssocie(Project projet) {
		this.projetAssocie = projet;
	}

	public Project getProjetAssocie() {
		return this.projetAssocie;
	}
    
}
