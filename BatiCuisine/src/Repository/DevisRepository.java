package Repository;


import Metier.Devis;
import Metier.Project;
import Service.ProjectService;
import Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DevisRepository {
        private DatabaseConnection dbConnection;
    private static final Logger logger = LoggerFactory.getLogger(DevisRepository.class);

    public DevisRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }
    // Method to add a new devis
    public Devis addDevis(Devis devis) {
        String sql = "INSERT INTO devis (montant_estime, date_emission, date_validite, accepte, project_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setDouble(1, devis.getMontantEstime());
            statement.setDate(2, new java.sql.Date(devis.getDateEmission().getTime()));
            statement.setDate(3, new java.sql.Date(devis.getDateValidite().getTime()));
            statement.setBoolean(4, devis.isAccepte()); 
            statement.setInt(5, devis.getProjetAssocie().getId());
        
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        devis.setId(generatedKeys.getInt(1));
                    }
                }
                return devis;
            }
        } catch (SQLException e) {
            logger.error("Error adding devis to database: " + e.getMessage());
            throw new RuntimeException("Error adding devis to database", e);
        }
        return null; 
    }

    // Method to retrieve a devis by ID
    public Devis getDevisByProjectId(int id) {
        String sql = "SELECT * FROM devis WHERE project_id = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Devis devis = new Devis();
                    devis.setId(resultSet.getInt("id"));
                    devis.setMontantEstime(resultSet.getDouble("montant_estime"));
                    devis.setDateEmission(resultSet.getDate("date_emission"));
                    devis.setDateValidite(resultSet.getDate("date_validite"));
                    devis.setAccepte(resultSet.getBoolean("accepte"));
                    int projectId = resultSet.getInt("project_id");
                    ProjectService projectService = new ProjectService();
                    Project project = projectService.getProjectById(projectId);
                    if (project != null) {
                        devis.setProjetAssocie(project);
                    } else {
                        logger.warn("Project with ID " + projectId + " not found for devis with ID " + devis.getId());
                    }
                    return devis;
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving devis from database: " + e.getMessage());
            throw new RuntimeException("Error retrieving devis from database", e);
        }
        return null;
    }

        
    public void updateDevis(Devis devis) {
        String sql = "UPDATE devis SET  accepte = ? WHERE id = ?";
        
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, devis.isAccepte());
            statement.setInt(2, devis.getId());
            
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                logger.warn("Updating devis failed, no rows affected.");
            }
        } catch (SQLException e) {
            logger.error("Error updating devis in database", e);
        }
    }
    }

   

   
