import java.util.Scanner;

public class ShiftCipherBFM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        for (int shift = 0; shift < 26; shift++) {
            String result = shiftCipher(text, shift);
            System.out.println("Shift " + shift + ": " + result);
        }

        scanner.close();
    }
            
    private static String shiftCipher(String text, int shift) {
        String result = "";

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';

                char shifted = (char) ((character - base + shift + 26) % 26 + base);
                result += shifted; 
            } else {
                result += character;
            }
        }

        return result;
    }
}