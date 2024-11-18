import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static BigInteger modExponentiation(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a prime number q : ");
        BigInteger q = new BigInteger(scanner.nextLine());

        System.out.print("Enter the generator alpha: ");
        BigInteger g = new BigInteger(scanner.nextLine());

        System.out.print("Enter Person 1's private key (Xa): ");
        BigInteger a = new BigInteger(scanner.nextLine());

        System.out.print("Enter Person 2's private key (Xb): ");
        BigInteger b = new BigInteger(scanner.nextLine());

        BigInteger A = modExponentiation(g, a, q);
        System.out.println("Person 1's public key A: " + A);

        BigInteger B = modExponentiation(g, b, q);
        System.out.println("Person 2's public key B: " + B);


        BigInteger S_A = modExponentiation(B, a, q);
        System.out.println("Person 1's computed shared secret: " + S_A);

        BigInteger S_B = modExponentiation(A, b,q);
        System.out.println("Person 2's computed shared secret: " + S_B);

        if (S_A.equals(S_B)) {
            System.out.println("The shared secret is successfully established: " + S_A);
        } else {
            System.out.println("Error: The shared secrets do not match!");
        }

        scanner.close();
    }
}