import java.lang.*;
import java.util.*;

class gcdEuclideanBasic {
    public static int gcd(int a, int b)
    {
        if (a == 0)
            return b;

        return gcd(b % a, a);
    }

    public static void main(String[] args)
    {
        int a, b, g;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value for a: ");
        a = sc.nextInt();
        System.out.print("Enter value for b: ");
        b = sc.nextInt();
        g = gcd(a, b);
        System.out.println("GCD(" + a + " , " + b + ") = " + g);
    }
}