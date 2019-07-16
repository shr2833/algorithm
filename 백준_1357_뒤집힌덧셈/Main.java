package 백준_1357_뒤집힌덧셈;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("123 100");

		int a = sc.nextInt();
		int b = sc.nextInt();
		int result = rev(rev(a) + rev(b));

		System.out.println(result);
	}

	static int rev(int x) {
		String reversed = reverse(x + "");
		return Integer.parseInt(reversed);
	}

	static String reverse(String str) {
		StringBuilder reversed = new StringBuilder();

		for (int i = str.length() - 1; i >= 0; i--) {
			reversed.append(str.charAt(i));
		}
		return reversed.toString();
	}
}
