package Metier;

import java.util.Date;
import Metier.Project;
public class Devis {
    private int id;
    private double montantEstime;
    private Date dateEmission;
    private Date dateValidite;
    private boolean accepte;
    private int projectId;
    private Project projetAssocie;

    // Constructors, getters, and setters
    public Devis() {}

    public Devis(double montantEstime, Date dateEmission, Date dateValidite, boolean accepte, int projectId) {
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projectId = projectId;
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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setProjetAssocie(Project projet) {
		this.projetAssocie = projet;
	}

	public Project getProjetAssocie() {
		return this.projetAssocie;
	}
    
}
