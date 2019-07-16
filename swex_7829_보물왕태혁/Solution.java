package swex_7829_보물왕태혁;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int T;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("3\r\n" + "4\r\n" + "2 3 4 6\r\n" + "3\r\n" + "27 3 9\r\n" + "1\r\n" + "13");

		T = sc.nextInt();

		for (int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			int nums[] = new int[N];
			int max = 0;
			for (int i = 0; i < nums.length; i++) {
				nums[i] = sc.nextInt();
				max = Math.max(max, nums[i]);
			}
			Arrays.sort(nums);
			int result = 0;
			outer: for (int i = 0; i < nums.length; i++) {
				for (int j = i; j < nums.length; j++) {
					int temp = nums[i] * nums[j];
					if (temp > max) {
						if (isResult(nums, temp)) {
							result = temp;
							break outer;
						}
					}
				}
			}
//			System.out.println(Arrays.toString(nums));

			System.out.println("#" + testCase + " " + result);
		}
	}

	static boolean isResult(int[] nums, int temp) {
		for (int i = 0; i < nums.length; i++) {
			if (temp % nums[i] != 0) {
				return false;
			}
		}
		return true;
	}
}
