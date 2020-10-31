package followup;

public class Problem_0060_PermutationSequence {

	// 0 ~ 9 的阶乘表
	public static int[] jc = { 0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

	// fdp[i][j]含义：一共有i个数，以j开头的全排列的最后一个，是i个数字所有全排列的第几个
	public static int[][] fdp = { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 4, 6, 0, 0, 0, 0, 0, 0 },
			{ 0, 6, 12, 18, 24, 0, 0, 0, 0, 0 },
			{ 0, 24, 48, 72, 96, 120, 0, 0, 0, 0 },
			{ 0, 120, 240, 360, 480, 600, 720, 0, 0, 0 },
			{ 0, 720, 1440, 2160, 2880, 3600, 4320, 5040, 0, 0 },
			{ 0, 5040, 10080, 15120, 20160, 25200, 30240, 35280, 40320, 0 },
			{ 0, 40320, 80640, 120960, 161280, 201600, 241920, 282240, 322560, 362880 }
	};

	public static int[] getThArray(int n, int k) {
		int[] arr = new int[n];
		int restTh = k;
		int restNum = n;
		for (int i = 0; i < n; i++) {
			int kth = 1;
			while (fdp[restNum][kth] < restTh) {
				kth++;
			}
			arr[i] = kth;
			restTh -= fdp[restNum--][kth - 1];
		}
		return arr;
	}

	public static String getPermutation(int n, int k) {
		if (n < 1 || n > 9 || k < 1 || k > jc[n]) {
			return "";
		}
		int[] getThArray = getThArray(n, k);
		char[] chas = new char[n + 1];
		for (int i = 1; i <= n; i++) {
			chas[i] = (char) ('0' + i);
		}
		char[] ans = new char[n];
		for (int i = 0; i < getThArray.length; i++) {
			ans[i] = getRestKthChar(getThArray[i], chas);
		}
		return String.valueOf(ans);
	}

	public static char getRestKthChar(int kth, char[] chas) {
		int index = 0;
		for (int i = 1; i < chas.length; i++) {
			if (chas[i] != 0) {
				if (--kth==0) {
					index = i;
					break;
				}
			}
		}
		char ans = chas[index];
		chas[index] = 0;
		return ans;
	}

	// 为了打出表，做的函数：kth... 最后一个，是整体的第几个
	public static int f(int kth, int all) {
		if (kth < 1 || kth > all) {
			return 0;
		}
		if (kth == 1 && all == 1) {
			return 1;
		}
		return kth * jc[all - 1];
	}

	// 为了打出表，做的函数
	public static void main(String[] args) {
		System.out.println("{");
		for (int all = 0; all <= 9; all++) {
			System.out.println("{");
			for (int kth = 0; kth <= 9; kth++) {
				System.out.print(f(kth, all) + ",");
			}
			System.out.println("},");
		}
		System.out.println("};");
	}

}
