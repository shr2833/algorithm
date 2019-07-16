package 백준_색종이붙이기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int T, result;
	static class Node{
		int row, col;
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("0 0 0 0 0 0 0 0 0 0\r\n" + 
				"0 1 1 1 1 1 0 0 0 0\r\n" + 
				"0 1 1 1 1 1 0 0 0 0\r\n" + 
				"0 0 1 1 1 1 0 0 0 0\r\n" + 
				"0 0 1 1 1 1 0 0 0 0\r\n" + 
				"0 1 1 1 1 1 1 1 1 1\r\n" + 
				"0 1 1 1 0 1 1 1 1 1\r\n" + 
				"0 1 1 1 0 1 1 1 1 1\r\n" + 
				"0 0 0 0 0 1 1 1 1 1\r\n" + 
				"0 0 0 0 0 1 1 1 1 1");

		int[][] map = new int[10][10];
		int remain_block = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					remain_block++;
				}
			}
		}

		for (int[] is : map) {
			System.out.println(Arrays.toString(is));
		}

		result = 26;
		recur(map, 0, 0, 0, remain_block, new int[] { 0, 5, 5, 5, 5, 5 });
		System.out.println(result == 26 ? -1 : result);
	}

	static void recur(int[][] map, int row, int col, int cnt, int remain_block, int[] remain) {
		if (cnt >= result) {
			return;
		}
		if (remain_block == 0) {
			result = Math.min(result, cnt);
			return;
		}
		if (row == map.length) {
			return;
		}
		if (col >= map[0].length) {
			recur(map, row + 1, 0, cnt, remain_block, remain);
			return;
		}

		if (map[row][col] == 1) {
			for (int size = 1; size <= 5; size++) {
				if (isIn(row, col) && remain[size] > 0 && isEmpty(map, row, col, size)) {
					remain[size]--;
					int[][] copy = copy(map);
					build(copy, row, col, size);
					recur(copy, row, col + size, cnt + 1, remain_block - size * size, remain);
					remain[size]++;
				} else {
					break;
				}
			}
		}
		recur(map, row, col + 1, cnt, remain_block, remain);
	}

	private static void build(int[][] copy, int row, int col, int size) {
		for (int i = 0; i < row + size && i < copy.length; i++) {
			for (int j = 0; j < col + size && j < copy.length; j++) {
				copy[i][j] = 0;
			}
		}
	}

	private static int[][] copy(int[][] map) {
		int[][] copy = new int[map.length][map[0].length];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	private static boolean isEmpty(int[][] map, int row, int col, int size) {
		// row, col에서 size 거리 체크
		size--;
		if (!isIn(row + size, col + size)) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (map[row + i][col + size] == 0) {
				return false;
			}
			if (map[row + size][col + i] == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isIn(int row, int col) {
		return 0 <= row && row < 10 && 0 <= col && col < 10;
	}
}
