package utils;

public class Validators {

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidPESEL(String pesel) {
        return pesel != null && pesel.matches("\\d{11}");
    }

    public static boolean isValidNIP(String NIP) {
        return NIP != null && NIP.matches("\\d{10}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{9,15}");
    }

    public static boolean passwordsMatch(String pass1, String pass2) {
        return pass1 != null && pass1.equals(pass2);
    }


    public static boolean allFieldsFilled(String... fields) {
        for (String field : fields) {
            if (!isNotEmpty(field)) return false;
        }
        return true;
    }
}
