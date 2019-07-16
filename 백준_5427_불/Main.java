package 백준_5427_불;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] dRow = { 0, 0, -1, 1 }, dCol = { -1, 1, 0, 0 };
	static int result, H, W;

	static class Node implements Comparable<Node> {
		int row, col, time;
		char type;

		public Node(int row, int col, int time, char type) {
			this.row = row;
			this.col = col;
			this.time = time;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", time=" + time + ", type=" + type + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (time == o.time) {
				return (-1) * Character.compare(type, o.type);
			}
			return time - o.time;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("5\r\n" + "4 3\r\n" + "###@\r\n" + "#*..\r\n" + "####\r\n" + "7 6\r\n" + "###.###\r\n"
				+ "#*#.#*#\r\n" + "#.....#\r\n" + "#.....#\r\n" + "#..@..#\r\n" + "#######\r\n" + "7 4\r\n"
				+ "###.###\r\n" + "#....*#\r\n" + "#@....#\r\n" + ".######\r\n" + "5 5\r\n" + ".....\r\n" + ".***.\r\n"
				+ ".*@*.\r\n" + ".***.\r\n" + ".....\r\n" + "3 3\r\n" + "###\r\n" + "#@#\r\n" + "###");

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			W = sc.nextInt();
			H = sc.nextInt();

			boolean flag = false;

			char[][] map = new char[H][W];
			PriorityQueue<Node> q = new PriorityQueue<>();

			for (int i = 0; i < map.length; i++) {
				String line = sc.next();
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '@' || map[i][j] == '*') {
						q.add(new Node(i, j, 0, map[i][j]));
					}
					if (i == 0 || j == 0 || i == map.length - 1 || j == map[0].length - 1) {
						if (map[i][j] == '.') {
							map[i][j] = 'E';
						}
						if (map[i][j] == '@') {
							flag = true;
						}
					}
				}
			}
//			for (int i = 0; i < map.length; i++) {
//				for (int j = 0; j < map[0].length; j++) {
//					if (map[i][j] == '*') {
//						q.add(new Node(i, j, 0, '*'));
//					}
//				}
//			}
//			for (char[] cs : map) {
//				System.out.println(Arrays.toString(cs));
//			}

			result = 0;

			bfs(q, map);

			if (flag) {
				System.out.println(1);
			} else {
				System.out.println(result == 0 ? "IMPOSSIBLE" : result);
			}
		}

	}

	private static void bfs(Queue<Node> q, char[][] map) {
		while (!q.isEmpty()) {
			Node n = q.poll();
//			for (char[] cs : map) {
//				System.out.println(Arrays.toString(cs));
//			}
//			System.out.println(n);

			if (n.type == '@' && map[n.row][n.col] == 'E') {
				result = n.time + 1;
				return;
			}

			if (n.type == '@' && map[n.row][n.col] == '*') {
				continue;
			}

			if (n.type == '@') {
				for (int dir = 0; dir < dRow.length; dir++) {
					int row = n.row + dRow[dir];
					int col = n.col + dCol[dir];
					if (isIn(row, col)) {
						char next = map[row][col];
						if (next == '.' || next == 'E') {
							if (next == '.') {
								map[row][col] = '@';
							}
							q.add(new Node(row, col, n.time + 1, n.type));
						}
					}
				}
			} else if (n.type == '*') {
				for (int dir = 0; dir < dRow.length; dir++) {
					int row = n.row + dRow[dir];
					int col = n.col + dCol[dir];
					if (isIn(row, col)) {
						char next = map[row][col];
						if (next != '#' && next != '*') {
							map[row][col] = '*';
							q.add(new Node(row, col, n.time + 1, n.type));
						}
					}
				}
			}
		}
	}

	static boolean isIn(int row, int col) {
		return 0 <= row && row < H && 0 <= col && col < W;
	}

}
