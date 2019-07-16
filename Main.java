import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Node {
		int r, c, s, d, z;

		public Node(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s; // 속도
			this.d = d; // 방향
			this.z = z; // 크기

		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]\n";
		}
		
	}

	static List<Node> fish;
	static int R,C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		int M = sc.nextInt();
		int arr[][] = new int[R][C];
		fish = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			fish.add(new Node(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), sc.nextInt(), sc.nextInt()));
		}
		//지도에 표시하기
		for (int i = 0; i < fish.size(); i++) {
			arr[fish.get(i).r][fish.get(i).c] = fish.get(i).z;
		}
		sum = 0;
		solve(arr);
		System.out.println(sum);

	}
	//방향이 1부터 시작 상 하 우 좌
	static int[][] dxy = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 } , { 0, -1 }};

	static void solve(int[][] arr) {
		for(int i=0;i<arr[0].length;i++) {
			kill(arr, i); //낚시를 하고
			for (int[] is : arr) {
				System.out.println(Arrays.toString(is));
			}
			System.out.println(fish);
			move(arr); //물고기가 움직인다.
			
		}
	}
	static void move(int[][] arr) { //물고기 움직이기
		ArrayList<Node> remove = new ArrayList<>(); 
		for(int i=0;i<fish.size();i++) {
			int x = fish.get(i).r;
			int y = fish.get(i).c;
			int speed = fish.get(i).s;
			int dir = fish.get(i).d;
			int size = fish.get(i).z;
			arr[x][y] = 0;
			for(int s =0;s<speed;s++) {
				x+=dxy[dir][0];
				y+=dxy[dir][1];
				if(!isRange(x, y)) { //범위가 넘어갔으면
					x-=dxy[dir][0];
					y-=dxy[dir][1];
					dir = reverse(dir); //방향반대로
					x+=dxy[dir][0];
					y+=dxy[dir][1];
					
				}
			}
			fish.set(i, new Node(x,y,speed,dir,size)); //새롭게 넣어주기
			if(arr[x][y]>0) {
				if(arr[x][y] < size) {
					for (int k = 0; k < fish.size(); k++) {
						if (fish.get(k).z == arr[x][y]) {
							remove.add(fish.get(k));
                            break;
						}
					}
					arr[x][y] = size;
				}else {
					remove.add(fish.get(i));
				}
			}else {
				arr[x][y] = size;
			}
		}
		for (Node f : remove) {
			fish.remove(f);
		}
	}
	static int reverse(int dir) {
		switch(dir){
		case 1 :
			return 2;
		case 2 : 
			return 1;
		case 3 : 
			return 4;
		case 4 :
			return 3;
		}
		return 0;
	}
	static boolean isRange(int x, int y) {
		return x>=0&& y>=0&&x<R&&y<C;
	}

	static int sum;

	static void kill(int[][] arr, int idx) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][idx] > 0) {
				sum += arr[i][idx];
				for (int k = 0; k < fish.size(); k++) {
					if (fish.get(k).z == arr[i][idx]) {
						System.out.println("kill"+fish.get(k));
						fish.remove(k);
                        arr[i][idx] = 0;
                        return;
					}
				}
			}
		}
	}

}
