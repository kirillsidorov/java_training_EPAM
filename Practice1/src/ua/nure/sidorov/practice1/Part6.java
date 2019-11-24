package ua.nure.sidorov.practice1;

public class Part6{
	private static boolean isPrime(int n, int[] primes, int last) {
		if (n < 3) {
			return true;
		}
		int root = (int)Math.sqrt(n);
		for (int i = 0; i < last; ++i) {
			if (n % primes[i] == 0) {
				return false;
			}
			if (primes[i] > root) {
				return true;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		int size = Integer.parseInt(args[0]);
		int[] primes = new int[size];
		int lastPrime = 2;

		for (int i = 0; i < size; ++i) {
			primes[i] = lastPrime++;
			while (!isPrime(lastPrime, primes, i)) {
				lastPrime++;
			}
		}
		for (int s = 0; s < size; s++) {
				System.out.print(primes[s] + " ");
		}
		System.out.println();
	}
}