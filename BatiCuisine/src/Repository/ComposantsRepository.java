package Repository;

import Metier.Composants;
import Metier.MainOeuvre;
import Metier.Material;
import Util.DatabaseConnection;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Repository class for managing Composants (Components) in the database.
 * This class handles CRUD operations for Composants, including Materials and MainOeuvre.
 */
public class ComposantsRepository {

    private DatabaseConnection dbConnection;
    private static final Logger logger = LoggerFactory.getLogger(ComposantsRepository.class);

    /**
     * Constructor for ComposantsRepository.
     * Initializes the database connection.
     */
    public ComposantsRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    /**
     * Adds a new component to the database.
     * This method handles both Material and MainOeuvre components.
     *
     * @param composant The Composants object to be added to the database.
     */
    public void addComposant(Composants composant) {
        String sql = "INSERT INTO composants (nom, type_composant, taux_tva, project_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            Composants comp = (Composants) composant;
            statement.setString(1, comp.getNom());
            statement.setString(2, comp.getTypeComposant());
            statement.setDouble(3, comp.getTauxTVA());
            statement.setInt(4, comp.getProjectAssocie().getId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int composantId = generatedKeys.getInt(1);

                    if (comp.getTypeComposant().equals("Material")) {
                        Material material = (Material) comp;
                        String sqlMaterial = "INSERT INTO material (id, cout_unitaire, quantite, cout_transport, coefficient_qualite) VALUES (?, ?, ?, ?, ?)";
                        try (
                            PreparedStatement pstmtMaterial = connection.prepareStatement(sqlMaterial)) {
                            pstmtMaterial.setInt(1, composantId);
                            pstmtMaterial.setDouble(2, material.getCoutUnitaire());
                            pstmtMaterial.setDouble(3, material.getQuantite());
                            pstmtMaterial.setDouble(4, material.getCoutTransport());
                            pstmtMaterial.setDouble(5, material.getCoefficientQualite());
                            pstmtMaterial.executeUpdate();
                        }
                    } else if (comp.getTypeComposant().equals("MainOeuvre")) {
                        MainOeuvre mainOeuvre = (MainOeuvre) comp;
                        String sqlMainOeuvre = "INSERT INTO mainoeuvre (id, taux_horaire, heures_travail, productivite_ouvrier) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement pstmtMainOeuvre = connection.prepareStatement(sqlMainOeuvre)) {
                            pstmtMainOeuvre.setInt(1, composantId);
                            pstmtMainOeuvre.setDouble(2, mainOeuvre.getTauxHoraire());
                            pstmtMainOeuvre.setDouble(3, mainOeuvre.getHeuresTravail());
                            pstmtMainOeuvre.setDouble(4, mainOeuvre.getProductiviteOuvrier());
                            pstmtMainOeuvre.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error adding component", e);
        }
    }

    /**
     * Retrieves a component by its ID from the database.
     *
     * @param id The ID of the component to retrieve.
     * @return The Composants object if found, or null if not found.
     */
    public Composants getComposantById(int id) {
        // TODO: Implement method to get composant from database
        return null; // Replace with actual composant
    }

    /**
     * Retrieves all components from the database.
     *
     * @return A List of all Composants objects in the database.
     */
    public List<Composants> getAllComposants() {
        // TODO: Implement method to retrieve all composants
        return null; // Replace with actual list of composants
    }

    /**
     * Updates an existing component in the database.
     *
     * @param composant The Composants object with updated information.
     */
    public void updateComposant(Composants composant) {
        // TODO: Implement method to update composant in database
    }

    /**
     * Deletes a component from the database by its ID.
     *
     * @param id The ID of the component to delete.
     */
    public void deleteComposant(int id) {
        // TODO: Implement method to delete composant from database
    }
}
