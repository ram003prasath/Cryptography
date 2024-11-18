import java.util.Scanner;

public class DES {

    private static final int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    private static final int[] IP_INV = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    private static final int[][] S_BOX = {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    };

    private static final String SAMPLE_KEY = "133457799BBCDFF1";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 64-bit block of plain text (in hex):");
        String plaintextHex = sc.nextLine();

        String plaintext = hexToBinary(plaintextHex);

        if (plaintext.length() != 64) {
            System.out.println("Error: Plaintext must be 64 bits long after converting from hex.");
            return;
        }

        String ciphertext = encrypt(plaintext, SAMPLE_KEY);
        System.out.println("Encrypted ciphertext (in binary): " + ciphertext);

        String decryptedText = decrypt(ciphertext, SAMPLE_KEY);
        System.out.println("Decrypted plaintext (in binary): " + decryptedText);

        sc.close();
    }

    public static String encrypt(String plaintext, String key) {
        String permutedText = initialPermutation(plaintext);

        String substitutedText = substituteUsingSBox(permutedText);

        String encryptedText = inverseInitialPermutation(substitutedText);

        return encryptedText;
    }

    public static String decrypt(String ciphertext, String key) {
        String permutedText = initialPermutation(ciphertext);

        String substitutedText = substituteUsingSBox(permutedText);

        String decryptedText = inverseInitialPermutation(substitutedText);

        return decryptedText;
    }

    public static String hexToBinary(String hex) {
        StringBuilder binary = new StringBuilder();
        for (char c : hex.toCharArray()) {
            binary.append(String.format("%4s", Integer.toBinaryString(Integer.parseInt(String.valueOf(c), 16))).replace(' ', '0'));
        }
        return binary.toString();
    }

    public static String initialPermutation(String input) {
        return permute(input, IP);
    }

    public static String inverseInitialPermutation(String input) {
        return permute(input, IP_INV);
    }

    public static String substituteUsingSBox(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i += 6) {
            String sixBits = input.substring(i, Math.min(i + 6, input.length()));
            int row = Integer.parseInt(sixBits.substring(0, 1) + sixBits.substring(5), 2);
            int col = Integer.parseInt(sixBits.substring(1, 5), 2);
            int sBoxValue = S_BOX[row][col];
            output.append(String.format("%4s", Integer.toBinaryString(sBoxValue)).replace(' ', '0'));
        }
        return output.toString();
    }

    public static String permute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            output.append(input.charAt(table[i] - 1));
        }
        return output.toString();
    }
}
