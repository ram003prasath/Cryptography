import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA256 {

    public static String hashWithSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string to hash with SHA-256: ");
            String input = scanner.nextLine();
            String hashedOutput = hashWithSHA256(input);
            if (hashedOutput != null) {
                System.out.println("SHA-256 Hash: " + hashedOutput);
            } else {
                System.out.println("Error occurred while hashing.");
            }
        }
    }
}
