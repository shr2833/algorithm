package 백준_1325_효율적인해킹;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("6 5\r\n" + "3 1\r\n" + "3 2\r\n" + "4 3\r\n" + "5 3 1 2");

		int N = sc.nextInt();
		int M = sc.nextInt();

		List<Integer>[] adj = new List[N + 1];

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int end = sc.nextInt();
			int start = sc.nextInt();
			adj[start].add(end);
		}
		for (List<Integer> list : adj) {
			System.out.println(list);
		}

		memo = new HashMap<>();
		List<Integer> results = new ArrayList<>();

		int max = 0;

		for (int i = 1; i < adj.length; i++) {
			int temp = recur(adj, i);
			if (max < temp) {
				max = temp;
				results.clear();
				results.add(i);
			} else if (max == temp) {
				results.add(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (Integer integer : results) {
			sb.append(integer + " ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());

		for (int i = 1; i <= 5; i++) {
			System.out.println(recur(adj, i));
		}
	}

	static Map<Integer, Integer> memo;

	static int recur(List<Integer>[] adj, int idx) {
		if (memo.containsKey(idx)) {
			return memo.get(idx);
		}

		int sum = 1;

		for (int i = 0; i < adj[idx].size(); i++) {
			sum += recur(adj, adj[idx].get(i));
		}

		memo.put(idx, sum);
		return sum;
	}

}
