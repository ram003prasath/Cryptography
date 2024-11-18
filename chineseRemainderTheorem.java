import java.util.Scanner;

public class chineseRemainderTheorem {
    
    static int modInverse(int a, int m) {
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;
        
        if (m == 1)
            return 0;

        while (a > 1) {
            q = a / m;
            t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    static int findMinX(int[] num, int[] rem, int k) {
        int prod = 1;
        for (int i = 0; i < k; i++)
            prod *= num[i];

        int result = 0;

        for (int i = 0; i < k; i++) {
            int pp = prod / num[i]; 
            result += rem[i] * modInverse(pp, num[i]) * pp;
        }

        return result % prod;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of equations: ");
        int k = scanner.nextInt();

        int[] num = new int[k];
        int[] rem = new int[k];

        System.out.println("Enter moduli and remainders:");
        for (int i = 0; i < k; i++) {
            System.out.print("Modulus (n_" + (i + 1) + "): ");
            num[i] = scanner.nextInt();
            System.out.print("Remainder (a_" + (i + 1) + "): ");
            rem[i] = scanner.nextInt();
        }

        int x = findMinX(num, rem, k);
        System.out.println("The solution is: x = " + x);

        scanner.close();
    }
}
