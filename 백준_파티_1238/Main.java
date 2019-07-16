package 백준_파티_1238;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("4 8 2\r\n" + "1 2 4\r\n" + "1 3 2\r\n" + "1 4 7\r\n" + "2 1 1\r\n" + "2 3 5\r\n" + "3 1 2\r\n"
				+ "3 4 4\r\n" + "4 2 3");

		int N = sc.nextInt();
		int M = sc.nextInt();
		int X = sc.nextInt();
		final int INFINITY = 1000_0000;

		int[][] dp = new int[N + 1][N + 1];

		for (int[] is : dp) {
			Arrays.fill(is, INFINITY); // 모든 길은 무한대로 설정하고 시작
		}
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int cost = sc.nextInt();
			dp[start][end] = cost;
		}

		for (int mid = 1; mid < dp.length; mid++) {
			for (int start = 1; start < dp.length; start++) {
				for (int end = 1; end < dp.length; end++) {
					dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid][end]);
				}
			}
		}

		int result = 0;

		for (int i = 1; i < dp.length; i++) {
			// 어떤 아이가 파티를 갔다가 돌아가는데 가장 오래걸리는지 찾자
			result = Math.max(dp[i][X] + dp[X][i], result);
		}

		System.out.println(result);

	}
}
