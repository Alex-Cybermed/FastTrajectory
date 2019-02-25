package application;
//new

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class board {

	static int x = 101;
	static int numOfMap = 10;

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
		randomAT(gridBoard,"A").setStatus("A");
		randomAT(gridBoard,"T").setStatus("T");
		return gridBoard;
	}

	public static GridNode randomAT(GridNode[][] gridBoard, String AT) {
		Random r = new Random();
		int row = r.nextInt(x);
		int col = r.nextInt(x);
		if(!gridBoard[row][col].getStatus().equals("X")) {
			if(gridBoard[row][col].getStatus().equals("A")) {
				return randomAT(gridBoard,"A");
			}else if(gridBoard[row][col].getStatus().equals("T")) {
				return randomAT(gridBoard,"T");
			}else {
				return gridBoard[row][col];
			}
		}
		return randomAT(gridBoard,AT);
	}

	public static void printMap(GridNode[][] gridBoard) {
		for (int k = 0; k < x; k++) {
			System.out.print(k+" ");
//			System.out.print("- ");
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
	public static  void printAIMap(GridNode[][] gridBoard, GridNode A) {
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
		System.out.println();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				if (gridBoard[i][j].getnID()==A.getnID()) {
					System.out.printf("A ");
				} else if (gridBoard[i][j].getStatus().equals("T")) {
					System.out.printf("T ");
				} else if (gridBoard[i][j].isBlocked()) {
					System.out.printf("X ");
				} else if (!gridBoard[i][j].isBlocked()) {
					System.out.printf("_ ");
//					System.out.print(gridBoard[i][j].getF()+" ");
				}
			}
//			System.out.print(i);
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
//			System.out.println("File Test" + k + ".txt successfully created!");
			PrintWriter fileWriter = new PrintWriter(s, "UTF-8");
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
//					if ([i][j].getStatus() == 'A') {
//						String msg = gridBoard[i][j].getStatus() + " ";
//						fileWriter.write(msg);
//					} else if (gridBoard[i][j].getStatus() == 'T') {
//						String msg = gridBoard[i][j].getStatus() + " ";
//						fileWriter.write(msg);
//					} else {
					// print node index
//					fileWriter.write(i + " " + j + " ");
//					//print node status
					fileWriter.write(gridBoard[i][j].getStatus() + " ");
//					//print visited status
//					if (!gridBoard[i][j].isVisited()) {
//						fileWriter.write("Unvisit ");
//					} else {
//						fileWriter.write("Visited ");
//					}
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
//					System.out.println(status);
					testCase[i][j] = new GridNode(i, j, status);
//					System.out.print(testCase[i][j].getStatus());
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		printMap(testCase);
		return testCase;
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		ForwardAStar fa = new ForwardAStar();
//		/*
//		Generate new maps
//		*/
//		for (int i = 1; i <= numOfMap; i++) {
//			GridNode[][] gridBoard = initial();
//			FileOut(gridBoard, i);
//		}
		
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		int n = 103;
//		System.out.println("Input the number of map you want to test:" + n);
		String fileAddress = "src/TestCases/Test" + n + ".txt";
		GridNode[][] out = fileIn(fileAddress);
		fa.forwardA(out);
//		printAIMap(out);
//		System.out.println(out.length);
//		sc.close();
//		long startTime = System.nanoTime();
//		.....your program....
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("total time:"+totalTime);
	}


}
