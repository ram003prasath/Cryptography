import java.math.BigInteger;
import java.util.Scanner;

public class RSACipherExample {

    private BigInteger n; 
    private BigInteger e; 
    private BigInteger d; 
    private BigInteger p; 
    private BigInteger q; 
    private BigInteger phi; 

    public RSACipherExample(BigInteger p, BigInteger q, BigInteger e) {
        this.p = p;
        this.q = q;
        this.n = p.multiply(q);
        this.phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        this.e = e;
        this.d = calculatePrivateKey();
    }

    private BigInteger calculatePrivateKey() {
        return e.modInverse(phi); // Calculate d as the modular inverse of e
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigInteger p = null;
        BigInteger q = null;

        while (true) {
            System.out.print("Enter prime number p: ");
            p = scanner.nextBigInteger();
            if (p.isProbablePrime(1)) {
                break;
            } else {
                System.out.println("That is not a prime number. Please try again.");
            }
        }

        while (true) {
            System.out.print("Enter prime number q: ");
            q = scanner.nextBigInteger();
            if (q.isProbablePrime(1)) {
                break;
            } else {
                System.out.println("That is not a prime number. Please try again.");
            }
        }

        BigInteger e;
        while (true) {
            System.out.print("Enter public exponent e (must be 1 < e < φ(n)): ");
            e = scanner.nextBigInteger();
            if (e.compareTo(BigInteger.ONE) > 0 && e.compareTo((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE))) < 0) {
                if (e.gcd((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE))).equals(BigInteger.ONE)) {
                    break;
                } else {
                    System.out.println("e must be coprime to φ(n). Please try again.");
                }
            } else {
                System.out.println("Invalid value for e. Please try again.");
            }
        }

        RSACipherExample rsa = new RSACipherExample(p, q, e);

        BigInteger message;
        while (true) {
            System.out.print("Enter a number to encrypt (must be less than " + rsa.n + "): ");
            message = scanner.nextBigInteger();
            if (message.compareTo(rsa.n) < 0) {
                break;
            } else {
                System.out.println("Message must be less than " + rsa.n + ". Please try again.");
            }
        }

        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted Data: " + encryptedMessage);

        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted Data: " + decryptedMessage);

        scanner.close();
    }
}