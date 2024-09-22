package Service;

import Metier.Devis;
import Metier.Project;
import Repository.DevisRepository;
import Util.DateUtils;

public class DevisService {
    private final DevisRepository devisRepository;

    public DevisService() {
        this.devisRepository = new DevisRepository();
    }

    public Devis creerDevis(Project projet, String dateEmission, String dateValidite) {
        Devis devis = new Devis();
        devis.setMontantEstime(projet.getCoutTotal());
        devis.setDateEmission(DateUtils.stringToDate(dateEmission));
        devis.setDateValidite(DateUtils.stringToDate(dateValidite));
        devis.setAccepte(false);
        devis.setProjetAssocie(projet);
        return devisRepository.save(devis);
    }
}