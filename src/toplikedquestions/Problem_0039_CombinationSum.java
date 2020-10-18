package toplikedquestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problem_0039_CombinationSum {

	/*
	 * 两个方法都能通过， 测试时想用哪个方法，就把函数名改成combinationSum
	 */

	public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
		return process1(candidates, candidates.length - 1, target);
	}

	// 想凑出来的和叫target
	// arr[0...index]所有的数，自由选择，每一种数任意个，(index+1....) 不用管
	// 凑出target，所有的方案，返回，List<List<Integer>>
	public static List<List<Integer>> process1(int[] arr, int index, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		if (target == 0) { // index == -1
			ans.add(new ArrayList<>());
			return ans;
		}
		// target != 0 
		if (index == -1) {
			return ans;
		}
		// 当前的数可以使用多少个
		for (int zhang = 0; zhang * arr[index] <= target; zhang++) {
			// 已经决定了使用zhang个，当前数
			List<List<Integer>> preLists = process1(arr, index - 1, target - (zhang * arr[index]));
			for (List<Integer> pre : preLists) {
				for (int i = 0; i < zhang; i++) {
					pre.add(arr[index]);
				}
				ans.add(pre);
			}
		}
		return ans;
	}

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		HashMap<Integer, HashMap<Integer, List<List<Integer>>>> map = new HashMap<>();
		return process2(candidates, candidates.length - 1, target, map);
	}

	public static List<List<Integer>> process2(int[] arr, int index, int target,
			HashMap<Integer, HashMap<Integer, List<List<Integer>>>> map) {
		if (map.containsKey(index) && map.get(index).containsKey(target)) {
			return copy(map.get(index).get(target));
		}
		List<List<Integer>> ans = new ArrayList<>();
		if (target == 0) {
			ans.add(new ArrayList<>());
			setAns(index, target, ans, map);
			return copy(map.get(index).get(target));
		}
		if (index == -1) {
			setAns(index, target, ans, map);
			return copy(map.get(index).get(target));
		}
		for (int zhang = 0; zhang * arr[index] <= target; zhang++) {
			List<List<Integer>> preLists = process2(arr, index - 1, target - (zhang * arr[index]), map);
			for (List<Integer> pre : preLists) {
				for (int i = 0; i < zhang; i++) {
					pre.add(arr[index]);
				}
				ans.add(pre);
			}
		}
		setAns(index, target, ans, map);
		return copy(map.get(index).get(target));
	}

	public static void setAns(int index, int target, List<List<Integer>> ans,
			HashMap<Integer, HashMap<Integer, List<List<Integer>>>> map) {
		if (!map.containsKey(index)) {
			map.put(index, new HashMap<>());
		}
		if (!map.get(index).containsKey(target)) {
			map.get(index).put(target, ans);
		}
	}

	public static List<List<Integer>> copy(List<List<Integer>> lists) {
		List<List<Integer>> ans = new ArrayList<>();
		for (List<Integer> cur : lists) {
			ArrayList<Integer> n = new ArrayList<>();
			for (Integer num : cur) {
				n.add(num);
			}
			ans.add(n);
		}
		return ans;
	}

}
