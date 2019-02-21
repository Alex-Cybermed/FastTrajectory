package application;

import java.util.ArrayList;

public class ForwardAStar {
	private BiHeap<GridNode> openList = new BiHeap<GridNode>();
	private DoubleLL<GridNode> closeList = new DoubleLL<GridNode>();
	private int counter = 0;
	private int cost = 1;
	static Integer Inf = Integer.MAX_VALUE;

	public void forwardA(GridNode[][] gridMap) {
		GridNode A = new GridNode(0, 0, null);
		GridNode T = new GridNode(0, 0, null);
		// traverse whole map initial search as 0 and find out A and T
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
		// set whole map with consistent h-value
		setHvalue(gridMap, T);
		// tree pointer point to current GridNode
		GridNode ptr = new GridNode(0, 0, null);
		ptr = A;
		while (ptr != T) {
			counter = counter + 1;
			ptr.setG(0);
			ptr.setSearch(counter);
			T.setG(Inf);
			T.setSearch(counter);
			openList.makeEmpty();
			closeList.reset();
			ptr.setF(ptr.getG() + ptr.getH());
			openList.insert(ptr);
			ComputerPath(gridMap, A, T);
			if (openList.isEmpty()) {
				System.out.println("Fail");
				break;
			}
			while(closeList.)

		}

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode A, GridNode T) {
		while (T.getG() > openList.findMin().getF()) {
			GridNode s = openList.findMin();
			closeList.addLast(openList.deleteMin());
			for (GridNode n : ADJ(gridMap, s)) {
				if (n.getSearch() < counter) {
					n.setG(Inf);
					n.setSearch(counter);
				}
				if (n.getG() > (s.getG() + cost)) {
					n.setG(s.getG() + cost);
					n.setParent(s);
					if (openList.contains(n)) {
						openList.remove(n);
					} else {
						n.setF(n.getG() + n.getH());
						openList.insert(n);
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

		if (up.getX() > gridMap.length || up.getY() > gridMap.length || up.getX() < 0 || up.getY() < 0) {
			// do not append
		} else if (down.getX() > gridMap.length || down.getY() > gridMap.length || down.getX() < 0 || down.getY() < 0) {
			// do not append
		} else if (left.getX() > gridMap.length || left.getY() > gridMap.length || left.getX() < 0 || left.getY() < 0) {
			// do not append
		} else if (right.getX() > gridMap.length || right.getY() > gridMap.length || right.getX() < 0
				|| right.getY() < 0) {
			// do not append
		} else {
			adjacent.add(curr);
		}
		return adjacent;
	}
}
