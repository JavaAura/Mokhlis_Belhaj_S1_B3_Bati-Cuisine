# BatiCuisine

BatiCuisine est une application Java de gestion de projets de construction et de rénovation de cuisines. Elle permet aux professionnels de gérer les clients, les projets, les devis et les composants.

## Structure du Projet

```bash
BatiCuisine/
├── src/
│   ├── ui/
│   │   └── ConsoleUI.java        # Interface utilisateur en ligne de commande
│   ├── service/
│   │   ├── ProjectService.java       # Gestion des projets
│   │   ├── ClientService.java        # Gestion des clients
│   │   ├── ComposantsService.java    # Gestion des composants
│   │   └── DevisService.java         # Gestion des devis
│   ├── metier/
│   │   ├── Project.java          # Entité projet
│   │   ├── Client.java           # Entité client
│   │   ├── Devis.java            # Entité devis
│   │   ├── Composants.java       # Classe abstraite pour les composants
│   │   ├── Material.java         # Entité matériau (hérite de Composants)
│   │   └── MainOeuvre.java       # Entité main d'œuvre (hérite de Composants)
│   ├── repository/
│   │   ├── ClientRepository.java     # Repository pour les clients
│   │   ├── ProjectRepository.java    # Repository pour les projets
│   │   ├── DevisRepository.java      # Repository pour les devis
│   │   └── ComposantsRepository.java # Repository pour les composants
│   └── util/
│       ├── DateUtils.java            # Utilitaires pour la gestion des dates
│       ├── DatabaseConnection.java   # Gestion des connexions à la base de données
│       └── enums/
│           ├── TypeMainOeuvre.java   # Enum pour le type de main-d'œuvre (basique, spécialisée)
│           ├── UniteDeMesure.java    # Enum pour les unités de mesure (m2, m3, m, etc.)
│           └── EtatProjet.java       # Enum pour l'état du projet (EN_COURS, TERMINE, ANNULE)
├── resources/
│   ├── config.properties             # Fichier de configuration (informations DB)
│   ├── dig-class-uml.png             # Diagramme de classe UML
│   ├── dig-use-case-uml.png          # Diagramme de cas d'utilisation UML
│   └── schema.sql                    # Script SQL pour créer les tables
├── README.md                         # ce fichier present le projet 
├── BatiCuisine.jar                   # le jar du projet 
└── .gitignore                        # pour ignorer les fichiers 
```

## Prérequis

- Java JDK 8 ou supérieur
- PostgreSQL

## Installation

1. **Configurer la base de données PostgreSQL :**
   - Créez une base de données appelée `baticuisine`.
   - Utilisez le script SQL situé dans le fichier `resources/schema.sql` pour générer les tables nécessaires.
   - mettre ajour la path de fichier config.properties dans le class DatabaseConnection
   ```Java
   Util.DatabaseConnection
           try (FileInputStream input = new FileInputStream("path/to/config.properties")) 

   ```

2. **Configurer le fichier `config.properties` :**
   Mettez à jour le fichier `resources/config.properties` avec vos informations de connexion à la base de données :

   ```properties
   db.url=jdbc:postgresql://localhost:5432/baticuisine
   db.user=VotreUtilisateur
   db.password=VotreMotDePasse
   ````
  

3. **Configurer les bibliothèques et dépendances :**
   Ajoutez manuellement les bibliothèques suivantes à votre classpath :
   * **JDBC Driver pour PostgreSQL** : PostgreSQL JDBC Driver
   * **SLF4J** : Utilisé pour la gestion des logs

4. **Compiler et exécuter :**
   * Importez le projet dans votre IDE (Eclipse, IntelliJ, etc.)
   * Assurez-vous que les fichiers JAR (PostgreSQL JDBC Driver, SLF4J) sont bien ajoutés au classpath
   * Compilez le projet
   * Exécutez la classe `ConsoleUI` pour démarrer l'application via la ligne de commande

## Composants Clés

1. **DatabaseConnection** : Gère les connexions à la base de données en utilisant un modèle singleton pour garantir une seule instance.

2. **Repositories** : Les classes du package `repository` gèrent l'accès aux données pour les entités suivantes :
   * `ClientRepository`
   * `ProjectRepository`
   * `DevisRepository`
   * `ComposantsRepository`

3. **Services** : Les classes du package `service` contiennent la logique métier :
   * `ProjectService`
   * `ClientService`
   * `ComposantsService`
   * `DevisService`

4. **Entités** : Les classes du package `metier` représentent les entités métier :
   * `Project`
   * `Client`
   * `Devis`
   * `Composants` (classe abstraite)
   * `Material`
   * `MainOeuvre`

5. **Interface Utilisateur** : La classe `ConsoleUI` gère l'interface utilisateur en ligne de commande.

## Démarrage

1. **Configurer la base de données :**
   * Exécutez le fichier SQL `resources/schema.sql` pour créer les tables.

2. **Configurer l'application :**
   * Mettez à jour le fichier `config.properties` avec vos identifiants PostgreSQL.

3. **Exécution :**
   * Exécutez la classe `ConsoleUI` pour lancer l'application depuis la ligne de commande.

## Utilisation

Après avoir configuré et lancé l'application, vous pouvez utiliser l'interface en ligne de commande pour :
* Gérer les projets
* Gérer les clients
* Gérer les devis

## Diagrammes UML
### Diagramme de classe
![dig-class-uml](https://github.com/JavaAura/Mokhlis_Belhaj_S1_B3_Bati-Cuisine/raw/main/BatiCuisine/Resources/dig-class-uml.png)

### Diagramme de cas d'utilisation
![dig-use-case-uml](https://github.com/JavaAura/Mokhlis_Belhaj_S1_B3_Bati-Cuisine/raw/main/BatiCuisine/Resources/dig-use-case-uml.png)

### lien de jira 
[BatiCuisine](https://belhajmokhlis.atlassian.net/jira/software/projects/BT/boards/35)



