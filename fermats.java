import java.util.Scanner;

public class fermats{
    public static long modExp(long base, long exp, long mod){
        long result = 1;
        base = base % mod;
        while(exp > 0){
            if((exp % 2) == 1){
                result = (result * base) % mod;
            }
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return result;
    }

    public static boolean checkFermat(long a, long p){
        if(p <= 1 || !isPrime(p)){
            System.out.println("p must be prime");
        }
        return modExp(a, p-1,p) == 1;
    }

    public static boolean isPrime(long num){
        if(num <= 1) return false;
        if(num <= 3) return true;
        if(num % 2 == 0 || num % 3 == 0) return false;
        for(long i =5; i * i <= num; i +=6){
            if(num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a: ");
        long a = sc.nextInt();
        System.out.println("Enter p: ");
        long p = sc.nextInt();
        sc.close();

        if(checkFermat(a,p)){
            System.out.println("Fermat theorem holds.");
        }
        else{
            System.out.println("Fermat theorem does not hold.");
        }
    }
}