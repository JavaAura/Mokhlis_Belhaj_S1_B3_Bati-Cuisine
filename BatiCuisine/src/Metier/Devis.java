package Metier;

import java.util.Date;

public class Devis  {

	private Double montantEstime;
	private Date dateEmission;
	private Date dateValidite;
	private boolean accepte;
	public Double getMontantEstime() {
		return montantEstime;
	}
	public void setMontantEstime(Double montantEstime) {
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
	
	
	
	
	
}
