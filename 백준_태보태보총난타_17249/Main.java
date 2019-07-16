package 백준_태보태보총난타_17249;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("@===@==@=@==(^0^)==@=@===@");

		int left = 0, right = 0;
		String line = sc.next();
		boolean flag = false;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '0') {
				flag = true;
			}
			if (c == '@') {
				if (flag) {
					right++;
				} else {
					left++;
				}
			}
		}

		System.out.println(left + " " + right);

	}
}
