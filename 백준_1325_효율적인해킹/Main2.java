package 백준_1325_효율적인해킹;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		List<Integer>[] adj = new List[N + 1];

		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		boolean[] isVisit = new boolean[N+1];

		for (int i = 0; i < M; i++) {
			int end = sc.nextInt();
			int start = sc.nextInt();
			adj[start].add(end);
			isVisit[end] = true;
		}

		results = new ArrayList<>();
		bfs(adj, isVisit);

		StringBuilder sb = new StringBuilder();
		for (Integer result : results) {
			sb.append(result + " ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	static ArrayList<Integer> results;

	static void bfs(List<Integer>[] adj, boolean[] isVisit) {
		int max = 0;

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < adj.length; i++) {
			// 1부터 해킹가능한 컴퓨터 수 세기
			if (isVisit[i]) {
				continue;
			}
			int tempCnt = 0;
			for (int j = 0; j < adj[i].size(); j++) {
				int n = adj[i].get(j);
				q.add(n);
				tempCnt++;
			}
			while (!q.isEmpty()) {
				int n = q.poll();
				for (int j = 0; j < adj[n].size(); j++) {
					q.add(adj[n].get(j));
					tempCnt++;
				}
			}
			if (max < tempCnt) {
				results = new ArrayList<>();
				results.add(i);
				max = tempCnt;
			} else if (max == tempCnt) {
				results.add(i);
			}
//			System.out.print("i = " + i + " >>  " + results);
//			System.out.println(" >>> " + tempCnt);
		}
	}
}