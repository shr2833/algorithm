package swex_7792_반장선출;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int T;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(Solution.class.getResourceAsStream("./input.txt"));

		T = sc.nextInt();

		for (int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			sc.nextLine();
			String[] names = new String[N];
			for (int i = 0; i < N; i++) {
				names[i] = sc.nextLine();
			}
			Arrays.sort(names);
//			System.out.println(Arrays.toString(names));
			int max = 0;
			String result = "";
			for (int i = 0; i < names.length; i++) {
				boolean[] chars = new boolean['Z' - 'A' + 1];
				int tempCnt = 0;
				for (int j = 0; j < names[i].length(); j++) {
					int idx = names[i].charAt(j) - 'A';
					if (names[i].charAt(j) == ' ') {
						continue;
					}
					if (!chars[idx]) {
						tempCnt++;
						chars[idx] = true;
					}
				}
				if (max < tempCnt) {
					result = names[i];
					max = tempCnt;
				}
			}

			System.out.println("#" + testCase + " " + result);
		}
	}
}
