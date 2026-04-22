package partC_system;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputValidator {

    // ========== String Validation ==========
    
    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isValidString(String input, int minLength, int maxLength) {
        if (isEmpty(input)) return false;
        int len = input.trim().length();
        return len >= minLength && len <= maxLength;
    }

    public static String validateNonEmptyString(String input, String fieldName) {
        if (isEmpty(input)) {
            throw new IllegalArgumentException(fieldName + " cannot be empty!");
        }
        return input.trim();
    }

    // ========== Numeric Validation ==========
    
    public static boolean isValidNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isPositive(double num) {
        return num > 0;
    }

    public static boolean isPositiveInteger(int num) {
        return num > 0;
    }

    public static double validatePositiveDouble(String input, String fieldName) {
        if (!isValidNumber(input)) {
            throw new IllegalArgumentException(fieldName + " must be a valid number!");
        }
        double value = Double.parseDouble(input);
        if (!isPositive(value)) {
            throw new IllegalArgumentException(fieldName + " must be positive!");
        }
        return value;
    }

    public static int validatePositiveInteger(String input, String fieldName) {
        if (!isValidInteger(input)) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer!");
        }
        int value = Integer.parseInt(input);
        if (!isPositiveInteger(value)) {
            throw new IllegalArgumentException(fieldName + " must be positive!");
        }
        return value;
    }

    public static int validateInRange(String input, String fieldName, int min, int max) {
        int value = validatePositiveInteger(input, fieldName);
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max + "!");
        }
        return value;
    }

    // ========== Category Validation ==========
    
    public static boolean isValidCategory(String category) {
        return category != null && (
            category.equalsIgnoreCase("food") ||
            category.equalsIgnoreCase("electronic") ||
            category.equalsIgnoreCase("beverage") ||
            category.equalsIgnoreCase("clothing") ||
            category.equalsIgnoreCase("cleaning") ||
            category.equalsIgnoreCase("personalcare")
        );
    }

    public static String validateCategory(String category) {
        if (!isValidCategory(category)) {
            throw new IllegalArgumentException(
                "Invalid category! Valid options: food, electronic, beverage, clothing, cleaning, personalcare"
            );
        }
        return category.trim();
    }

    // ========== Phone Number Validation ==========
    
    public static boolean isValidPhoneNumber(String phone) {
        if (isEmpty(phone)) return false;
        String cleaned = phone.replaceAll("[^0-9]", "");
        return cleaned.length() >= 7 && cleaned.length() <= 15;
    }

    public static String validatePhoneNumber(String phone) {
        if (!isValidPhoneNumber(phone)) {
            throw new IllegalArgumentException("Phone number must contain 7-15 digits!");
        }
        return phone.trim();
    }

    // ========== Date Validation ==========
    
    public static boolean isValidDate(String dateStr) {
        if (isEmpty(dateStr)) return false;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFutureDate(String dateStr) {
        if (!isValidDate(dateStr)) return false;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }

    public static String validateExpiryDate(String dateStr) {
        if (!isValidDate(dateStr)) {
            throw new IllegalArgumentException("Expiry date must be in format dd/MM/yyyy (e.g., 25/12/2025)!");
        }
        if (!isFutureDate(dateStr)) {
            throw new IllegalArgumentException("Expiry date must be in the future!");
        }
        return dateStr.trim();
    }

    // ========== Product ID Validation ==========
    
    public static boolean isValidProductId(String id) {
        if (isEmpty(id)) return false;
        if (id.length() > 20) return false;
        if (!id.matches("[a-zA-Z0-9\\-_]+")) return false;
        // Check if the ID is purely numeric and ensure it's not negative
        if (id.matches("\\d+")) {
            try {
                long numericId = Long.parseLong(id);
                return numericId >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static String validateProductId(String id) {
        if (isEmpty(id)) {
            throw new IllegalArgumentException("Product ID cannot be empty!");
        }
        if (id.length() > 20) {
            throw new IllegalArgumentException("Product ID must be 20 characters or less!");
        }
        if (!id.matches("[a-zA-Z0-9\\-_]+")) {
            throw new IllegalArgumentException("Product ID can only contain alphanumeric characters, hyphens, and underscores!");
        }
        // Validate that if the ID is numeric, it must not be negative
        if (id.matches("\\d+")) {
            try {
                long numericId = Long.parseLong(id);
                if (numericId < 0) {
                    throw new IllegalArgumentException("Product ID cannot be negative!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid Product ID format!");
            }
        }
        return id.trim();
    }
}