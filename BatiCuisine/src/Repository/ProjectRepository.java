package Repository;



import Metier.Client;
import Metier.Project;
import Util.DatabaseConnection;

import java.util.List;
import java.util.ArrayList;
import Service.ClientService;
import Util.enums.EtatProjet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Repository class for managing Project entities in the database.
 */
public class ProjectRepository {
    private DatabaseConnection dbConnection;
    private static final Logger logger = LoggerFactory.getLogger(ProjectRepository.class);

    /**
     * Constructor for ProjectRepository.
     * Initializes the database connection.
     */
    public ProjectRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    /**
     * Adds a new project to the database.
     *
     * @param project The Project object to be added.
     * @return The Project object with its ID set after insertion.
     */
    public Project addProject(Project project) {
        String sql = "INSERT INTO project (nom,surface, marge_beneficiaire, cout_total, etat_projet, client_id) VALUES (?, ?, ?, ?, ?,?)";
        
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, project.getNomProjet());
            statement.setInt(3, project.getSurface());
            statement.setDouble(2, project.getMargeBeneficiaire());
            statement.setDouble(4, project.getCoutTotal());
            statement.setString(5, project.getEtatProjet().toString());
            statement.setInt(6, project.getClient().getId());
            
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error adding project to database", e);
        }
        return project;
    }

    /**
     * Retrieves a project from the database by its ID.
     *
     * @param id The ID of the project to retrieve.
     * @return The Project object if found, null otherwise.
     */
    public Project getProjectById(int id) {
        Project project = null;
        String sql = "SELECT * FROM project WHERE id = ?";
        
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    project = new Project();
                    project.setId(resultSet.getInt("id"));
                    project.setNomProjet(resultSet.getString("nom"));
                    project.setSurface(resultSet.getInt("surface"));
                    project.setMargeBeneficiaire(resultSet.getDouble("marge_beneficiaire"));
                    project.setCoutTotal(resultSet.getDouble("cout_total"));
                    project.setEtatProjet(EtatProjet.valueOf(resultSet.getString("etat_projet")));
                    
                    int clientId = resultSet.getInt("client_id");
                    ClientService clientService = new ClientService();
                    Client client = clientService.getClientById(clientId);
                    if (client != null) {
                        project.setClient(client);
                    } else {
                        logger.warn("Client with ID " + clientId + " not found for project with ID " + id);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving project by ID from database", e);
        }
        return project;
    }
    
    /**
     * Retrieves projects from the database by name (partial match).
     *
     * @param name The name or part of the name to search for.
     * @return A List of Project objects matching the search criteria.
     */
    public List<Project> getProjectsByName(String name) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project WHERE nom LIKE ?";
        
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, "%" + name + "%"); // Use wildcard for LIKE clause
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project();
                    project.setId(resultSet.getInt("id"));
                    project.setNomProjet(resultSet.getString("nom"));
                    project.setSurface(resultSet.getInt("surface"));
                    project.setMargeBeneficiaire(resultSet.getDouble("marge_beneficiaire"));
                    project.setCoutTotal(resultSet.getDouble("cout_total"));
                    project.setEtatProjet(EtatProjet.valueOf(resultSet.getString("etat_projet")));
                    int clientId = resultSet.getInt("client_id");
                    ClientService clientService = new ClientService();
                    Client client = clientService.getClientById(clientId);
                    if (client != null) {
                        project.setClient(client);
                    } else {
                        logger.warn("Client with ID " + clientId + " not found for project with ID " + resultSet.getInt("id"));
                    }
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving projects by name from database", e);
        }
        return projects;
    }
            
        
    

    /**
     * Retrieves all projects from the database.
     *
     * @return A List of all Project objects in the database.
     */
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM project";
        
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setNomProjet(resultSet.getString("nom"));
                project.setSurface(resultSet.getInt("surface"));
                project.setMargeBeneficiaire(resultSet.getDouble("marge_beneficiaire"));
                project.setCoutTotal(resultSet.getDouble("cout_total"));
                
                String etatProjetStr = resultSet.getString("etat_projet");
                if (etatProjetStr != null) {
                    try {
                        project.setEtatProjet(EtatProjet.valueOf(etatProjetStr));
                    } catch (IllegalArgumentException e) {
                        logger.error("Invalid etat_projet value: " + etatProjetStr, e);
                    }
                } else {
                    logger.warn("Project with ID " + resultSet.getInt("id") + " has null etat_projet");
                }
                
                int clientId = resultSet.getInt("client_id");
               
                ClientService clientService = new ClientService();
                Client client = clientService.getClientById(clientId);
                if (client != null) {
                    project.setClient(client);
                } else {
                    logger.warn("Client with ID " + clientId + " not found for project with ID " + resultSet.getInt("id"));
                }
                
                projects.add(project);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving all projects from database", e);
        }
        return projects;
    }

    /**
     * Updates the state of a project in the database.
     *
     * @param project The Project object with updated information.
     */
    public void updateProject(Project project) {
        String sql = "UPDATE project SET  etat_projet = ? WHERE id = ?";
        
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                
            statement.setString(1, project.getEtatProjet().toString());
            statement.setInt(2, project.getId());
            
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                logger.warn("Updating project failed, no rows affected.");
            }
        } catch (SQLException e) {
            logger.error("Error updating project in database", e);
        }
    }
}

