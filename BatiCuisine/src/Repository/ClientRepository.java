package Repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Metier.Client;
import Util.DatabaseConnection;

/**
 * Repository class for managing Client entities in the database.
 */
public class ClientRepository {
    private DatabaseConnection dbConnection;
    private static final Logger logger = LoggerFactory.getLogger(ClientRepository.class);

    /**
     * Constructor for ClientRepository.
     * Initializes the database connection.
     */
    public ClientRepository() {
            this.dbConnection = DatabaseConnection.getInstance();
    }

    /**
     * Adds a new client to the database.
     *
     * @param client The Client object to be added.
     * @return The Client object with updated ID after insertion.
     * @throws RuntimeException if there's an error during the insertion.
     */
    public Client addClient(Client client) {
        String sql = "INSERT INTO client (nom, adresse, telephone, est_professionnel) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getAdresse());
            pstmt.setString(3, client.getTelephone());
            pstmt.setBoolean(4, client.isEstProfessionnel());
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating client failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
            return client;
        } catch (SQLException e) {
            logger.error("Error adding client", e);
            throw new RuntimeException("Error adding client", e);
        }
    }
    
    /**
     * Retrieves a list of clients whose names match the given search term.
     *
     * @param name The search term to match against client names.
     * @return A List of Client objects matching the search term.
     */
    public List<Client> getClientsByName(String name) {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client WHERE nom LIKE ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("est_professionnel")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Error retrieving clients by name", e);
        }
        return clients;
    }

    /**
     * Retrieves a client by their ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The Client object if found.
     * @throws SQLException if the client is not found or if there's a database error.
     */
    public Client getClientById(int id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("est_professionnel")
                    );
                } else {
                    throw new SQLException("Client not found with id: " + id);
                }
            }
        }
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return A List of all Client objects in the database.
     */
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                clients.add(new Client(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("adresse"),
                    rs.getString("telephone"),
                    rs.getBoolean("est_professionnel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client The Client object with updated information.
     */
    public void updateClient(Client client) {
        String sql = "UPDATE client SET nom = ?, adresse = ?, telephone = ?, est_professionnel = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getAdresse());
            pstmt.setString(3, client.getTelephone());
            pstmt.setBoolean(4, client.isEstProfessionnel());
            pstmt.setInt(5, client.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a client from the database by their ID.
     *
     * @param id The ID of the client to be deleted.
     */
    public void deleteClient(int id) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

