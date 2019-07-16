package 백준_캐슬디펜스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int T, D, H, W, result;
	static int[][] map;
	static ArrayList<Node> list, copy_list;

	static class Node {
		int row, col, sr, sc;
		Node target;

		public Node(int row, int col) {
			sr = row;
			sc = col;
			init();
		}

		public void init() {
			row = sr;
			col = sc;
			target = null;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", target=" + target + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("6 5 2\r\n" + "1 0 1 0 1\r\n" + "0 1 0 1 0\r\n" + "1 1 0 0 0\r\n" + "0 0 0 1 1\r\n"
				+ "1 1 0 1 1\r\n" + "0 0 1 0 0");

		H = sc.nextInt();
		W = sc.nextInt();
		D = sc.nextInt();

		map = new int[H][W];
		list = new ArrayList<>();
		copy_list = new ArrayList<>();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					list.add(new Node(i, j));
				}
			}
		}

		result = 0;
		recur(new int[3], 0, 0);

		System.out.println(result);
	}

	static int[][] copy(int[][] map) {
		int[][] copy = new int[map.length][map[0].length];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	static ArrayList<Node> copy_list() {
		ArrayList<Node> copyed = new ArrayList<>();
		for (Node node : list) {
			node.init();
			copyed.add(node);
		}
		return copyed;
	}

	static void recur(int[] sel, int idx, int cnt) {
		if (cnt == sel.length) {
			result = Math.max(result, cal(sel));
			return;
		}
		if (idx == W) {
			return;
		}

		sel[cnt] = idx;
		recur(sel, idx + 1, cnt + 1);
		recur(sel, idx + 1, cnt);
	}

	static int dist(Node a, Node b) {
		return Math.abs(a.row - b.row) + Math.abs(b.col - a.col);
	}

	private static int cal(int[] sel) {
//		System.out.println(Arrays.toString(sel));
		int cnt = 0;
		Node[] archers = new Node[3];
		for (int i = 0; i < archers.length; i++) {
			archers[i] = new Node(H, sel[i]);
		}
//		System.out.println(Arrays.toString(archers));
		int[][] copy_map = copy(map);
		ArrayList<Node> copyed = copy_list();
		while (!copyed.isEmpty()) {
			for (int i = 0; i < copyed.size(); i++) {
				Node n = copyed.get(i);
				for (Node arc : archers) {
					Node tar = arc.target;
					int dist = dist(n, arc);

					if (tar == null) {
						if (dist <= D) {
							arc.target = n;
						}
					} else {
						int tar_dist = dist(arc, tar);
						if (dist < tar_dist) {
							// 내거리가 젤 짧나?
							arc.target = n;
						} else if (dist == tar_dist) {
							if (tar.col > n.col) {
								arc.target = n;
							}
						}
					}
				}
			}

			ArrayList<Node> remove = new ArrayList<>();
			for (Node node : archers) {
				Node n = node.target;
				node.target = null;
				if (n != null && copy_map[n.row][n.col] == 1 && n != null) {
					cnt++;
					copy_map[n.row][n.col] = 0;
					remove.add(n);
				}
			}
			for (Node node : remove) {
				copyed.remove(node);
			}

			// 내리기

			for (int i = copyed.size() - 1; i >= 0; i--) {
				Node node = copyed.get(i);
				copy_map[node.row][node.col] = 0;
				node.row++;
				if (node.row == H) {
					copyed.remove(node);
				} else {
					copy_map[node.row][node.col] = 1;
				}
			}
		}

		return cnt;
	}
}
