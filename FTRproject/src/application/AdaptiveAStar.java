package application;

import java.util.ArrayList;

public class AdaptiveAStar {

	public BH<GridNode> openList = new BH<GridNode>();
	public DoubleLL<GridNode> closeList = new DoubleLL<GridNode>();
	public int counter = 0;
	public int cost = 1;
	public Integer Inf = Integer.MAX_VALUE;

	public void Forward(GridNode[][] gridMap) {
		GridNode A = new GridNode(0, 0, null);
		GridNode T = new GridNode(0, 0, null);
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				gridMap[i][j].setnID(i * gridMap.length + j);
				gridMap[i][j].setSearch(0);
				if (gridMap[i][j].getStatus().equals("A")) {
					A = gridMap[i][j];
				}
				if (gridMap[i][j].getStatus().equals("T")) {
					T = gridMap[i][j];
				}
			}
		}
		GridNode ptr = new GridNode(0, 0, null);
		ptr = A;
		setHvalue(gridMap, T);
		while (ptr.getnID() != T.getnID()) {
			counter = counter + 1;

			ptr.setG(0);
			ptr.setSearch(counter);
			T.setG(Inf);
			T.setSearch(counter);
			openList.clear();
			closeList.reset();
			ptr.setF(ptr.getG() + ptr.getH());
			openList.insert(ptr);
			setBlocked(ADJ(gridMap, ptr));
			resetVisited(gridMap);
			ComputerPath(gridMap, ptr, T);
//			board.printAIMap(gridMap, ptr, T);
//			System.out.println("counter" + counter);
			if (openList.size() == 0) {
				System.out.println("Fail");
				return;
			}
			ArrayList<GridNode> path = new ArrayList<GridNode>();
			GridNode L = closeList.tail.element;
			while (L != null) {
				path.add(L);
				L = L.getParent();
			}

			for (int pt = path.size() - 1; pt >= 0; pt--) {
				if (path.get(pt).getStatus().equals("X")) {
					break;
				} else {
					ptr = path.get(pt);
				}
			}

			if (ptr.getH() == 1) {
				break;
			}
		}
		System.out.println("Adaptive A* Search success!");

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode ptr, GridNode T) {
		ArrayList<GridNode> list = new ArrayList<GridNode>();
		int ptrh = ptr.getH();
		while (T.getG() > openList.getByIndex(0).getF()) {
			GridNode visited = openList.delete();
			visited.setVisited(true);
			if (visited.getH() > ptrh) {
//				System.out.println("Expanded cell " + visited.getnID() + "'s h=" + visited.getH());
//				System.out.println("A cell " + ptr.getnID() + "'s h=" + ptr.getH());
				visited.setVisited(true);
			}
			closeList.addLast(visited);
			list.add(visited);
			ArrayList<GridNode> adj = ADJ(gridMap, visited);
			for (int i = 0; i < adj.size(); i++) {
				if (adj.get(i).isVisited() || adj.get(i).isBlocked()) {
					if (i == (adj.size() - 1)) {
						if (openList.size() == 0) {
							return;
						}
					}
				} else 
				{
					GridNode action = adj.get(i);
					if (action.getSearch() < counter) {
						action.setG(Inf);
						action.setSearch(counter);
					}
					if (action.getG() > (visited.getG() + cost)) 
					{
						action.setG(visited.getG() + cost);
						action.setParent(visited);
						if (listContains(openList, action)) {
							openList.remove(action);
						} else {
							action.setF(action.getG() + action.getH());
							openList.insert(action);
						}
					}
				}
			}
			}
			
		for (int i = 0; i < list.size(); i++) {
			
			GridNode n = list.get(i);
			n.setH(T.getG() - n.getG());
			
		}
//		closeList.iterateForward();

	}

	public void setBlocked(ArrayList<GridNode> adj) {
		for (int i = 0; i < adj.size(); i++) {
			if (adj.get(i).getStatus().equals("X")) {
				adj.get(i).setBlocked(true);
			}
		}
	}

	public void resetVisited(GridNode[][] gridMap) {
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				gridMap[i][j].setVisited(false);
				gridMap[i][j].setParent(null);
			}
		}
	}

	public boolean listContains(BH<GridNode> list, GridNode obj) {
		if (list.size() == 0) {
			return false;
		}
		int j;
		for (j = 0; j < list.size(); j++) {
			if (list.getByIndex(j).getnID() == obj.getnID()) {
				return true;
			}
		}
		return false;
	}

	public void setHvalue(GridNode[][] gridMap, GridNode Target) {
		int rowNum, colNum;
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				gridMap[i][j].setBlocked(false);
				rowNum = Math.abs(Target.getX() - i);
				colNum = Math.abs(Target.getY() - j);
				gridMap[i][j].setH(rowNum + colNum);
			}
		}
	}

	public ArrayList<GridNode> ADJ(GridNode[][] gridMap, GridNode curr) {
		ArrayList<GridNode> adjacent = new ArrayList<GridNode>();
		int x = gridMap.length;
		if (curr.getX() + 1 < x && !curr.isBlocked()) {
			adjacent.add(gridMap[curr.getX() + 1][curr.getY()]);
		}
		if (curr.getX() - 1 >= 0 && !curr.isBlocked()) {
			adjacent.add(gridMap[curr.getX() - 1][curr.getY()]);
		}
		if (curr.getY() - 1 >= 0 && !curr.isBlocked()) {
			adjacent.add(gridMap[curr.getX()][curr.getY() - 1]);
		}
		if (curr.getY() + 1 < x && !curr.isBlocked()) {
			adjacent.add(gridMap[curr.getX()][curr.getY() + 1]);
		}
		return adjacent;
	}

	public static void p(String s) {
		System.out.println(s);
	}
}
