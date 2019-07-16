package swex_7728_다양성측정;

import java.util.Scanner;

public class Solution {
	static int T;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(Solution.class.getResourceAsStream("./sample_input (1).txt"));

		T = sc.nextInt();
		int a = 1000_0000;

		for (int testCase = 1; testCase <= T; testCase++) {
			int result = 0;
			int n = sc.nextInt();
			String num = Integer.toString(n);
			boolean isExist[] = new boolean[10];
			for (int i = 0; i < num.length(); i++) {
				char c = num.charAt(i);
				if (!isExist[c - '0']) {
					result++;
					isExist[c - '0'] = true;
				}
			}

			System.out.println("#" + testCase + " " + result);
		}
	}
}
