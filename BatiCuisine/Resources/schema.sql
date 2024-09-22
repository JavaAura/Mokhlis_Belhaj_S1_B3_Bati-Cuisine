CREATE DATABASE baticuisine;

\c baticuisine;  -- Connect to the newly created database

-- Table for storing client information
CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,  -- Client's name
    adresse VARCHAR(255),       -- Client's address
    telephone VARCHAR(20),      -- Client's phone number
    est_professionnel BOOLEAN   -- Indicates if the client is a professional or not
);

-- Table for storing project information
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,                  -- Project name
    marge_beneficiaire DOUBLE PRECISION,        -- Profit margin for the project
    cout_total DOUBLE PRECISION,                -- Total cost of the project
    etat_projet VARCHAR(50) CHECK (etat_projet IN ('EN_COURS', 'TERMINE', 'ANNULE')),  -- Project status
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE SET NULL  -- Link to the client
);

-- Table for storing quotation information
CREATE TABLE devis (
    id SERIAL PRIMARY KEY,
    montant_estime DOUBLE PRECISION,  -- Estimated amount of the quotation
    date_emission DATE,               -- Date when the quotation was issued
    date_validite DATE,               -- Expiration date of the quotation
    accepte BOOLEAN,                  -- Indicates if the quotation was accepted
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE  -- Link to the project
);

-- Table for storing component information (parent table for materials and main_oeuvre)
CREATE TABLE composants (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,    -- Component name
    type_composant VARCHAR(50),   -- Type of component (e.g., material, labor)
    taux_tva DOUBLE PRECISION     -- VAT rate for the component
);

-- Table for storing material information
CREATE TABLE material (
    id INT PRIMARY KEY REFERENCES composants(id) ON DELETE CASCADE,  -- Inherits from composants
    cout_unitaire DOUBLE PRECISION,     -- Unit cost of the material
    quantite DOUBLE PRECISION,          -- Quantity of the material
    cout_transport DOUBLE PRECISION,    -- Transportation cost
    coefficient_qualite DOUBLE PRECISION -- Quality coefficient of the material
);

-- Table for storing labor information
CREATE TABLE mainoeuvre (
    id INT PRIMARY KEY REFERENCES composants(id) ON DELETE CASCADE,  -- Inherits from composants
    taux_horaire DOUBLE PRECISION,      -- Hourly rate for labor
    heures_travail DOUBLE PRECISION,    -- Number of work hours
    productivite_ouvrier DOUBLE PRECISION -- Worker productivity factor
);

-- Junction table to link projects and components
CREATE TABLE project_composants (
    project_id INT,
    composant_id INT,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (composant_id) REFERENCES composants(id) ON DELETE CASCADE,
    PRIMARY KEY (project_id, composant_id)
);
