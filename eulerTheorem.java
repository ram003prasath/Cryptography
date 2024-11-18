class eulerTheorem 
{
	static boolean isprime(long n)
	{
		for (int i = 2; i * i <= n; i++) 
		{
			if (n % i == 0) 
			{
				return false;
			}
		}
		return false;
	}

	static boolean isperfect(long n) 
	{
		long s = -n;
		for (long i = 1; i * i <= n; i++) 
		{

			if (n % i == 0) 
			{
				long factor1 = i, factor2 = n / i;
				s += factor1 + factor2;
				if (factor1 == factor2) 
				{
					s -= i;
				}
			}
		}
		return (n == s);
	}
	public static void main(String[] args) 
	{
		long power2[] = new long[61];
		for (int i = 0; i <= 60; i++)
		{
			power2[i] = 1L << i;
		}

		System.out.print("Generating first few numbers " + "satisfying Euclid Euler's theorem\n");
		for (int i = 2; i <= 25; i++) 
		{
			long no = (power2[i] - 1) * (power2[i - 1]);
			if (isperfect(no) && (no % 2 == 0)) 
			{
				System.out.print("(2^" + i + " - 1) * (2^(" + i + " - 1)) = " + no + "\n");
			}
		}
	}
} 

