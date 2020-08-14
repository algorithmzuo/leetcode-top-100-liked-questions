package toplikedquestions;

public class Problem_0621_TaskScheduler {

	public static int leastInterval(char[] tasks, int n) {
		int[] count = new int[256];
		int maxCount = 0;
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
		int restTasks = tasks.length - maxKinds;
		int spaces = (n + 1) * (maxCount - 1);
		int restSpaces = Math.max(0, spaces - restTasks);
		return tasks.length + restSpaces;
	}

}
