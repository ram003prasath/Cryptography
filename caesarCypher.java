import java.util.Scanner;

public class caesarCypher {

    public static void encrypt(String text, int shift, StringBuilder encrypted) {
        for (int i = 0; i < text.length(); i++) {
            encrypted.append((char) (text.charAt(i) + shift));
        }
    }

    public static void decrypt(String text, int shift, StringBuilder decrypted) {
        for (int i = 0; i < text.length(); i++) {
            decrypted.append((char) (text.charAt(i) - shift));
        }
    }

    public static void bruteforce(String encrypted, String originalText) {
        StringBuilder decrypted = new StringBuilder();
        for (int shift = 0; shift < 26; shift++) {
            decrypted.setLength(0);  
            decrypt(encrypted, shift, decrypted);
            if (decrypted.toString().equals(originalText)) {
                System.out.println("Shift " + shift + ": " + decrypted.toString());
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text to encrypt: ");
        String text = scanner.nextLine();
        System.out.print("Enter the shift value: ");
        int shift = scanner.nextInt();

        StringBuilder encrypted = new StringBuilder();
        StringBuilder decrypted = new StringBuilder();

        encrypt(text, shift, encrypted);
        System.out.println("Encrypted text: " + encrypted.toString());

        decrypt(encrypted.toString(), shift, decrypted);
        System.out.println("Decrypted text: " + decrypted.toString());

        bruteforce(encrypted.toString(), text);

        scanner.close();
    }
}
