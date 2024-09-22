package Service;

import Metier.Client;
import Repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;

public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private ClientRepository clientRepository = new ClientRepository();


    public void afficherClient(Client client) {
        System.out.println("ID : " + client.getId());
        System.out.println("Nom : " + client.getNom());
        System.out.println("Adresse : " + client.getAdresse());
        System.out.println("Numéro de téléphone : " + client.getTelephone());
        System.out.println("===============================");
    }
    public void addClient(Client client) {
        clientRepository.addClient(client);
    }

    public Client getClientById(int id) {
        try {
            return clientRepository.getClientById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du client par ID: {}. Message: {}", id, e.getMessage());
            return null;
        }
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public void updateClient(Client client) {
        clientRepository.updateClient(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteClient(id);
    }

    public List<Client> getClientsByName(String nom) {
        try {
            return clientRepository.getClientsByName(nom);
        } catch (Exception e) {
            logger.error("Erreur lors de la recherche des clients par nom: {}. Message: {}", nom, e.getMessage());
            return Collections.emptyList(); 
        }
    }

   

   
}
