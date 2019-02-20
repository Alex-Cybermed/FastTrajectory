package application;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ForwardAStar {
	private PriorityQueue<GridNode> oL = new PriorityQueue<GridNode>();
	private PriorityQueue<GridNode> cL = new PriorityQueue<GridNode>();
	private int counter = 0;
//	private int h = 0;
//	private int g = 0;
//	private int f = 0;
	private int cost = 1;
	static Integer Inf = Integer.MAX_VALUE;

	public void forwardA(GridNode[][] gridMap) {
		GridNode A = new GridNode(0, 0, null);
		GridNode T = new GridNode(0, 0, null);
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				gridMap[i][j].setSearch(0);
				if (gridMap[i][j].getStatus().equals("A")) {
					A = gridMap[i][j];
				}
				if (gridMap[i][j].getStatus().equals("T")) {
					T = gridMap[i][j];
				}
			}
		}
		setHvalue(gridMap, T);
		GridNode ptr = new GridNode(0, 0, null);
		ptr = A;
		while (ptr != T) {
			counter = counter++;
			ptr.setG(0);
			ptr.setSearch(counter);
			T.setG(Inf);
			T.setSearch(counter);
			oL.clear();
			cL.clear();
			ptr.setF(ptr.getG() + ptr.getH());
			oL.add(ptr);
			ComputerPath(gridMap, A, T);
			if(oL.isEmpty()) {
				System.out.println("Fail");
				break;
			}
		}

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode A, GridNode T) {
		while (T.getG() > oL.peek().getF()) {
			GridNode s = oL.peek();
			cL.add(oL.remove());
			for(GridNode n:ADJ(gridMap,s)){
				if(n.getSearch()<counter) {
					n.setG(Inf);
					n.setSearch(counter);
				}
				if(n.getG()>(s.getG()+cost)){
					n.setG(s.getG()+cost);
					n.setParent(s);
					if(oL.contains(n)) {
						oL.remove(n);
					}else {
						n.setF(n.getG()+n.getH());
						oL.add(n);
					}
				}
			}
		}
	}

	public void setHvalue(GridNode[][] gridMap, GridNode Target) {
		int rowNum, colNum;
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				rowNum = Math.abs(Target.getX() - i);
				colNum = Math.abs(Target.getY() - j);
				gridMap[i][j].setH(rowNum + colNum);
			}
		}
	}

	public ArrayList<GridNode> ADJ(GridNode[][] gridMap, GridNode curr) {
		GridNode up = gridMap[curr.getX() + 1][curr.getY()];
		GridNode down = gridMap[curr.getX() - 1][curr.getY()];
		GridNode left = gridMap[curr.getX()][curr.getY() - 1];
		GridNode right = gridMap[curr.getX()][curr.getY() + 1];
		ArrayList<GridNode> adjacent = new ArrayList<GridNode>();

		if (up.getX() > gridMap.length || up.getY() > gridMap.length || up.getX() < 0 || up.getY() < 0
				|| up.getStatus().equals("X")) {
			//do not append
		}else if (down.getX() > gridMap.length || down.getY() > gridMap.length || down.getX() < 0 || down.getY() < 0
				|| down.getStatus().equals("X")) {
			//do not append
		}else if (left.getX() > gridMap.length || left.getY() > gridMap.length || left.getX() < 0 || left.getY() < 0
				|| left.getStatus().equals("X")) {
			//do not append
		}else if (right.getX() > gridMap.length || right.getY() > gridMap.length || right.getX() < 0 || right.getY() < 0
				|| right.getStatus().equals("X")) {
			//do not append
		}else {
			adjacent.add(curr);
		}
		return adjacent;
	}
}
