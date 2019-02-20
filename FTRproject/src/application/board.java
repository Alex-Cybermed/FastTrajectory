package application;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;

public class board {

	static int x = 10;
	static int testnum = 10;


	public static void main(String[] args) throws IOException {
//		GridNode[][] gridBoard = initial();
//		printMap(gridBoard);
		// System.out.println(A.getX()+", "+ A.getY());
		// System.out.println(T.getX()+", "+ T.getY());
		int i;
		for(i=0;i<testnum;i++) {
			GridNode[][] gridBoard = initial();
			printMap(gridBoard);
			FileOut(gridBoard);
		}
//		FileOut(gridBoard);

	}

	public static void FileOut(GridNode[][] gridBoard) throws IOException {
		
		Writer writer = null;
		try {
			for (int k = 0; k < testnum; k++) {
				String s = "src/TestCases/Test" + k + ".txt";
				System.out.println(s);
				PrintWriter fileWriter = new PrintWriter(s, "UTF-8");
				//writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(s), "utf-8"));
				// write line by line
				for (int i = 0; i < x; i++) {
					for (int j = 0; j < x; j++) {
						if (gridBoard[i][j].getStatus() == 'A') {
							String msg = i + " " + j + " " + " " + gridBoard[i][j].getStatus() + "\n";
							fileWriter.write(msg);
						} else if (gridBoard[i][j].getStatus() == 'T') {
							String msg = i + " " + j + " " + " " + gridBoard[i][j].getStatus() + "\n";
							fileWriter.write(msg);
						} else {
							// normal node
							String msg = i + " " + j + " " + " " + gridBoard[i][j].getStatus() + "\n";
							fileWriter.write(msg);
						}
					}
				}
				fileWriter.close();
			}
		} catch (IOException ex) {
			System.out.println("IOException");
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}
	}

	public static void printMap(GridNode[][] gridBoard) {
		for (int k = 0; k < x; k++) {
			System.out.print("--");

		}
		System.out.println();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {

				if (gridBoard[i][j].getStatus() == 'A') {
					System.out.printf("%c ", 'A');
				} else if (gridBoard[i][j].getStatus() == 'T') {
					System.out.printf("%c ", 'T');
				} else if (gridBoard[i][j].getStatus()=='X') {
					System.out.printf("%c ", 'X');
				} else if (gridBoard[i][j].getStatus()==' ') {
					System.out.printf("%c ", ' ');
				}
			}
			System.out.println();
		}

		for (int k = 0; k < x; k++) {
			System.out.print("--");

		}
	}

	public static GridNode randomAT(GridNode[][] gridBoard) {
		Random r = new Random();
		int numX = r.nextInt(x);
		int numY = r.nextInt(x);
		return gridBoard[numX][numY];

	}

	public static GridNode[][] initial() {

		GridNode[][] gridBoard = new GridNode[x][x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				GridNode n = new GridNode(i, j,' ');
				// random blocked 30% gridNode
				if (Math.random() <= 0.3) {
					n.setStatus('X');;
				}
				gridBoard[i][j] = n;

			}
		}
		GridNode A = randomAT(gridBoard);
		A.setStatus('A');
		GridNode T = randomAT(gridBoard);
		T.setStatus('T');
		return gridBoard;
	}

	public static void printGridPane(GridNode[][] gridBoard) {
		for (int i = 0; i < gridBoard.length; i++) {
			for (int j = 0; j < gridBoard.length; j++) {
				char c = 'f';
				if (gridBoard[i][j].isBlocked() == true) {
					c = 't';
				}
				System.out.printf("%d%d%c", gridBoard[i][j].getX(), gridBoard[i][j].getY(), c);
				System.out.print(" ");

			}
			System.out.println();
		}
		System.out.println();
	}

}
