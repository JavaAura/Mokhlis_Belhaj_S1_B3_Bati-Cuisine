package Presentation;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Consumer;

import Metier.Client;
import Service.ProjectService;
import Util.InputValidator;
import Service.ClientService;
import Service.DevisService;
import Service.ComposantsService;

public class ConsoleUI {
    private Scanner scanner;
    private ProjectService projectService;
    private ClientService clientService;
    private DevisService devisService;
    private ComposantsService composantsService;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        projectService = new ProjectService();
        clientService = new ClientService();
        devisService = new DevisService();
        composantsService = new ComposantsService();
    }

    public void start() {
        while (true) {
            System.out.println("=== Bati-Cuisine Application ===");
            System.out.println("===Menu Principal===");
            System.out.println("1. Gérer les projets");
            System.out.println("2. Gérer les clients");
            System.out.println("3. Gérer les devis");
            System.out.println("4. Gérer les composants");
            System.out.println("5. Quitter");

            String input = scanner.nextLine();
            if (InputValidator.isValidChoice(input, 1, 5)) {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        manageProjects();
                        break;
                    case 2:
                        manageClients();
                        break;
                    case 3:
                        manageDevis();
                        break;
                    case 4:
                        manageComposants();
                        break;
                    case 5:
                        System.out.println("Au revoir!");
                        return;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

    private void manageProjects() {
        // Placeholder for project management functionality
        System.out.println("Gestion des projets...");
        // Implement project management methods here
    }
// gestion des client
    private void manageClients() {
        while (true) {
            System.out.println("\n=== Gestion des Clients ===");
            System.out.println("1. Ajouter un nouveau client");
            System.out.println("2. Afficher les informations clients");
            System.out.println("3. Modifier un client existant");
            System.out.println("4. Supprimer un client");
            System.out.println("0. Retour au menu principal");
            System.out.println("===============================");
            System.out.print("Votre choix : ");

            String input = scanner.nextLine();
            if (InputValidator.isValidChoice(input, 0, 4)) {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        addClient();
                        break;
                    case 2:
                        displayClientInfo();
                        break;
                    case 3:
                        updateClient();
                        break;
                    case 4:
                        deleteClient();
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
    private void displayClientInfo() {
        System.out.println("\n--- Afficher les informations clients ---");
        System.out.println("1. Lister tous les clients");
        System.out.println("2. Rechercher un client par nom");
        System.out.print("Votre choix : ");

        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 1, 2)) {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    listClients();
                    break;
                case 2:
                    searchClientByName();
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } else {
            System.out.println("Choix invalide, veuillez réessayer.");
        }
    }
    private void addClient() {
        System.out.println("Ajouter un client :");
        
        String nom = getValidInput("Nom", InputValidator::isValidName);
        String adresse = getValidInput("Adresse", InputValidator::isValidAddress);
        String telephone = getValidInput("Téléphone", InputValidator::isValidPhoneNumber);

        boolean estProfessionnel = false;
        while (true) {
            System.out.println("Est-ce un professionnel ? (oui/non)");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("oui")) {
                estProfessionnel = true;
                break;
            } else if (input.equals("non")) {
                break;
            } else {
                System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
            }
        }

        Client client = new Client(nom, telephone, adresse, estProfessionnel);
        clientService.addClient(client);
        System.out.println("Client ajouté avec succès.");
    }
    private String getValidInput(String fieldName, Predicate<String> validator) {
        String input;
        do {
            System.out.println(fieldName + " :");
            input = scanner.nextLine().trim();
            if (!validator.test(input)) {
                System.out.println(fieldName + " invalide. Veuillez réessayer.");
            }
        } while (!validator.test(input));
        return input;
    }
    private void listClients() {
        List<Client> clients = clientService.getAllClients();
        if (!clients.isEmpty()) {
            clients.forEach(client ->clientService.afficherClient(client));
        }else{
            System.out.println("Aucun client trouvé.");
        }
    }
    private void deleteClient() {
        System.out.println("Entrer l'id du client à supprimer :");
        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 1, Integer.MAX_VALUE)) {
            int id = Integer.parseInt(input);
        clientService.deleteClient(id);
        System.out.println("Client supprimé avec succès.");
        }
    }
    private void searchClientByName() {
        System.out.println("Entrez le nom du client à rechercher :");
        String name = scanner.nextLine();
        List<Client> foundClients = clientService.getClientsByName(name);
        if (!foundClients.isEmpty()) {
            foundClients.forEach(client -> clientService.afficherClient(client));
        }else{
            System.out.println("Aucun client trouvé avec ce nom:" + name + " ");
        }    
    }
    private void updateClient() {
        System.out.println("Entrez l'ID du client à modifier :");
        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 1, Integer.MAX_VALUE)) {
            int id = Integer.parseInt(input);
            Client client = clientService.getClientById(id);
            if (client == null) {
                System.out.println("Client non trouvé.");
                return;
            }

            updateClientField("nom", client::setNom, InputValidator::isValidName);
            updateClientField("adresse", client::setAdresse, InputValidator::isValidAddress);
            updateClientField("téléphone", client::setTelephone, InputValidator::isValidPhoneNumber);
            
            client.setEstProfessionnel(askYesNoQuestion("Est-ce un professionnel ? (oui/non)"));

            clientService.updateClient(client);
            System.out.println("Client mis à jour avec succès.");
        }
    }
    private void updateClientField(String fieldName, java.util.function.Consumer<String> setter, Predicate<String> validator) {
        while (true) {
            System.out.printf("Nouveau %s (laisser vide pour ne pas changer) :%n", fieldName);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            } else if (validator.test(input)) {
                setter.accept(input);
                break;
            } else {
                System.out.printf("%s invalide. Veuillez entrer un %s valide.%n", fieldName, fieldName);
            }
        }
    }
    private boolean askYesNoQuestion(String question) {
        while (true) {
            System.out.println(question);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("oui")) {
                return true;
            } else if (input.equals("non")) {
                return false;
            } else {
                System.out.println("Réponse invalide. Veuillez répondre par 'oui' ou 'non'.");
            }
        }
    }
   
   
    private void manageDevis() {
        // Placeholder for devis management functionality
        System.out.println("Gestion des devis...");
        // Implement devis management methods here
    }
    private void manageComposants() {
        // Placeholder for composants management functionality
        System.out.println("Gestion des composants...");
        // Implement composants management methods here
    }

   

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }
}
