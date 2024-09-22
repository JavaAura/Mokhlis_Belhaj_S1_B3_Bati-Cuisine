package Service;

import Metier.Composants;
import Repository.ComposantsRepository;
import java.util.List;

public class ComposantsService {
    private ComposantsRepository composantsRepository = new ComposantsRepository();

    public void addComposant(Composants composant) {
        composantsRepository.addComposant(composant);
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
