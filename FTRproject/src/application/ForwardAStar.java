package application;

import java.util.ArrayList;

import application.DoubleLL;

public class ForwardAStar {
	public BiHeap<GridNode> openList = new BiHeap<GridNode>();
	public DoubleLL<GridNode> closeList = new DoubleLL<GridNode>();
	public int counter = 0;
	public int cost = 1;
	public Integer Inf = Integer.MAX_VALUE;

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
//			openList.printHeap();
			ComputerPath(gridMap, A, T, openList);
			if (openList.isEmpty()) {
				System.out.println("Fail");
				return;
			}
			DoubleLL<GridNode>.Node n;
			for (n = closeList.head; !n.next.element.getStatus().equals("X"); n = n.next) {
				ptr = n.element;
			}

		}
		System.out.println("success");

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode A, GridNode T, BiHeap<GridNode> ol) {
		while (T.getG() > ol.findMin().getF()) {
			GridNode s = ol.findMin();
			closeList.addLast(ol.deleteMin());
			ArrayList<GridNode> adj = ADJ(gridMap, s);
			for (int i = 0;i<adj.size();i++) {
				GridNode n = adj.get(i);
				if (n.getSearch() < counter) {
					n.setG(Inf);
					n.setSearch(counter);
				}
				if (n.getG() > (s.getG() + cost)) {
					n.setG(s.getG() + cost);
					n.setParent(s);
					if (ol.contains(n)) {
						ol.remove(n);
					} else {
						n.setF(n.getG() + n.getH());
						ol.insert(n);
						ol.printHeap();
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
		ArrayList<GridNode> adjacent = new ArrayList<GridNode>();
		int x=gridMap.length;
		if(curr.getX()+1<x ) {
			adjacent.add(gridMap[curr.getX() + 1][curr.getY()]);
		}
		if(curr.getX()-1>=0 ) {
			adjacent.add(gridMap[curr.getX() - 1][curr.getY()]);
		}
		if(curr.getY()-1>=0) {
			adjacent.add(gridMap[curr.getX()][curr.getY()-1]);
		}
		if(curr.getY()+1<x) {
			adjacent.add(gridMap[curr.getX()][curr.getY()+1]);
		}
		return adjacent;
	}
}
