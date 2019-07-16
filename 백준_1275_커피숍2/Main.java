package 백준_1275_커피숍2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("5 2\r\n" + "1 2 3 4 5\r\n" + "2 3 3 1\r\n" + "3 5 4 1");

		int result = 0;
		int N = sc.nextInt();
		int Q = sc.nextInt();
		int[] nums = new int[N];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = sc.nextInt();
		}
		int[] sums = new int[N];
		sums[0] = nums[0];
		for (int i = 1; i < sums.length; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}
		int[] log = new int[N];

		for (int i = 0; i < Q; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			int a = sc.nextInt() - 1;
			int b = sc.nextInt();

			int l = Math.min(x, y);
			int r = Math.max(x, y);
			log[a] = b;

			int print = sums[r] - sums[l];

			for (int idx = l; idx <= r; idx++) {
				print += log[idx];
			}
			System.out.println(print);
		}
//		System.out.println(Arrays.toString(nums));
//
//		System.out.println(result);

	}
}
