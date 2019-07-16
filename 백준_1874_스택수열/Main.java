package 백준_1874_스택수열;

import java.util.Scanner;

public class Main {
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("8\r\n" + "4\r\n" + "3\r\n" + "6\r\n" + "8\r\n" + "7\r\n" + "5\r\n" + "2\r\n" + "1");

		N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		boolean[] visit = new boolean[N + 1];
		int min = 0;

		for (int i = 0; i < N; i++) {
			int data = sc.nextInt();
			// 최소값이 오른만큼 sb에 +한줄 추가
			if (min < data) {
				add(sb, '+', data - min);
				min = find_min(visit, min);
				visit[data] = true;
			} else if (min - 1 == data) {
				add(sb, '+', 1);
				min = find_min(visit, min);
				visit[data] = true;
			}
		}

		System.out.println(sb.toString());
	}

	private static int find_min(boolean[] visit, int min) {
		for (int i = min; i >= 1; i--) {
			if (!visit[i]) {
				return i;
			}
		}
		return 0;
	}

	public static void add(StringBuilder sb, char c, int num) {
		// num번 sb에 c를 한줄한줄 추가
		for (int j = 0; j < num; j++) {
			sb.append(c + "\n");
		}
	}
}
