package toplikedquestions;

public class Problem_0096_UniqueBinarySearchTrees {

	public static int numTrees(int N) {
		if (N < 0) {
			return 0;
		}
		if (N < 2) {
			return 1;
		}
		long a = 1;
		long b = 1;
		for (int i = 1, j = N + 1; i <= N; i++, j++) {
			a *= i;
			b *= j;
			long gcd = gcd(a, b);
			a /= gcd;
			b /= gcd;
		}
		return (int) ((b / a) / (N + 1));
	}

	public static long gcd(long m, long n) {
		return n == 0 ? m : gcd(n, m % n);
	}

}
