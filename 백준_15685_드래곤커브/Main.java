package 백준_15685_드래곤커브;

import java.util.Scanner;

public class Main {
	static int T;

	static class Curve {
		int sr, sc, dir, g;

		public Curve(int sr, int sc, int dir, int g) {
			this.sr = sr;
			this.sc = sc;
			this.dir = dir;
			this.g = g;
		}
		
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("3\r\n" + "3 3 0 1\r\n" + "4 2 1 3\r\n" + "4 2 2 1");

		T = sc.nextInt();

		for (int testCase = 1; testCase <= T; testCase++) {
			int result = 0;

			System.out.println("#" + testCase + " " + result);
		}
	}
}
