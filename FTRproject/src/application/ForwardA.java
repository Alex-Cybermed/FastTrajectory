package application;

import java.awt.List;
import java.util.PriorityQueue;

public class ForwardA {
	static Integer myInf = Integer.MAX_VALUE;
	public static void main(String[] args) {
		board b = new board();
		GridNode[][] g = b.initial();
		Procedure(g);
	}

	public static void Procedure(GridNode[][] gridBoard) {
		
		int counter = 0;
		GridNode start = new GridNode(0, 0, null);
		GridNode goal = new GridNode(0, 0, null);
		for (int i = 0; i < gridBoard.length; i++) {
			for (int j = 0; j < gridBoard.length; j++) {
				gridBoard[i][j].setSearch(0);
				if (gridBoard[i][j].getStatus().equals("A")) {
					start = gridBoard[i][j];
				} else if (gridBoard[i][j].getStatus().equals("T")) {
					goal = gridBoard[i][j];
				}
			}
		}
		GridNode ptr = start;
		while (ptr != goal) {
			counter++;
			ptr.setG(0);
			ptr.setSearch(counter);
			goal.setG(myInf);
			goal.setSearch(counter);
			PriorityQueue<GridNode> openlist = new PriorityQueue<GridNode>();
			PriorityQueue<GridNode> closedlist = new PriorityQueue<GridNode>();
			ptr.setF(ptr.getG()+ptr.getH());
			openlist.add(ptr);
			ComputePath(ptr);
			if(openlist.isEmpty()) {
				System.out.println("I cannot reach the target.");
			}
			
			

		}

	

	}

	public static void ComputePath(GridNode current) {
//		while

	}
}
