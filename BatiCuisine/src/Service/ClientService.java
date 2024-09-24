package Service;

import Repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Metier.Client;

import java.util.Collections;
import java.util.List;

/**
 * Service class for managing client-related operations.
 */
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private ClientRepository clientRepository = new ClientRepository();

    /**
     * Displays the details of a client.
     *
     * @param client The client to display.
     */
    public void afficherClient(Client client) {
        System.out.println("ID : " + client.getId());
        System.out.println("Nom : " + client.getNom());
        System.out.println("Adresse : " + client.getAdresse());
        System.out.println("Numéro de téléphone : " + client.getTelephone());
        System.out.println("===============================");
    }

    /**
     * Adds a new client to the repository.
     *
     * @param client The client to add.
     * @return The added client with updated information (e.g., generated ID).
     */
    public Client addClient(Client client) {
        Client addedClient = clientRepository.addClient(client);
        return addedClient;
    }

    /**
     * Retrieves a client by their ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The client with the specified ID, or null if not found or an error occurs.
     */
    public Client getClientById(int id) {
        try {
            return clientRepository.getClientById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du client par ID: {}. Message: {}", id, e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves all clients from the repository.
     *
     * @return A list of all clients.
     */
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    /**
     * Updates the information of an existing client.
     *
     * @param client The client with updated information.
     */
    public void updateClient(Client client) {
        clientRepository.updateClient(client);
    }

    /**
     * Deletes a client from the repository.
     *
     * @param id The ID of the client to delete.
     */
    public void deleteClient(int id) {
        clientRepository.deleteClient(id);
    }

    /**
     * Retrieves clients by their name.
     *
     * @param nom The name to search for.
     * @return A list of clients with the specified name, or an empty list if none found or an error occurs.
     */
    public List<Client> getClientsByName(String nom) {
        try {
            return clientRepository.getClientsByName(nom);
        } catch (Exception e) {
            logger.error("Erreur lors de la recherche des clients par nom: {}. Message: {}", nom, e.getMessage());
            return Collections.emptyList(); 
        }
    }

   

   
}
