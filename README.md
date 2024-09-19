# Mokhlis_Belhaj_S1_B3_Bati-Cuisine

```

```BatiCuisine/
├── src/
│   └── com/
│       └── baticuisine/
│           ├── Main.java                 # Application entry point
│           ├── ui/
│           │   └── ConsoleUI.java        # Console user interface
│           ├── service/
│           │   ├── ProjectService.java   # Service to manage projects
│           │   ├── ClientService.java    # Service to manage clients
│           │   └── QuoteService.java     # Service to manage quotes
│           ├── metier/
│           │   ├── Project.java          # Project entity
│           │   ├── Client.java           # Client entity
│           │   ├── Quote.java            # Quote entity
│           │   └── Material.java         # Material entity
│           ├── repository/
│           │   ├── ClientRepository.java  # Repository for clients
│           │   ├── ProjectRepository.java # Repository for projects
│           │   └── QuoteRepository.java   # Repository for quotes
│           ├── dao/
│           │   ├── ClientDAO.java        # DAO for clients
│           │   ├── ProjectDAO.java       # DAO for projects
│           │   ├── QuoteDAO.java         # DAO for quotes
│           │   └── DatabaseConnection.java # Database connection
│           └── util/
│               └── DateUtils.java        # Date management utilities
├── resources/
│   ├── config.properties                 # Configuration file (including DB connection details)
│   └── schema.sql                        # SQL script to create tables
├── bin/                                  # Folder for compiled files (.class)
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