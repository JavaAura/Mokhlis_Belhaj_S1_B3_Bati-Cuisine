package Service;

import Metier.Composants;
import Metier.MainOeuvre;
import Metier.Material;
import Repository.ComposantsRepository;

import java.util.HashMap;
import java.util.List;

public class ComposantsService {
    private ComposantsRepository composantsRepository = new ComposantsRepository();

    public void addComposant(Composants composant) {
        composantsRepository.addComposant(composant);
    }
    // this methode parameter is hashmap of material
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

    public Composants getComposantById(int id) {
        return composantsRepository.getComposantById(id);
    }

    public List<Composants> getAllComposants() {
        return composantsRepository.getAllComposants();
    }

    public void updateComposant(Composants composant) {
        composantsRepository.updateComposant(composant);
    }

    public void deleteComposant(int id) {
        composantsRepository.deleteComposant(id);
    }
}
