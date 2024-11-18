import java.util.Scanner;

class eucli{
    public static int gcd(int a, int b){
        int r1 = a;
        int r2 = b;
        int s1 = 1;
        int s2 = 0;
        int t1 = 0;
        int t2 = 1;
        int s, t;
        while(r2 > 0){
            int q = r1/r2;
            int r = r1 - q * r2;
            r1 = r2;
            r2 = r;
            s = s1 - q * s2;
            s1 = s2;
            s2 = s;
            t = t1 - q * t2;
            t1 = t2;
            t2 = t;
        }
        s = s1;
        t = t1;
        if(((s*a)+(t*b)) == r1){
            System.out.println("Proved (S * A) + (T * B) = gcd(A,B)");
        }
        return r1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a,b;
        System.out.println("Enter value for a: ");
        a = sc.nextInt();
        System.out.println("Enter value for b: ");
        b = sc.nextInt();
        sc.close();
        int val = gcd(a,b);
        System.out.println("gcd(a,b) = " + val);
    }
}