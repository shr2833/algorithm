package 백준_아기상어;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int T, N;
	static Queue<Node> q;
	static Node shark;

	static class Node implements Comparable<Node> {
		int row, col, size, cnt, time;

		public Node(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", size=" + size + ", cnt=" + cnt + ", time=" + time + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (o.row == row) {
				return col - o.col;
			}
			return row - o.row;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("6\r\n" + 
				"1 1 1 1 1 1\r\n" + 
				"2 2 6 2 2 3\r\n" + 
				"2 2 5 2 2 3\r\n" + 
				"2 2 2 4 6 3\r\n" + 
				"0 0 0 0 0 6\r\n" + 
				"0 0 0 0 0 9");

		N = sc.nextInt();
		int[][] map = new int[N + 2][N + 2];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i * j == 0 || i == map.length - 1 || j == map[0].length - 1) {
					map[i][j] = 99;
				} else {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 9) {
						shark = new Node(i, j, 0);
						shark.cnt = 0;
						shark.size = 2;
						map[i][j] = 0;
					}
				}
			}
		}

//		for (int[] is : map) {
//			System.out.println(Arrays.toString(is));
//		}

//		더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
//		먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
//		먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
//		거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최소값이다.
//		거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
		q = new LinkedList<>();
		bfs(map);
		System.out.println(shark.time);
	}

	static void bfs(int[][] map) {
		Node target = find(map);
//		System.out.println(shark);
//		System.out.println(target);
		while (target != null) {
//			System.out.println(target);
//			for (int[] is : map) {
//				System.out.println(Arrays.toString(is));
//			}
			shark.time = target.time;
			eat(target, map);
			target = find(map);
		}
	}

	private static void eat(Node target, int[][] map) {
		map[target.row][target.col] = 0;
		shark.cnt++;
		shark.row = target.row;
		shark.col = target.col;
		if (shark.cnt == shark.size) {
			shark.cnt = 0;
			shark.size++;
		}
	}

	static final int[] dRow = { -1, 1, 0, 0 }, dCol = { 0, 0, -1, 1 };
	static final int U = 0, D = 1, L = 2, R = 3;

	static Node find(int[][] map) {
		// shark 에서 먹이 찾아서 리턴
		q.clear();
		boolean[][] visit = new boolean[N + 2][N + 2];
		q.add(shark);
		visit[shark.row][shark.col] = true;

		PriorityQueue<Node> eat = new PriorityQueue<>();
//		1. row, col에서 bfs 해서 범위 안에서 먹을 수 있는 애들 다 큐에 담아서 그 큐에서 우선순위에 따라 나오는애 리턴
		while (!q.isEmpty()) {
			int len = q.size();
			for (int i = 0; i < len; i++) {
				Node n = q.poll();

				for (int dir = 0; dir < dRow.length; dir++) {
					int row = n.row + dRow[dir];
					int col = n.col + dCol[dir];
					if (!visit[row][col]) {
						// 아직 안갔으면 검사 해보자
						visit[row][col] = true;
						int next = map[row][col];
						if (next == 0 || next == shark.size) {
							// 지나가도됨
							q.add(new Node(row, col, n.time + 1));
						} else if (next < shark.size) {
							// 이거 먹을 얘
							eat.add(new Node(row, col, n.time + 1));
						}
					}
				}
			}

			// 이번 거리에서 먹을 수 있는 애들 다 담았다.
			if (!eat.isEmpty()) {
				return eat.poll();
			}
		}
		return null;
	}
}
