package utils;

import java.security.SecureRandom;

public class AccountNumberGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        // For simplicity, skip checksum calculation, just generate 26 digits
        for (int i = 0; i < 26; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}