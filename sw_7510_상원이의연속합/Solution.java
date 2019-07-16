package sw_7510_상원이의연속합;

import java.util.Scanner;

public class Solution {
	static int T;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(Solution.class.getResourceAsStream("./sample_input.txt"));

		T = sc.nextInt();
		long[] sums = new long[100_0001];
		sums[0] = 0;
		for (int i = 1; i < sums.length; i++) {
			sums[i] = sums[i - 1] + i;
		}
//		System.out.println(Arrays.toString(sums));
		for (int testCase = 1; testCase <= T; testCase++) {
			int result = 0;

			int n = sc.nextInt();
			for (int right = n; right >= 0; right--) {
				for (int left = right; left >= 0; left--) {
					long dif = sums[right] - sums[left];
					if (dif == n) {
						result++;
					} else if (dif > n) {
						break;
					}
				}
			}

			System.out.println("#" + testCase + " " + result);
		}
	}
}
