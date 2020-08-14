package toplikedquestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem_0739_DailyTemperatures {

	public static int[] dailyTemperatures(int[] arr) {
		if (arr == null || arr.length == 0) {
			return new int[0];
		}
		int[] record = rightMoreNear(arr);
		int N = arr.length;
		int[] ans = new int[N];
		for (int i = 0; i < N; i++) {
			ans[i] = record[i] == -1 ? 0 : (record[i] - i);
		}
		return ans;
	}

	public static int[] rightMoreNear(int[] arr) {
		int N = arr.length;
		int[] ans = new int[N];
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(0)] < arr[i]) {
				List<Integer> popIs = stack.pop();
				for (Integer popi : popIs) {
					ans[popi] = i;
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(Integer.valueOf(i));
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}
		while (!stack.isEmpty()) {
			List<Integer> popIs = stack.pop();
			for (Integer popi : popIs) {
				ans[popi] = -1;
			}
		}
		return ans;
	}

}
