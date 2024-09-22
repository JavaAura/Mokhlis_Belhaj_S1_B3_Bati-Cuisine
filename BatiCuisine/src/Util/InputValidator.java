package Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputValidator {

    private static final Logger logger = LoggerFactory.getLogger(InputValidator.class);

   

    //  valider le choix dans un intervalle
    public static boolean isValidChoice(String choice, int min, int max) {
        try {
            int value = Integer.parseInt(choice);
            if (value >= min && value <= max) {
                return true;
            } else {
                logger.error("Choix hors de l'intervalle valide ({} - {}), veuillez réessayer.", min, max);
                return false;
            }
        } catch (NumberFormatException e) {
            logger.error("Choix invalide, veuillez entrer un nombre entier.");
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
}
