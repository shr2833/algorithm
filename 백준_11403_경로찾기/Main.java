package 백준_11403_경로찾기;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("7\r\n" + "0 0 0 1 0 0 0\r\n" + "0 0 0 0 0 0 1\r\n" + "0 0 0 0 0 0 0\r\n" + "0 0 0 0 1 1 0\r\n"
				+ "1 0 0 0 0 0 0\r\n" + "0 0 0 0 0 0 1\r\n" + "0 0 1 0 0 0 0");

		int N = sc.nextInt();
		int[][] dp = new int[N][N];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = sc.nextInt();
				if (dp[i][j] == 0) {
					dp[i][j] = 1000;
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				for (int j2 = 0; j2 < dp.length; j2++) {
					dp[j][j2] = Math.min(dp[j][i] + dp[i][j2], dp[j][j2]);
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (dp[i][j] >= 1000) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = 1;
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
