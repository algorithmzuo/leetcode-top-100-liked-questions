package toplikedquestions;

public class Problem_0621_TaskScheduler {

	public static int leastInterval(char[] tasks, int n) {
		// 所有任务中，有最多出现次数的任务，出现了几次
		int maxCount = 0;
		int[] count = new int[256];
		for (char task : tasks) {
			count[task]++;
			maxCount = Math.max(maxCount, count[task]);
		}
		// 都是最多出现次数的任务有几个
		int maxKinds = 0;
		for (int task = 0; task < 256; task++) {
			if (count[task] == maxCount) {
				maxKinds++;
			}
		}
		// partNum代表连续空格子串的份数
		// 假设A出现了5次，且出现数量最多，且只有A出现了5次
		// 那么应该怎么安排？
		// A...A...A...A...A
		// ...的部分表示一份连续空格子串，可能任意长度
		// 需要注意，最后一个A并不需要补充... ，即不需要补充一份连续空格子串，因为任务可以直接结束
		// 可以看出...这个部分出现了4次，所以是4份 = 5 - 1
		// 需要注意的是，因为A出现了5次，剩下的字符都不如5次，
		// 所以，A...A...A...A...A这里面一定能放下剩下所有的任务
		// 再举个例子，
		// 假设A、B都出现了5次，且出现数量最多
		// 那么应该怎么安排？
		// AB...AB...AB...AB...AB
		// ...的部分表示一份连续空格子串，可能任意长度
		// 需要注意，最后一个AB并不需要补充... ，即不需要补充一份连续空格子串，因为任务可以直接结束
		// 可以看出...这个部分出现了4次，所以是4份 = 5 - 1
		int partNum = maxCount - 1;
		// partLen代表每份连续空格子串至少应该多长
		// 例子一
		// 假设A出现了5次，且出现数量最多，且只有A出现了5次，即如果maxKinds == 1
		// 假设n = 3，空格用X表示
		// 那么应该安排如下：
		// AXXXAXXXAXXXAXXXA
		// 可以看到每份连续空格子串都是XXX，长度为 3 = 3 - 1 + 1
		// 例子二
		// 假设A和B都出现了5次，且出现数量最多，即如果maxKinds == 2
		// 假设n = 3，空格用X表示
		// 那么应该安排如下：
		// ABXXABXXABXXABXXAB
		// 可以看到每份连续空格子串都是XX，长度为 2 = 3 - 2 + 1
		// 例子三
		// 假设A、B、C、D、E都出现了5次，且出现数量最多，即如果maxKinds == 5
		// 假设n = 3，空格用X表示
		// 那么应该安排如下：
		// ABCDEABCDEABCDEABCDEABCDE
		// 可以看到并不需要连续空格子串，长度为 0 = Math.max(0, 3 - 5 + 1)
		// 为什么要补充空格X？为了满足任务之间的间隔要求
		// 所以综合起来，公式就是下行代码
		int partLen = Math.max(0, n - maxKinds + 1);
		// 所以总的空格数量 = 连续空格子串的分数 * 每份连续空格子串的长度
		// 注意，这个总的空格数量，不是说最终就一定需要补充这么多空格，
		// 而是说在有这么多空格的情况下，一定满足任务之间的间隔要求
		int emptySlots = partNum * partLen;
		// taskRest表示除了拥有最大次数的任务之外还有多少剩余任务
		int taskRest = tasks.length - maxCount * maxKinds;
		// emptySlots是说，在有这么多空格的情况下，一定满足任务之间的间隔要求
		// 于是接下来，需要把剩下的任务，拆分着尽量放在每一份的空间上，那么一定也满足任务之间的间隔要求
		// 最好的情况是，emptySlots == taskRest，也就是每一个空格空间上，都放了剩余任务
		// 如果放不满，那么多出来的空格也不能去掉，因为去掉的话将不满足任务之间的间隔要求
		// idles表示如果所有任务都放完了，还剩多少空格
		int idles = Math.max(0, emptySlots - taskRest);
		// tasks.length表示所有任务都放完了，长度是多少；idles表示还需要补多少空格
		return tasks.length + idles;
	}

}
