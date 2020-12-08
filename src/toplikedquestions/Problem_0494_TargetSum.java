package toplikedquestions;

public class Problem_0494_TargetSum {

	public static int findWays(int[] arr, int s) {
		if (arr == null || arr.length == 0) {
			return s == 0 ? 1 : 0;
		}
		return ways(arr, 0, s);
	}

	// dp[index][rest]
	public static int ways(int[] arr, int index, int rest) {
		if (index == arr.length) {
			return rest == 0 ? 1 : 0;
		}
		int p1 = ways(arr, index + 1, rest + arr[index]);
		int p2 = ways(arr, index + 1, rest - arr[index]);
		return p1 + p2;
	}

	public static int dp(int[] arr, int s) {
		if (arr == null || arr.length == 0) {
			return s == 0 ? 1 : 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		if (sum < s) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][sum * 2 + 1];
		dp[N][0 + sum] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = -sum; rest <= sum; rest++) {
				dp[index][sum + rest] = 0;
				if (sum + rest + arr[index] <= 2 * sum) {
					dp[index][sum + rest] += dp[index + 1][sum + rest + arr[index]];
				}
				if (sum + rest - arr[index] >= 0) {
					dp[index][sum + rest] += dp[index + 1][sum + rest - arr[index]];
				}
			}
		}
		return dp[0][sum + s];
	}

	// 题目已知arr中都是非负数
	// arr中每个数都能在之前添加+或者-，想最后的结果是num，返回方法数
	public static int findTargetSumWays1(int[] arr, int num) {
		return process(arr, num, 0, 0);
	}

	// arr[0..index-1]的范围已经添加过符号了，具体是什么不用操心，反正形成了pre的结果
	// 现在请在arr[index...]上添加符号，请问在之前结果为pre的情况下，
	// 最终形成num的方法数是多少？返回
	public static int process(int[] arr, int num, int index, int pre) {
		if (index == arr.length) {
			return pre == num ? 1 : 0;
		}
		// 添加+号，形成的方法数
		int ways = process(arr, num, index + 1, pre + arr[index]);
		// 再累加上，添加-号，形成的方法数
		ways += process(arr, num, index + 1, pre - arr[index]);
		return ways;
	}

	public static int findTargetSumWays2(int[] arr, int num) {
		int N = arr.length;
		// index变化范围是0..N
		// 如果arr所有数字的累加和是sum，那么pre的范围是：-sum ~ +sum
		// 所以改成记忆化搜索可能更好，不过这里无所谓了，改成严格表结构吧
		int sum = 0;
		for (int cur : arr) {
			sum += cur;
		}
		if (num > sum) {
			return 0;
		}
		int M = 2 * sum + 1;
		int[][] dp = new int[N + 1][M];
		// 举个例子，如果arr，累加和是10，那么pre有可能的范围是-10到+10
		// 所以我们需要准备21列，去对应pre的变化范围，也就是dp[N+1][2 * sum + 1]
		// 我们规定如何表示pre：用dp[...][sum+pre]来表示，当然也可以有其他的表示。
		// 如何表示pre=0的时候？dp[...][10+0]->用dp[...][10]来表示
		// 如何表示pre=1的时候？dp[...][10+1]->用dp[...][11]来表示
		// 如何表示pre=5的时候？dp[...][10+5]->用dp[...][15]来表示
		// 如何表示pre=10的时候？dp[...][10+10]->用dp[...][20]来表示
		// 如何表示pre=-1的时候？dp[...][10+(-1)]->用dp[...][9]来表示
		// 如何表示pre=-5的时候？dp[...][10+(-5)]->用dp[...][5]来表示
		// 如何表示pre=-10的时候？dp[...][10+(-10)]->用dp[...][0]来表示
		// 所以pre等于多少，都可以表示下
		dp[N][sum + num] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int pre = -sum; pre <= sum; pre++) {
				if (sum + pre + arr[index] < M) {
					dp[index][sum + pre] = dp[index + 1][sum + pre + arr[index]];
				}
				if (sum + pre - arr[index] >= 0) {
					dp[index][sum + pre] += dp[index + 1][sum + pre - arr[index]];
				}
			}
		}
		return dp[0][sum + 0];
	}

}
