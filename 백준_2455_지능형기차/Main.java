package 백준_2455_지능형기차;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("0 32\r\n" + "3 13\r\n" + "28 25\r\n" + "39 0");

		int result = 0;
		int max = 0;
		for (int i = 0; i < 4; i++) {
			int minus = sc.nextInt();
			int plus = sc.nextInt();

			result = result - minus + plus;

			max = Math.max(max, result);
		}

		System.out.println(max);
	}
}
