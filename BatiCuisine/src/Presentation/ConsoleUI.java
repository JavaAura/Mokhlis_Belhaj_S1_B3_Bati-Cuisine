package Presentation;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Scanner;
import java.util.function.Predicate;

import Metier.Client;
import Metier.Composants;
import Metier.Devis;
import Metier.MainOeuvre;
import Metier.Material;
import Metier.Project;
import Util.enums.UniteDeMesure;
import Service.ClientService;
import Service.ComposantsService;
import Service.DevisService;
import Service.ProjectService;
import Util.DateUtils;
import Util.InputValidator;
import Util.enums.EtatProjet;
import Util.enums.TypeMainOeuvre;




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
            System.out.println("4. Quitter");

            String input = scanner.nextLine();
            if (InputValidator.isValidChoice(input, 1, 4)) {
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
        while (true) {
            System.out.println("\n=== Gestion des Projets ===");
            System.out.println("1. Ajouter un nouveau projet");
            System.out.println("2. Afficher les informations des projets");
            System.out.println("0. Retour au menu principal");
            System.out.println("===============================");
            System.out.print("Votre choix : ");

            String input = scanner.nextLine();
            if (InputValidator.isValidChoice(input, 0, 2)) {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        addProject();
                        break;
                    case 2:
                        displayProjectInfo();
                        break;
                  
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
    private void addProject() {
        System.out.println("Ajouter un projet :");
        Client client = selectClient();
        
        if (client != null) {
            String nom = getValidInput("Nom du projet", InputValidator::isValidName);
            int surface = Integer.parseInt(getValidInput("Surface de la cuisine (en m²)", InputValidator::isValidSurface));
            
            HashMap<String, Material> materials = new HashMap<>();
            HashMap<String, MainOeuvre> mainOeuvres = new HashMap<>();

            while (true) {
                System.out.println("--- Ajout des matériaux ---");
                Material material = createMaterial();
                // creat hashmap list of material
                materials.put(material.getNom(), material);
                
                System.out.println("Voulez-vous ajouter un autre matériau ? (oui/non)");
                if (!scanner.nextLine().toLowerCase().equals("oui")) {
                    break;
                }
            }
            while (true) {
                System.out.println("--- Ajout de la main-d'œuvre ---");
                MainOeuvre mainOeuvre = createMainOeuvre();
                // creat hashmap list of mainOeuvre
                mainOeuvres.put(mainOeuvre.getNom(), mainOeuvre);
                System.out.println("Voulez-vous ajouter un autre  main-d'œuvre? (oui/non)");
                if (!scanner.nextLine().toLowerCase().equals("oui")) {
                    break;
                }
            }
            // calcul du cout total du projet
            System.out.println("====Calcul du cout total du projet=====");
            String input = getValidInput("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (1 pour oui et 2 pour non) : ", 
                choice -> InputValidator.isValidChoice(choice, 1, 2));
            
            double margeBeneficiaire = 0;
            if (input.equals("1")) {
                margeBeneficiaire = Double.parseDouble(getValidInput("Entrez le pourcentage de marge bénéficiaire (%) : ", InputValidator::isValidMarge));
            }
            // 
            double coutMaterial = composantsService.materialCalcul(materials);
            double coutMainOeuvre = composantsService.mainOeuvreCalcul(mainOeuvres);
            
            Double coutTotal = projectService.calculCoutTotal(coutMainOeuvre,coutMaterial,margeBeneficiaire);
            // Calculate coutTotal here
            Project project = new Project(nom, surface, margeBeneficiaire, coutTotal, EtatProjet.EN_COURS, client);
            Project addedProject = projectService.addProject(project);
            // Add materials to the project
            for (Material material : materials.values()) {
                material.setProjectAssocie(addedProject);
                composantsService.addComposant(material);
                
            }

            // Add main d'oeuvre to the project
            for (MainOeuvre mainOeuvre : mainOeuvres.values()) {
                mainOeuvre.setProjectAssocie(addedProject);
                composantsService.addComposant(mainOeuvre); // Add main d'oeuvre to the service
            }

            if (addedProject != null) {
                System.out.println("Projet ajouté avec succès :");
                projectService.afficherProject(addedProject);
                createDevis(addedProject);
            } else {
                System.out.println("Échec de l'ajout du projet.");
            }
        } else {
            System.out.println("Échec de la création du projet. Client non valide.");
        }
    }
    private void createDevis(Project project) {
        System.out.println("--- Enregistrement du Devis pour " + project.getNomProjet() + " ---");
        double montantEstime = project.getCoutTotal();
        
            String dateEmissionStr = getValidInput("Entrez la date d'émission du devis (format : jj/mm/aaaa)",InputValidator::isValidDate);
            String dateValiditeStr = getValidInput("Entrez la date de validité du devis (format : jj/mm/aaaa)",InputValidator::isValidDate);

            Date dateEmission = DateUtils.parseDDMMYYYY(dateEmissionStr);
            Date dateValidite = DateUtils.parseDDMMYYYY(dateValiditeStr);        
            Devis devis = new Devis(montantEstime, dateEmission, dateValidite, false, project);
            String input = getValidInput("Souhaitez-vous enregistrer le devis ? (1 pour oui/2 pour non) : ", 
                choice -> InputValidator.isValidChoice(choice, 1, 2));
            if (input.equals("1")) {
                try {
                    Devis savedDevis = devisService.addDevis(devis);
                    if (savedDevis != null) {
                        System.out.println("Devis enregistré avec succès.");
                        devisService.afficherDevis(savedDevis);
                    } else {
                        System.out.println("Erreur lors de l'enregistrement du devis.");
                    }
                } catch (Exception e) {
                    System.out.println("Une erreur est survenue lors de l'enregistrement du devis : " + e.getMessage());
                }
            } else {
                System.out.println("Devis non enregistré.");
            }
     
    }

    private Client selectClient() {
        System.out.println("Est-ce un nouveau client ou un client existant ? (1 pour nouveau, 2 pour existant)");
        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 1, 2)) {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    return addClient();
                case 2:
                    List<Client> clients = searchClientByName();
                    if (clients.size() == 1) {
                        return clients.get(0);
                    } else if (clients.size() > 1) {
                        clients.forEach(item -> clientService.afficherClient(item));
                        System.out.println("Choisissez le client par id :");
                        String inputClient = scanner.nextLine();
                        return clientService.getClientById(Integer.parseInt(inputClient));
                    }
                    break;
            }
        }
        System.out.println("Choix invalide, veuillez réessayer.");
        return null;
    }

    private Material createMaterial() {
        String nomMaterial = getValidInput("Nom du matériau", InputValidator::isValidName);
        UniteDeMesure unite = selectUniteDeMesure();
        
        Double quantite = Double.parseDouble(getValidInput("Quantité de ce matériau (" + unite.getSymbol() + ")", InputValidator::isValidDouble));
        Double coutUnitaire = Double.parseDouble(getValidInput("Coût unitaire de ce matériau (€/" + unite.getSymbol() + ")", InputValidator::isValidDouble));
        Double coutTransport = Double.parseDouble(getValidInput("Coût de transport de ce matériau (€)", InputValidator::isValidDouble));
        Double coefficientQualite = Double.parseDouble(getValidInput("Coefficient de qualité entre 1 et 1.5", InputValidator::isValidDoubleProductivité));
        Double tauxTVA = Double.parseDouble(getValidInput("Taux de TVA (%)", InputValidator::isValidTauxTVA));

        return new Material(nomMaterial, tauxTVA, coutUnitaire, quantite, coutTransport, coefficientQualite, unite, null);
    }
    private MainOeuvre createMainOeuvre() {
        String nomMainOeuvre = getValidInput("Nom de la main d'oeuvre", InputValidator::isValidName);
        
        System.out.println("Choisissez le type de main d'oeuvre :");
        System.out.println("1. Base");
        System.out.println("2. Spécialisé");
        String typeInput = getValidInput("Entrez 1 pour Base ou 2 pour Spécialisé", 
            input -> InputValidator.isValidChoice(input, 1, 2));
        TypeMainOeuvre type = typeInput.equals("1") ? TypeMainOeuvre.base : TypeMainOeuvre.specialise;
        
        double tauxHoraire = Double.parseDouble(getValidInput("Taux horaire (€/h)", InputValidator::isValidDouble));
        double nbHeures = Double.parseDouble(getValidInput("Nombre d'heures", InputValidator::isValidDouble));
        double tauxTVA = Double.parseDouble(getValidInput("Taux de TVA (%)", InputValidator::isValidTauxTVA));
        double productiviteOuvrier = Double.parseDouble(getValidInput("Productivité de l'ouvrier entre 1 et 1.5", InputValidator::isValidDoubleProductivité));
        return new MainOeuvre(nomMainOeuvre, tauxTVA, tauxHoraire, nbHeures, productiviteOuvrier,type,null);

    }

    private UniteDeMesure selectUniteDeMesure() {
        System.out.println("Choisissez l'unité du matériau :");
        UniteDeMesure[] unites = UniteDeMesure.values();
        for (int i = 0; i < unites.length; i++) {
            System.out.println((i + 1) + ". " + unites[i]);
        }
        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 1, unites.length)) {
            return unites[Integer.parseInt(input) - 1];
        }
        System.out.println("Choix invalide, METRE sera utilisé par défaut.");
        return UniteDeMesure.METRE;
    }

    private void displayProjectInfo() {
        System.out.println("\n--- Afficher les informations des projets ---");
        System.out.println("1. Lister tous les projets");
        System.out.println("2. Rechercher un projet par nom");
        System.out.print("Votre choix : ");

        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 1, 2)) {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    listProjects();
                    break;
                case 2:
                    searchProjectByName();
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } else {
            System.out.println("Choix invalide, veuillez réessayer.");
        }
    }

    private void listProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (!projects.isEmpty()) {
            projects.forEach(project -> projectService.afficherProject(project));
        } else {
            System.out.println("Aucun projet trouvé.");
        }
    }

    private boolean searchProjectByName() {
        System.out.println("Entrez le nom du projet à rechercher :");
        String name = scanner.nextLine();
        List<Project> foundProjects = projectService.getProjectsByName(name);
        if (!foundProjects.isEmpty()) {
            foundProjects.forEach(project -> projectService.afficherProject(project));
            return true;
        } else {
            System.out.println("Aucun projet trouvé avec ce nom : " + name);
            return false;
        }
    }

  

 
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
    private Client addClient() {
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
        Client sucess = clientService.addClient(client);

        
     
        clientService.afficherClient(sucess);
        
        System.out.println("\u001B[32mClient ajouté avec succès.\u001B[0m");
        return sucess;
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
    private List<Client> searchClientByName() {
        System.out.println("Entrez le nom du client à rechercher :");
        String name = scanner.nextLine();
        List<Client> foundClients = clientService.getClientsByName(name);
        if (!foundClients.isEmpty()) {
            foundClients.forEach(client -> clientService.afficherClient(client));
        } else {
            System.out.println("Aucun client trouvé avec ce nom : " + name);
        }
        return foundClients; // Return the list of found clients
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
        System.out.println("=== Gestion des Devis ===");
        System.out.println("1. trouver un devis par nom de projet");
        System.out.println("0. Retour au menu principal");
        System.out.println("===============================");
        System.out.print("Votre choix : ");

        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 0, 2)) {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 0:
                    return;
                case 1:
                    searchDevisByProjectName();
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } else {
            System.out.println("Choix invalide, veuillez réessayer.");
        }
    }
    private void searchDevisByProjectName() {
        boolean found = searchProjectByName();
        if (found) {
            System.out.println("Entrez l'id du devis :");
            int id = Integer.parseInt(scanner.nextLine());

        Devis  devis = devisService.getDevisByProjectid(id);
        devisService.afficherDevis(devis);
        System.out.println("voulez-vous accepter le devis ? (1 pour oui/2 pour non)");
        String input = scanner.nextLine();
        if (InputValidator.isValidChoice(input, 1, 2)) {
            int choice = Integer.parseInt(input);
            Project project = devis.getProjetAssocie();
            if (choice == 1) {
                devis.setAccepte(true);
                devisService.updateDevis(devis);
                System.out.println("Devis accepté avec succès.");
                project.setEtatProjet(EtatProjet.TERMINE);
                projectService.updateProject(project);
            } else {
                project.setEtatProjet(EtatProjet.ANNULE); // Change ANNULER to ANNULE
                projectService.updateProject(project);
                System.out.println("Devis non accepté.");
            }
        }
        }

        
    }


  
   

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }
}
