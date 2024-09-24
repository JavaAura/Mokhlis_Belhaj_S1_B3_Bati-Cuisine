package Service;

import Metier.Devis;
import Repository.DevisRepository;

/**
 * Service class for managing Devis (quote) operations.
 */
public class DevisService {
    private final DevisRepository devisRepository;

    /**
     * Constructs a new DevisService with a DevisRepository.
     */
    public DevisService() {
        this.devisRepository = new DevisRepository();
    }

    /**
     * Adds a new Devis to the repository.
     *
     * @param devis The Devis object to be added.
     * @return The added Devis object.
     */
    public Devis addDevis(Devis devis) {
        return devisRepository.addDevis(devis);
    }

    /**
     * Displays the details of a Devis.
     *
     * @param devis The Devis object to be displayed.
     * @return The displayed Devis object.
     */
    public Devis afficherDevis(Devis devis){
        System.out.println(devis.getMontantEstime());
        System.out.println(devis.getDateEmission());
        System.out.println(devis.getDateValidite());
        if (devis.isAccepte()){
            System.out.println("le devis est accepté");
        }else{
            System.out.println("le devis n'est pas accepté");
        }
        
        return devis;
    }

    /**
     * Retrieves a Devis by its associated project ID.
     *
     * @param id The ID of the project.
     * @return The Devis object associated with the given project ID.
     */
    public Devis getDevisByProjectid(int id){
        return devisRepository.getDevisByProjectId(id);
    }

    /**
     * Updates an existing Devis in the repository.
     *
     * @param devis The Devis object to be updated.
     */
    public void updateDevis(Devis devis){
        devisRepository.updateDevis(devis);
    }
}