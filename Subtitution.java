import java.util.Hashtable;
import java.util.Scanner;

public class Subtitution {

    public static String encrypt(String message, Hashtable<Character, Character> set) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (set.containsKey(c)) {
                sb.append(set.get(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String decrypt(String encryptedMessage, Hashtable<Character, Character> set) {
        // Reverse the mapping
        Hashtable<Character, Character> reverseSet = new Hashtable<>();
        for (Character key : set.keySet()) {
            reverseSet.put(set.get(key), key);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char c = encryptedMessage.charAt(i);
            if (reverseSet.containsKey(c)) {
                sb.append(reverseSet.get(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Hashtable<Character, Character> set = new Hashtable<>();
        set.put('a', 'm');
        set.put('b', 'j');
        set.put('c', 'w');
        set.put('d', 'f');
        set.put('e', 'g');
        set.put('f', 'h');
        set.put('g', 'z');
        set.put('h', 'd');
        set.put('i', 'k');
        set.put('j', 'l');
        set.put('k', 'e');
        set.put('l', 'n');
        set.put('m', 'b');
        set.put('n', 'p');
        set.put('o', 'q');
        set.put('p', 'x');
        set.put('q', 'v');
        set.put('r', 't');
        set.put('s', 'u');
        set.put('t', 's');
        set.put('u', 'c');
        set.put('v', 'r');
        set.put('w', 'y');
        set.put('x', 'i');
        set.put('y', 'a');
        set.put('z', 'o');

        Scanner sc = new Scanner(System.in);

        // Encrypt message
        System.out.print("Enter Message to Encrypt: ");
        String message = sc.nextLine();
        String encryptedMessage = encrypt(message, set);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypt message
        System.out.println("Decrypted Message: " + decrypt(encryptedMessage, set));

        sc.close();
    }
}
