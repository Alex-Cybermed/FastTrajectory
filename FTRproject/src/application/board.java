package application;

public class board {
	public static void main(String[] args) {
		GridNode[][] board = initial();
		printGridPane(board);
		
	}

	public static GridNode[][] initial() {
		int x = 5;
		GridNode[][] board = new GridNode[x][x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				GridNode n = new GridNode(i, j, false, false);
				//random blocked 30% gridNode
				if(Math.random()<0.3) {
					n.setBlocked(true);
				}
				board[i][j] = n;
				
			}
		}
		return board;	
	}
	public static void printGridPane(GridNode[][] board ) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				System.out.printf("%d%d%s", board[i][j].getX(), board[i][j].getY(),board[i][j].isBlocked());
				System.out.print(" ");
				
			}
			System.out.println();
		}
		System.out.println();
	}
	

	/*
	 * public static int[][] initalizeBoard() { int[][] board = new int[5][5];
	 * 
	 * return board;
	 * 
	 * }
	 * 
	 * public static void printBoard(int[][]board) { for (int i = 0; i < 5; i++) {
	 * for (int j = 0; j < 5; j++) {
	 * 
	 * System.out.printf("%d%d", i, j); System.out.print(" "); }
	 * System.out.println(); } System.out.println(); }
	 * 
	 * public static void main(String[] args) { int[][] board = initalizeBoard();
	 * printBoard(board); }
	 */
}
