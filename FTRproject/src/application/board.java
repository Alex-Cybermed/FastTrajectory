package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class board {
	
	static int x = 10;
	static int testnum = 10;

	public GridNode[][] initial() {
		GridNode[][] gridBoard = new GridNode[x][x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				GridNode n = new GridNode(i, j, ' ');
				// random blocked 30% gridNode
				if (Math.random() <= 0.3) {
					n.setStatus('X');
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
				if (gridBoard[i][j].getStatus() == 'A') {
					System.out.printf("%c ", 'A');
				} else if (gridBoard[i][j].getStatus() == 'T') {
					System.out.printf("%c ", 'T');
				} else if (gridBoard[i][j].getStatus() == 'X') {
					System.out.printf("%c ", 'X');
				} else if (gridBoard[i][j].getStatus() == ' ') {
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
				System.out.println(s);
				PrintWriter fileWriter = new PrintWriter(s, "UTF-8");
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
		} catch (IOException ex) {
			System.out.println("IOException");
		}
	}

	public static void main(String[] args) throws IOException {
		board bd = new board();
//		GridNode[][] gridBoard = initial();
//		printMap(gridBoard);
		// System.out.println(A.getX()+", "+ A.getY());
		// System.out.println(T.getX()+", "+ T.getY());
		int i;
		for (i = 0; i < testnum; i++) {
			GridNode[][] gridBoard = bd.initial();
			bd.printMap(gridBoard);
			bd.FileOut(gridBoard, i);
		}
	}

}
