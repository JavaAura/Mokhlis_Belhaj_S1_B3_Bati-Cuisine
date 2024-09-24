package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * Checks if the input string is in DD/MM/YYYY format and parses it to a Date object.
     *
     * @param dateString The date string to parse.
     * @return A Date object if the string is valid, null otherwise.
     */
    public static Date parseDDMMYYYY(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            logger.error("Failed to parse date: {}. Expected format: DD/MM/YYYY", dateString, e);
            return null;
        }
    }
  
}