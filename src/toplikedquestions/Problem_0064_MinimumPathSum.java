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

}
