package Service;

import Metier.Composants;
import Metier.MainOeuvre;
import Metier.Material;
import Repository.ComposantsRepository;

import java.util.HashMap;
import java.util.List;

/**
 * Service class for managing Composants (Components) in the BatiCuisine application.
 * This class provides methods for CRUD operations on Composants and calculations related to materials and labor.
 */
public class ComposantsService {
    private ComposantsRepository composantsRepository = new ComposantsRepository();

    /**
     * Adds a new Composant to the repository.
     *
     * @param composant The Composant object to be added.
     */
    public void addComposant(Composants composant) {
        composantsRepository.addComposant(composant);
    }
    // this methode parameter is hashmap of material
    /**
     * Calculates the total cost of materials.
     *
     * @param materials A HashMap containing Material objects, with their names as keys.
     * @return The total cost of all materials, including taxes.
     */
    public double materialCalcul(HashMap<String, Material> materials){
        double materialTotalCout = 0;
        for (Material material : materials.values()) {
            double coutTotalHT = material.getQuantite() * material.getCoutUnitaire() * material.getCoefficientQualite() + material.getCoutTransport();
            double coutTotalTTC = coutTotalHT * (1 + material.getTauxTVA()/100);
            System.out.println("cout total HT "+material.getNom() + " : " + coutTotalHT + " € (quantité : " + material.getQuantite() + " " + material.getUnite() + ", coût unitaire : " + material.getCoutUnitaire() + " €/" + material.getUnite() + ", qualité : " + material.getCoefficientQualite() + ", transport : " + material.getCoutTransport() + " €)");
            System.out.println("cout total TTC "+material.getNom() + " : " + coutTotalTTC + " € (taux TVA : " + material.getTauxTVA() + "%)");
            materialTotalCout += coutTotalTTC;
        }
        return materialTotalCout;
    }
    /**
     * Calculates the total cost of labor.
     *
     * @param mainOeuvres A HashMap containing MainOeuvre objects, with their names as keys.
     * @return The total cost of all labor, including taxes.
     */
    public double mainOeuvreCalcul(HashMap<String, MainOeuvre> mainOeuvres) {
        double mainOeuvretotalCoutTTC = 0;
        for (MainOeuvre mainOeuvre : mainOeuvres.values()) {
            double coutHT = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail() * mainOeuvre.getProductiviteOuvrier();
            System.out.printf("%s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.1f h, productivité : %.1f)%n",
                    mainOeuvre.getNom(), coutHT, mainOeuvre.getTauxHoraire(), mainOeuvre.getHeuresTravail(), mainOeuvre.getProductiviteOuvrier());
            double coutTTC = coutHT * (1 + mainOeuvre.getTauxTVA()/100);
            System.out.printf("%s : %.2f € (taux TVA : %.2f%%)%n", mainOeuvre.getNom(), coutTTC, mainOeuvre.getTauxTVA());
            mainOeuvretotalCoutTTC += coutTTC;
        }
        return mainOeuvretotalCoutTTC;
    }

    /**
     * Retrieves a Composant by its ID.
     *
     * @param id The ID of the Composant to retrieve.
     * @return The Composant object with the specified ID.
     */
    public Composants getComposantById(int id) {
        return composantsRepository.getComposantById(id);
    }

    /**
     * Retrieves all Composants from the repository.
     *
     * @return A List of all Composants.
     */
    public List<Composants> getAllComposants() {
        return composantsRepository.getAllComposants();
    }

    /**
     * Updates an existing Composant in the repository.
     *
     * @param composant The Composant object with updated information.
     */
    public void updateComposant(Composants composant) {
        composantsRepository.updateComposant(composant);
    }

    /**
     * Deletes a Composant from the repository by its ID.
     *
     * @param id The ID of the Composant to be deleted.
     */
    public void deleteComposant(int id) {
        composantsRepository.deleteComposant(id);
    }
}
