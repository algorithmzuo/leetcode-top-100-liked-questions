package toplikedquestions;

public class Problem_0338_CountingBits {

	public int[] countBits1(int num) {
		int[] ans = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			ans[i] = count(i);
		}
		return ans;
	}

	public static int count(int n) {
		n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
		n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
		n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
		return n;
	}

	public int[] countBits2(int num) {
		int[] ans = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			ans[i] = ans[i >> 1] + (i & 1);
		}
		return ans;
	}

}
