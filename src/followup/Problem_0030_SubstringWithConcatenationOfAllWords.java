package followup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problem_0030_SubstringWithConcatenationOfAllWords {

	public static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> ans = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return ans;
		}
		int wordLen = words[0].length();
		int wordNum = words.length;
		if (s.length() < wordLen * wordNum) {
			return ans;
		}
		HashMap<String, Integer> map = new HashMap<>();
		for (String key : words) {
			if (!map.containsKey(key)) {
				map.put(key, 1);
			} else {
				map.put(key, map.get(key) + 1);
			}
		}
		for (int start = 0; start < wordLen; start++) {
			find(s, start, wordLen, wordNum, map, ans);
		}
		return ans;
	}

	public static void find(String s, int start, int wordLen, int wordNum, HashMap<String, Integer> map,
			List<Integer> ans) {
		int N = s.length();
		int allLen = wordLen * wordNum;
		HashMap<String, Integer> window = new HashMap<>();
		int debt = wordNum;
		for (int part = 0; part < wordNum; part++) {
			int L = start + part * wordLen;
			int R = L + wordLen;
			if (R > s.length()) {
				break;
			}
			String cur = s.substring(L, R);
			if (!window.containsKey(cur)) {
				window.put(cur, 1);
			} else {
				window.put(cur, window.get(cur) + 1);
			}
			if (map.containsKey(cur) && window.get(cur) <= map.get(cur)) {
				debt--;
			}
		}
		if (debt == 0) {
			ans.add(start);
		}
		for (int next = start + wordLen; next <= N - allLen; next += wordLen) {
			String out = s.substring(next - wordLen, next);
			String in = s.substring(next + allLen - wordLen, next + allLen);
			window.put(out, window.get(out) - 1);
			if (map.containsKey(out) && window.get(out) < map.get(out)) {
				debt++;
			}
			if (!window.containsKey(in)) {
				window.put(in, 1);
			} else {
				window.put(in, window.get(in) + 1);
			}
			if (map.containsKey(in) && window.get(in) <= map.get(in)) {
				debt--;
			}
			if (debt == 0) {
				ans.add(next);
			}
		}
	}

}
