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

public class ClientRepository {
    private DatabaseConnection dbConnection;
    private static final Logger logger = LoggerFactory.getLogger(ClientRepository.class);

    public ClientRepository() {
            this.dbConnection = DatabaseConnection.getInstance();
    }

    // Method to add a new client
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

    // Method to retrieve a client by ID
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

    // Method to get all clients
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

