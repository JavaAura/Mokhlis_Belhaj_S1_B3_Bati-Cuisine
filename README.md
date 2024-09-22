# Mokhlis_Belhaj_S1_B3_Bati-Cuisine

```

```BatiCuisine/
├── src/
│           ├── Main.java                 # Application entry point
│           ├── ui/
│           │   └── ConsoleUI.java        # Console user interface
│           ├── service/
│           │   ├── ProjectService.java       # Service to manage projects
│           │   ├── ClientService.java        # Service to manage clients
│           │   ├── ComposantsService.java    # Service to manage Composants
│           │   └── DevisService.java         # Service to manage Devis
│           ├── metier/
│           │   ├── Project.java          # Project entity
│           │   ├── Client.java           # Client entity
│           │   ├── Devis.java            # Devis entity
│           │   ├── Composants.java       # Composants entity (abstract class)
│           │   ├── Material.java         # Material entity (extends Composants)
|           |   └── MainOeuvre.java       # MainOeuvre entity (extends Composants)
│           ├── repository/
│           │   ├── ClientRepository.java     # Repository for clients
│           │   ├── ProjectRepository.java    # Repository for projects
│           │   ├── DevisRepository.java      # Repository for Devis
│           │   └── ComposantsRepository.java # Repository for Composants
│           │   
│           └── util/
│               ├── DateUtils.java          # Date management utilities
|               ├── databaseConnection.java # connection to the database
|               └── enums/
|                   └── EtatProjet.java     # enum for the state of the project(EN_COURS, TERMINE, ANNULE)
├── resources/
│   ├── config.properties                 # Configuration file (including DB connection details)
│   └── schema.sql                        # SQL script to create tables
├── README.md
└── .gitignore
'''
instalation
config.properties file
contenu of fill 
```Config.properties
db.url=jdbc:example
db.user=exampleUser
db.password=examplePassword
```