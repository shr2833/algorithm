package 백준_15686_치킨배달;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] base_map;
	static ArrayList<Node> houses;
	static int result;
	static int[] dRow = { -1, 1, 0, 0 }, dCol = { 0, 0, -1, 1 };

	static class Node {
		int row, col, type, time;

		public Node(int row, int col, int type, int time) {
			this.row = row;
			this.col = col;
			this.type = type;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", type=" + type + ", time=" + time + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "5 1\r\n" + "1 2 0 2 1\r\n" + "1 2 0 2 1\r\n" + "1 2 0 2 1\r\n" + "1 2 0 2 1\r\n" + "1 2 0 2 1";
		sc = new Scanner(str);

		// 0 빈집
		// 1 집
		// 2 치킨집
		int N = sc.nextInt();
		int M = sc.nextInt();

		houses = new ArrayList<>();
		ArrayList<Node> chickens = new ArrayList<>();

		base_map = new int[N][N];
		for (int i = 0; i < base_map.length; i++) {
			for (int j = 0; j < base_map[0].length; j++) {
				int data = sc.nextInt();
				if (data == 1) {
					base_map[i][j] = data;
					houses.add(new Node(i, j, 1, 0));
				} else if (data == 2) {
					chickens.add(new Node(i, j, 2, 0));
				}
			}
		}

//		for (int[] is : base_map) {
//			System.out.println(Arrays.toString(is));
//		}
//		System.out.println(chickens.size());

		Node[] selected = new Node[M];
		result = Integer.MAX_VALUE;
		recur(chickens, selected, 0, 0);

		System.out.println(result);
	}

	static void recur(ArrayList<Node> chickens, Node[] selected, int idx, int cnt) {
//		System.out.println(Arrays.toString(selected));
		// 치킨집은 많을수록 이득이다. 그러니 M개를 고르자
		if (cnt == selected.length) {
			// M개를 다 골랐다.
			calDistance(selected);
			return;
		}
		if (idx >= chickens.size()) {
			return;
		}

//		System.out.println(Arrays.toString(selected));
//		System.out.println(idx);
		selected[cnt] = chickens.get(idx);
		recur(chickens, selected, idx + 1, cnt + 1);
		selected[cnt] = null;
		recur(chickens, selected, idx + 1, cnt);
	}

	private static void calDistance(Node[] selected) {
		// 선택된 치킨집을 맵에 추가하고
		int[][] map = getDeepCopyArray(base_map);
		for (int i = 0; i < selected.length; i++) {
			Node n = selected[i];
			map[n.row][n.col] = 2;
		}

		// 선택된 치킨집으로 치킨거리를 구하자
		int sum = 0;
//		for (int[] is : map) {
//			System.out.println(Arrays.toString(is));
//		}
//		System.out.println(houses);
//		System.out.println(bfs(houses.get(1), map));
		for (int i = 0; i < houses.size(); i++) {
			// 각 집에서의 치킨거리를 더해서 더하자
			Node start = houses.get(i);
			sum += bfs(start, map);
			if (sum >= result) {
				// 이 치킨집으론 이전에 구한 최소값보다 별로다.
				return;
			}
		}
		result = Math.min(result, sum);
	}

	private static int bfs(Node start, int[][] map) {
		// start 집에서 치킨거리 구해서 리턴하자
		Queue<Node> q = new LinkedList<>();
		boolean[][] visit = new boolean[map.length][map[0].length];
		q.add(start);
		visit[start.row][start.col] = true;

		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int dir = 0; dir < dRow.length; dir++) {
				int row = n.row + dRow[dir];
				int col = n.col + dCol[dir];
				if (isIn(row, col, map) && !visit[row][col]) {
					if (map[row][col] == 2) {
						return n.time + 1;
					} else {
						visit[row][col] = true;
						q.add(new Node(row, col, map[row][col], n.time + 1));
					}
				}
			}
		}

		return -1;
	}

	static boolean isIn(int row, int col, int[][] map) {
		return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
	}

	private static int[][] getDeepCopyArray(int[][] map) {
		int[][] copyed = new int[map.length][map[0].length];
		for (int i = 0; i < copyed.length; i++) {
			for (int j = 0; j < copyed[0].length; j++) {
				copyed[i][j] = map[i][j];
			}
		}
		return copyed;
	}
}
