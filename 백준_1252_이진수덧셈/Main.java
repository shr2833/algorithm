package 백준_1252_이진수덧셈;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("1000000 11");

		String num1 = sc.next();
		String num2 = sc.next();

		int MAX = Math.max(num1.length(), num2.length());

		int ar = num1.length();
		int br = num2.length();

		int a = 0, b = 0, c = 0;
		StringBuilder result = new StringBuilder("");

		for (int i = 0; i < MAX; i++) {
			ar--;
			br--;
			// 두 수를 더하고 캐리도 더한다.
			// a + b + c
			a = ar < 0 ? 0 : num1.charAt(ar) - '0';
			b = br < 0 ? 0 : num2.charAt(br) - '0';

			int sum = a + b + c;
			c = 0;
			if (sum >= 2) {
				c = 1;
				sum -= 2;
			}
			result.insert(0, sum);
		}
		if (c == 1) {
			result.insert(0, 1);
		}
		System.out.println(result.toString());

	}

}
