package 백준_4197_불;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int T, result;

	static class Node {
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

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("4 4\r\n" + "#.F#\r\n" + "#.F#\r\n" + "#..#\r\n" + "#J.#");

//		#: 벽
//		.: 지나갈 수 있는 공간
//		J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
//		F: 불이난 공간

		int R = sc.nextInt();
		int C = sc.nextInt();
		char[][] map = new char[R][C];
		Queue<Node> q = new LinkedList<>();

		for (int i = 0; i < map.length; i++) {
			String line = sc.next();
			for (int j = 0; j < map[0].length; j++) {
				char c = line.charAt(j);
				map[i][j] = c;
				if (c == 'J' || c == 'F') {
					q.add(new Node(i, j, 0, c));
				}
				if (i == 0 || i == map.length - 1 || j == 0 || j == map[0].length - 1) {
					// 테두리이면
					if (map[i][j] == '.') {
						map[i][j] = 'E';
					}
					if (map[i][j] == 'J') {
						System.out.println(1);
						return;
					}
				}
			}
		}

//		for (char[] ds : map) {
//			System.out.println(Arrays.toString(ds));
//		}
		result = 0;
		bfs(map, q);

		if (result == 0) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(result);
		}
	}

	static int[] dRow = { 0, 0, -1, 1 };
	static int[] dCol = { -1, 1, 0, 0 };

	static void bfs(char[][] map, Queue<Node> q) {
		while (!q.isEmpty()) {
//			for (char[] ca : map) {
//				System.out.println(Arrays.toString(ca));
//			}
//			System.out.println(">>>>>>>>>>>>>>>>");

			Node n = q.poll();
			if (n.type != 'F' && map[n.row][n.col] == 'F') {
				// 지훈이가 이동 후에 지훈이 자리가 불에 탔다
				continue;
			}

			if (map[n.row][n.col] == 'E') {
				result = n.time + 1;
				return;
			}

			for (int dir = 0; dir < dRow.length; dir++) {
				int row = n.row + dRow[dir];
				int col = n.col + dCol[dir];

				if (!isIn(row, col, map) || map[row][col] == '#') {
					// 맵 밖이면 그방향은 끝
					continue;
				}

				switch (n.type) {
				case 'J':
					if (map[row][col] == '.') {
						// 이동가능
						map[row][col] = 'J';
						q.add(new Node(row, col, n.time + 1, n.type));
					} else if (map[row][col] == 'E') {
						q.add(new Node(row, col, n.time + 1, 'J'));
					}
					break;
				case 'F':
					if (map[row][col] != '#' && map[row][col] != 'F') {
						map[row][col] = 'F';
						q.add(new Node(row, col, n.time + 1, 'F'));
					}
					break;
				}
			}
		}
	}

	static boolean isIn(int row, int col, char[][] map) {
		return 0 <= row && row < map.length && 0 <= col && col < map[0].length;
	}
}
