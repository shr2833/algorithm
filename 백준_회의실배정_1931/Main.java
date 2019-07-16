package 백준_회의실배정_1931;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node> {
		long start, end;

		@Override
		public int compareTo(Node o) {
			if (end == o.end) {
				return Long.compare(start, o.start);
			}
			return Long.compare(end, o.end);
		}

		public Node(long start, long end) {
			this.start = start;
			this.end = end;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner("2\r\n" + "\r\n" + "2 2\r\n" + "\r\n" + "1 2");

		int result = 0;
		int N = sc.nextInt();
		List<Node> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			long s = sc.nextLong();
			long e = sc.nextLong();

			list.add(new Node(s, e));
		}

//		System.out.println(list);
		Collections.sort(list);
//		System.out.println(list);
		long time = 0;

		for (int i = 0; i < list.size(); i++) {
			Node n = list.get(i);
			if (n.start >= time) {
				time = n.end;
				result++;
			}
		}
		System.out.println(result);

	}
}
