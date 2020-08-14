package toplikedquestions;

public class Problem_0621_TaskScheduler {

	public static int leastInterval(char[] tasks, int limit) {
		int maxCount = 0;
		int[] count = new int[256];
		for (char task : tasks) {
			count[task]++;
			maxCount = Math.max(maxCount, count[task]);
		}
		int maxKinds = 0;
		for (int task = 0; task < 256; task++) {
			if (count[task] == maxCount) {
				maxKinds++;
			}
		}
		int N = tasks.length;
		int spacePartLen = Math.max(0, limit + 1 - maxKinds);
		int spaceParts = maxCount - 1;
		int enoughSpaces = spaceParts * spacePartLen;
		int usedSpaces = N - maxCount * maxKinds;
		int restSpaces = Math.max(0, enoughSpaces - usedSpaces);
		return N + restSpaces;
	}

}
