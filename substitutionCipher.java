import java.util.*;

public class substitutionCipher {

    static String[] alphabetsInOrder = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    };

    static String[] alphabetsJumbled = {
            "M", "T", "Q", "R", "Z", "F", "X", "B", "G", "C",
            "K", "A", "N", "L", "W", "O", "D", "H", "U", "P",
            "E", "V", "Y", "S", "I", "J",
            "m", "t", "q", "r", "z", "f", "x", "b", "g", "c",
            "k", "a", "n", "l", "w", "o", "d", "h", "u", "p",
            "e", "v", "y", "s", "i", "j"
    };

    public static String encrypt(String message) {
        String outputString = "";
        List<String> alphabetList = Arrays.asList(alphabetsInOrder);
        for (char character : message.toCharArray()) {
            String S = Character.toString(character);
            int index = alphabetList.indexOf(S);
            outputString += alphabetsJumbled[index];
        }
        return outputString;
    }

    public static String decrypt(String message) {
        String outputString = "";
        List<String> alphabetList = Arrays.asList(alphabetsJumbled);
        for (char character : message.toCharArray()) {
            String S = Character.toString(character);
            int index = alphabetList.indexOf(S);
            outputString += alphabetsInOrder[index];
            // System.out.println(index);
        }
        return outputString;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter plain text: ");
        String pt = sc.nextLine();
        String ct = encrypt(pt);
        System.out.print("\nCiper text: " + ct);
        System.out.print("\nDecryption:" + decrypt(ct));
        sc.close();
    }
}