package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class board {

	static int x = 5;
	static int numOfMap = 2;

	static void DFSUtil(GridNode curr, boolean visited[], GridNode[][] map, ArrayList<GridNode> vert) {
		printMap(map);
		visited[curr.getnID()] = true;
		ArrayList<GridNode> adj = ADJ(map, curr, visited);

		for (int i = 0; i < adj.size(); i++) {

			GridNode n = adj.get(i);
			System.out.println("Node: " + n.getnID());
			for (int j = 0; j < vert.size(); j++) {
				if (vert.get(i).getnID() == n.getnID()) {
					vert.remove(i);
				}
			}
			if (!vert.isEmpty()) {
				DFSUtil(n, visited, map, vert);
			}
		}

	}

	static GridNode[][] DFS(GridNode A, GridNode[][] map, ArrayList<GridNode> vert) {
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[x * x];
		for (int i = 0; i < visited.length - 1; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				if (Math.random() <= 0.3) {
					map[i][j].setStatus("X");
				}
			}
		}

		// Call the recursive helper function to print DFS traversal
		DFSUtil(A, visited, map, vert);
		return map;
	}

	public static ArrayList<GridNode> ADJ(GridNode[][] gridMap, GridNode curr, boolean visited[]) {
		ArrayList<GridNode> adjacent = new ArrayList<GridNode>();
		int x = gridMap.length;
		GridNode n = null;
		if (curr.getX() + 1 < x && (!visited[gridMap[curr.getX() + 1][curr.getY()].getnID()])) {
			n = gridMap[curr.getX() + 1][curr.getY()];
			// if (Math.random() <= 0.3) {
			// n.setStatus("X");
			// }
			adjacent.add(n);
			// adjacent.add(gridMap[curr.getX() + 1][curr.getY()]);

		}
		if (curr.getX() - 1 >= 0 && (!visited[gridMap[curr.getX() + 1][curr.getY()].getnID()])) {
			n = gridMap[curr.getX() - 1][curr.getY()];
			// if (Math.random() <= 0.3) {
			// n.setStatus("X");
			// }
			adjacent.add(n);
			// adjacent.add(gridMap[curr.getX() - 1][curr.getY()]);
		}
		if (curr.getY() - 1 >= 0 && (!visited[gridMap[curr.getX() + 1][curr.getY()].getnID()])) {
			n = gridMap[curr.getX()][curr.getY() - 1];
			// if (Math.random() <= 0.3) {
			// n.setStatus("X");
			// }
			adjacent.add(n);
			// adjacent.add(gridMap[curr.getX()][curr.getY() - 1]);
		}
		if (curr.getY() + 1 < x && (!visited[gridMap[curr.getX() + 1][curr.getY()].getnID()])) {
			n = gridMap[curr.getX()][curr.getY() + 1];
			// if (Math.random() <= 0.3) {
			// n.setStatus("X");
			// }
			adjacent.add(n);
			// adjacent.add(gridMap[curr.getX()][curr.getY() + 1]);
		}
		return adjacent;
	}

	// public static GridNode[][] initial() {
	// GridNode[][] gridBoard = new GridNode[x][x];
	// ArrayList<GridNode> vertices = new ArrayList<GridNode>();
	// for (int i = 0; i < x; i++) {
	// for (int j = 0; j < x; j++) {
	// GridNode n = new GridNode(i, j, "_");
	// gridBoard[i][j] = n;
	// gridBoard[i][j].setnID(i*x+j);
	// vertices.add(n);
	// }
	// }
	// // set A first
	// Random r = new Random();
	// GridNode A = gridBoard[r.nextInt(x)][r.nextInt(x)];
	// A.setStatus("A");
	// // DFS
	// gridBoard = DFS(A, gridBoard, vertices);
	// return gridBoard;
	// }

	public static GridNode[][] initial() {
		GridNode[][] gridBoard = new GridNode[x][x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				GridNode n = new GridNode(i, j, "_");
				if (Math.random() <= 0.3) {
					n.setStatus("X");
				}
				gridBoard[i][j] = n;
			}
		}
		randomAT(gridBoard, "A").setStatus("A");
		randomAT(gridBoard, "T").setStatus("T");
		return gridBoard;
	}

	public static GridNode randomAT(GridNode[][] gridBoard, String AT) {
		Random r = new Random();
		int row = r.nextInt(x);
		int col = r.nextInt(x);
		if (!gridBoard[row][col].getStatus().equals("X")) {
			if (gridBoard[row][col].getStatus().equals("A")) {
				return randomAT(gridBoard, "A");
			} else if (gridBoard[row][col].getStatus().equals("T")) {
				return randomAT(gridBoard, "T");
			} else {
				return gridBoard[row][col];
			}
		}
		return randomAT(gridBoard, AT);
	}

	public static void printMap(GridNode[][] gridBoard) {
		for (int k = 0; k < x; k++) {
			System.out.print(k + " ");
			// System.out.print("- ");
		}
		System.out.println();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				if (gridBoard[i][j].getStatus().equals("A")) {
					System.out.printf("A ");
				} else if (gridBoard[i][j].getStatus().equals("T")) {
					System.out.printf("T ");
				} else if (gridBoard[i][j].getStatus().equals("X")) {
					System.out.printf("X ");
				} else if (gridBoard[i][j].getStatus().equals("_")) {
					System.out.printf("_ ");
				}
			}
			System.out.print(i);
			System.out.println();
		}
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
	}

	public static void printAIMap(GridNode[][] gridBoard, GridNode A, GridNode T) {
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
		System.out.println();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				// if (gridBoard[i][j].getnID()==A.getnID()) {
				// System.out.printf("A ");
				// } else if (gridBoard[i][j].getnID()==T.getnID()) {
				// System.out.printf("T ");
				// } else if (gridBoard[i][j].isBlocked()) {
				// System.out.printf("X ");
				// } else if(gridBoard[i][j].getStatus().equals("*")) {
				// System.out.printf("* ");
				//// System.out.print(gridBoard[i][j].getParent().getnID()+" ");
				// } else if (!gridBoard[i][j].isBlocked()) {
				// System.out.printf("_ ");
				//// System.out.print(gridBoard[i][j].getH()+" ");
				// }
				// System.out.print("ID:" + gridBoard[i][j].getnID());
				System.out.print(" h" + gridBoard[i][j].getH() + " ");
				System.out.print("g" + gridBoard[i][j].getG() + "   ");

			}
			// System.out.print(i);
			System.out.println();
		}
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
		System.out.println();
	}

	public static void FileOut(GridNode[][] gridBoard, int k) throws IOException {
		try {
			String s = "src/TestCases/Test" + k + ".txt";
			PrintWriter fileWriter = new PrintWriter(s, "UTF-8");
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
					fileWriter.write(gridBoard[i][j].getStatus() + " ");
				}
				fileWriter.write("\n");
			}
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println("IOException");
		}
	}

	public static GridNode[][] fileIn(String address) throws IOException {
		GridNode[][] testCase = new GridNode[x][x];
		try {
			Scanner fr = new Scanner(new File(address));
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
					String status = fr.next();
					// System.out.println(status);
					testCase[i][j] = new GridNode(i, j, status);
					// System.out.print(testCase[i][j].getStatus());
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return testCase;
	}

	public static void main(String[] args) throws IOException {
		ForwardAStar fa = new ForwardAStar();
		AdaptiveAStar aa = new AdaptiveAStar();
		// BackwardAStar ba = new BackwardAStar();
		// /*
		// Generate new maps
		// */
//		 for (int i = numOfMap; i <= numOfMap; i++) {
//		 GridNode[][] gridBoard = initial();
//		 FileOut(gridBoard, i);
//		 }

		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		int n = numOfMap;
		// System.out.println("Input the number of map you want to test:" + n);
		String fileAddress = "src/TestCases/Test" + n + ".txt";
		GridNode[][] out = fileIn(fileAddress);
		
		long fstime = System.nanoTime();
		fa.Forward(out);
		long fetime = System.nanoTime();
		long fnstime = (fetime - fstime);
		
//		long bstime = System.nanoTime();
//		ba.Backward(out);
//		long betime = System.nanoTime();
//		long bnstime = (betime - bstime);

		long aptime = System.nanoTime();
		aa.Forward(out);
		long apend = System.nanoTime();
		long apstime = (apend - aptime);

		double fsecond = Math.pow(10, -9) * fnstime;
		// double bsecond = Math.pow(10, -9) * bnstime;
		double asecond = Math.pow(10, -9) * apstime;
		
		double diffSec = Math.pow(10, -9) * (fnstime-apstime);

		System.out.println("Forward time:" + fnstime);
		// System.out.println("Backward time:" + bnstime);
		System.out.println("Forward second time:" + fsecond);
		// System.out.println("Backward second time:" + bsecond);
		System.out.println("Adaptive time:" + apstime);
		System.out.println("Adaptive second time: " + asecond);
		System.out.println("shijianchaL: " + diffSec);

	}

}
