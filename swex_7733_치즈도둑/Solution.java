package swex_7733_치즈도둑;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static int T;

	static class Node {
		int row, col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(Solution.class.getResourceAsStream("./input.txt"));

		T = sc.nextInt();

		for (int testCase = 1; testCase <= T; testCase++) {
			int result = 0;
			int N = sc.nextInt();

			int minLimit = 100;
			int maxLimit = 0;

			int[][] map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					int data = sc.nextInt();
					map[i][j] = data;
					minLimit = Math.min(minLimit, data);
					maxLimit = Math.max(maxLimit, data);
				}
			}
//			for (int[] is : map) {
//				System.out.println(Arrays.toString(is));
//			}

			int max = 1;

			for (int limit = minLimit; limit <= maxLimit; limit++) {
				boolean[][] isVisit = new boolean[N][N];
				result = 0;
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						if (!isVisit[i][j] && map[i][j] > limit) {
							result++;
							bfs(i, j, limit, map, isVisit);
						}
					}
				}
				max = Math.max(max, result);
			}

			System.out.println("#" + testCase + " " + max);
		}

	}

	static int[] dRow = { 0, 0, -1, 1 };
	static int[] dCol = { -1, 1, 0, 0 };

	static public void bfs(int row, int col, int limit, int[][] map, boolean[][] isVisit) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(row, col));
		isVisit[row][col] = true;

		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int dir = 0; dir < dRow.length; dir++) {
				int nr = n.row + dRow[dir];
				int nc = n.col + dCol[dir];
				if (isIn(nr, nc, map) && !isVisit[nr][nc] && map[nr][nc] > limit) {
					isVisit[nr][nc] = true;
					q.add(new Node(nr, nc));
				}
			}
		}
	}

	static private boolean isIn(int row, int col, int[][] map) {
		return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
	}
}
