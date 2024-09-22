package Repository;


import Metier.Devis;
import java.util.List;

public class DevisRepository {
    // Method to add a new devis
    public void addDevis(Devis devis) {
        // Implementation to add devis to database
    }

    // Method to retrieve a devis by ID
    public Devis getDevisById(int id) {
        // Implementation to get devis from database
        return null; // Replace with actual devis
    }

    // Method to get all devis
    public List<Devis> getAllDevis() {
        // Implementation to retrieve all devis
        return null; // Replace with actual list of devis
    }

    // Method to update a devis
    public void updateDevis(Devis devis) {
        // Implementation to update devis in database
    }

    // Method to delete a devis
    public void deleteDevis(int id) {
        // Implementation to delete devis from database
    }

    // Method to save (add or update) a devis
    public Devis save(Devis devis) {
        if (getDevisById(devis.getId()) == null) {
            // This is a new devis, so add it
            addDevis(devis);
        } else {
            // This is an existing devis, so update it
            updateDevis(devis);
        }
        // Return the saved devis (you might want to fetch it from the database to get any auto-generated fields)
        return getDevisById(devis.getId());
    }
}
