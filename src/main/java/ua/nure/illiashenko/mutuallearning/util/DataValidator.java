package ua.nure.illiashenko.mutuallearning.util;

import org.springframework.stereotype.Component;

@Component
public class DataValidator {

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
