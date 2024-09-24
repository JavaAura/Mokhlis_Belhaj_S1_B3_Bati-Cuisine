package Service;

import Metier.Devis;
import Repository.DevisRepository;

public class DevisService {
    private final DevisRepository devisRepository;

    public DevisService() {
        this.devisRepository = new DevisRepository();
    }

    public Devis addDevis(Devis devis) {
        return devisRepository.addDevis(devis);
    }
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
    public Devis getDevisByProjectid(int id){
        return devisRepository.getDevisByProjectId(id);
    }
    public void updateDevis(Devis devis){
        devisRepository.updateDevis(devis);
    }
}