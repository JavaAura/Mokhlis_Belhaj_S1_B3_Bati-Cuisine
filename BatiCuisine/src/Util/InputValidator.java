package Util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputValidator {

    private static final Logger logger = LoggerFactory.getLogger(InputValidator.class);
    public InputValidator() {};

   

    //  valider le choix dans un intervalle
    public static boolean isValidChoice(String choice, int min, int max) {
        try {
            int value = Integer.parseInt(choice);
            if (value >= min && value <= max) {
                return true;
            } else {
                logger.info("Choix hors de l'intervalle valide ({} - {}), veuillez réessayer.", min, max);
                return false;
            }
        } catch (NumberFormatException e) {
            logger.error("Choix invalide, veuillez entrer un nombre entier." + e);
            return false;
        }
    }

            public static boolean isValidName(String name) {
                if (name == null || name.trim().isEmpty()) {
                    logger.error("Le nom ne peut pas être vide.");
                    return false;
                }
                if (!name.matches("^[\\p{L} .'-]+$")) {
                    logger.error("Le nom contient des caractères non autorisés. Utilisez uniquement des lettres, espaces, points, apostrophes et tirets.");
                    return false;
                }
                return true;
            }

    // Valide si le numéro de téléphone est valide (format international ou local)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+?\\d{10,14}$");
    }

    // Valide si l'adresse est non nulle et non vide
    public static boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }
// Valide si l'input est un boolean acsept only "true" or "false"
public static boolean isValidBoolean(String input) {
    return input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false");
}
public static boolean isValidSurface(String surface) {
    try {
        double value = Double.parseDouble(surface);
        return value > 0;
    } catch (NumberFormatException e) {
        logger.error("La surface doit être un nombre positif. " + e);
        return false;
    }
}
public static boolean isValidTauxTVA(String tauxTVA) {
    try {
        double value = Double.parseDouble(tauxTVA);
        // tauxTVA must be between 1 and 20
        if (value >= 1 && value <= 20) {
            return true;
        } else {
            logger.error("Le taux de TVA doit être compris entre 1 et 20.");
            return false;
        }
    } catch (NumberFormatException e) {
        logger.error("Le taux de TVA doit être un nombre valide.", e);
        return false;
    }
}
public static boolean isValidDouble(String quantite) {
    try {
        double value = Double.parseDouble(quantite);
        return value > 0;
    } catch (NumberFormatException e) {
        logger.error("La quantité doit être un nombre positif. " + e);
        return false;
    }
}
public static boolean isValidDoubleProductivité(String productiviteOuvrier) {
    try {
        double value = Double.parseDouble(productiviteOuvrier);
        return value >= 1 && value <= 1.5;
    } catch (NumberFormatException e) {
        logger.error("La productivité de l'ouvrier doit être un nombre entre 1 et 1.5. " + e);
        return false;
    }
}
public static boolean isValidMarge(String margeBeneficiaire) {
    try {
        double value = Double.parseDouble(margeBeneficiaire);
        return value >= 0 && value <= 30;
    } catch (NumberFormatException e) {
        logger.error("La marge bénéficiaire doit être un nombre entre 0 et 30. " + e);
        return false;
    }
}
public static boolean isValidDate(String date) {
    if (date == null || date.trim().isEmpty()) {
        logger.error("La date ne peut pas être vide.");
        return false;
    }
    
    String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
    if (!date.matches(datePattern)) {
        logger.error("Format de date invalide. Utilisez le format dd/MM/yyyy.");
        return false;
    }
    
    // Additional check for valid day-month combinations
    String[] parts = date.split("/");
    int day = Integer.parseInt(parts[0]);
    int month = Integer.parseInt(parts[1]);
    int year = Integer.parseInt(parts[2]);
    
    if (month == 2) {
        // Check for February
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        if (day > 29 || (day == 29 && !isLeapYear)) {
            logger.error("Date invalide pour février.");
            return false;
        }
    } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
        logger.error("Date invalide pour le mois spécifié.");
        return false;
    }
    
    return true;
}

}
