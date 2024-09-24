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
    surface bigint NOT NULL,                    -- Project surface
    marge_beneficiaire NUMERIC(10, 2),          -- Profit margin for the project
    cout_total NUMERIC(12, 2),                  -- Total cost of the project
    etat_projet VARCHAR(50) CHECK (etat_projet IN ('EN_COURS', 'TERMINE', 'ANNULE')),  -- Project status
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE SET NULL  -- Link to the client
);

-- Table for storing quotation information
CREATE TABLE devis (
    id SERIAL PRIMARY KEY,
    montant_estime NUMERIC(12, 2),  -- Estimated amount of the quotation
    date_emission DATE,             -- Date when the quotation was issued
    date_validite DATE,             -- Expiration date of the quotation
    accepte BOOLEAN,                -- Indicates if the quotation was accepted
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE  -- Link to the project
);

-- Table for storing component information (parent table for materials and labor)
CREATE TABLE composants (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,    -- Component name
    type_composant VARCHAR(50),   -- Type of component (e.g., material, labor)
    taux_tva NUMERIC(5, 2),       -- VAT rate for the component
    project_id INT,
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE  -- Link to the project
);

-- Table for storing material information
CREATE TABLE material (
    id INT PRIMARY KEY REFERENCES composants(id) ON DELETE CASCADE,  -- Inherits from composants
    cout_unitaire NUMERIC(12, 2),         -- Unit cost of the material
    quantite NUMERIC(12, 2),              -- Quantity of the material
    cout_transport NUMERIC(12, 2),        -- Transportation cost
    coefficient_qualite NUMERIC(5, 2)     -- Quality coefficient of the material
);

-- Table for storing labor information
CREATE TABLE mainoeuvre (
    id INT PRIMARY KEY REFERENCES composants(id) ON DELETE CASCADE,  -- Inherits from composants
    taux_horaire NUMERIC(10, 2),         -- Hourly rate for labor
    heures_travail NUMERIC(10, 2),       -- Number of work hours
    productivite_ouvrier NUMERIC(5, 2)   -- Worker productivity factor
);
