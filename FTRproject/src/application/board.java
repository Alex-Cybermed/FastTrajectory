package application;



import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class board {

	static int x = 101;
	static int numOfMap = 50;

	public GridNode[][] initial() {
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
		GridNode A = randomAT(gridBoard);
		A.setStatus("A");
		GridNode T = randomAT(gridBoard);
		T.setStatus("T");
		return gridBoard;
	}

	public GridNode randomAT(GridNode[][] gridBoard) {
		Random r = new Random();
		int numX = r.nextInt(x);
		int numY = r.nextInt(x);
		return gridBoard[numX][numY];
	}

	public void printMap(GridNode[][] gridBoard) {
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
		System.out.println();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				if (gridBoard[i][j].getStatus() == "A") {
					System.out.printf("%c ", 'A');
				} else if (gridBoard[i][j].getStatus() == "T") {
					System.out.printf("%c ", 'T');
				} else if (gridBoard[i][j].getStatus() == "X") {
					System.out.printf("%c ", 'X');
				} else if (gridBoard[i][j].getStatus() == " ") {
					System.out.printf("%c ", ' ');
				}
			}
			System.out.println();
		}
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
	}

	public void FileOut(GridNode[][] gridBoard, int k) throws IOException {
		try {
			String s = "src/TestCases/Test" + k + ".txt";
			System.out.println("File Test" + k + ".txt successfully created!");
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
					//print node index
//					fileWriter.write(i + " " + j + " ");
//					//print node status
					fileWriter.write(gridBoard[i][j].getStatus()+" ");
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

	public GridNode[][] fileIn(String address) throws IOException {
		GridNode[][] testCase = new GridNode[x][x];
		try {
			FileReader fr = new FileReader(address);
			for(int i=0;i<x;i++) {
				for(int j=0;j<x;j++) {
					testCase[i][j] = new GridNode(i,j,fr.toString());
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return testCase;
	}

	public static void main(String[] args) throws IOException {
		board bd = new board();
		int i;
		for (i = 1; i <= numOfMap; i++) {
			GridNode[][] gridBoard = bd.initial();
			bd.FileOut(gridBoard, i);
		}
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println("Input the number of map you want to test:" + n);
		String fileAddress = "src/TestCases/Test" + n + ".txt";
		bd.fileIn(fileAddress);
		sc.close();
	}

}
