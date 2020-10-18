package toplikedquestions;

public class Problem_0064_MinimumPathSum {

	public static int minPathSum(int[][] grid) {
		if (grid == null || grid[0] == null) {
			return 0;
		}
		int N = grid.length;
		int M = grid[0].length;
		if (N == 0 || M == 0) {
			return 0;
		}
		// 其实应该是一张二维表，但是用了空间压缩技巧
		int[] dp = new int[M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == 0 && j == 0) {
					dp[j] = grid[i][j];
				} else {
					dp[j] = Math.min((i > 0 ? dp[j] : Integer.MAX_VALUE), (j > 0 ? dp[j - 1] : Integer.MAX_VALUE))
							+ grid[i][j];
				}

			}
		}
		return dp[M - 1];
	}

	public static int minPathSum2(int[][] grid) {
		if (grid == null || grid[0] == null) {
			return 0;
		}
		int N = grid.length;
		int M = grid[0].length;
		if (N == 0 || M == 0) {
			return 0;
		}
		// 其实应该是一张二维表，但是用了空间压缩技巧
		int[] dp = new int[M];
		// dp数组变成，想象中二维表的第0行数据
		// m : 3 2 1 6 3 5 6 12
		dp[0] = grid[0][0];
		for (int i = 1; i < M; i++) {
			dp[i] = dp[i - 1] + grid[0][i];
		}
		for (int i = 1; i < N; i++) {
			// dp此时是想象中二维表的第i-1行数据
			// 想更新成，想象中二维表的第i行数据
			// dp[0]
			dp[0] = dp[0] + grid[i][0];
			for (int j = 1; j < M; j++) {
				dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j];
			}
		}
		return dp[M - 1];
	}

}
