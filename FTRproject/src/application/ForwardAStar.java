package application;

import java.util.ArrayList;

import application.DoubleLL;

public class ForwardAStar {
	public BH<GridNode> openList = new BH<GridNode>();
	public DoubleLL<GridNode> closeList = new DoubleLL<GridNode>();
	public int counter = 0;
	public int cost = 1;
	public Integer Inf = Integer.MAX_VALUE;

	public void forwardA(GridNode[][] gridMap) {
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
		setHvalue(gridMap,T);
		while (ptr.getnID() != T.getnID()) {
			p("++++++++++++++++_____________++++++++++++++++++");
			counter = counter + 1;
			ptr.setG(0);
			ptr.setSearch(counter);
			T.setG(Inf);
			T.setSearch(counter);
			openList.clear();
			closeList.reset();
			ptr.setF(ptr.getG() + ptr.getH());
			openList.insert(ptr);
			// openList.printHeap();
			setBlocked(ADJ(gridMap, ptr));
			ComputerPath(gridMap, ptr, T);
			//board.printAIMap(gridMap);
			p("Before check empty:");
//			openList.printHeap();
			if (openList.size() == 0) {
				p("After check empty:");
//				openList.printHeap();
				closeList.iterateForward();
				p("Fail");
				return;
			}
			// wonderland loop
			DoubleLL<GridNode>.Node n;
			for (n = closeList.head; !n.next.element.getStatus().equals("X"); n = n.next) {
				setBlocked(ADJ(gridMap, ptr));
				ptr = n.element;
				p("========wonderland=========");
				board.printAIMap(gridMap, ptr);
				p("========wonderland END=====");

			}

		}
		p("success");

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode ptr, GridNode T) {
		board.printAIMap(gridMap, ptr);
		p("");
		while (T.getG() > openList.getByIndex(0).getF()) {
			p("Print Heap at the beginning of ComputerPath: ");
			openList.printHeap();
			p("IN WHILE LOOP");
			board.printAIMap(gridMap, ptr);
			// openList.printHeap();
			GridNode visited = openList.delete();
			visited.setVisited(true);
			closeList.addLast(visited);
			ArrayList<GridNode> adj = ADJ(gridMap, visited);
			for(int w = 0; w<adj.size();w++) {
				System.out.print(adj.get(w).getnID()+" ");
			}
			p("");
			for (int i = 0; i < adj.size(); i++) {
				if (adj.get(i).isVisited() || adj.get(i).isBlocked()) {

				} else {
					GridNode action = adj.get(i);
					if (action.getSearch() < counter) {
						action.setG(Inf);
						action.setSearch(counter);
					}
					if (action.getG() > (visited.getG() + cost)) {
						action.setG(visited.getG() + cost);
						action.setParent(visited);
						p("action:" + action.getX() + "" + action.getY() + " " + "ptr:"
								+ visited.getX() + "" + visited.getY());
						if (listContains(openList, action)) {
							openList.remove(action);
							p("Deleting " + action.getX() + action.getY());
						} else {
							action.setF(action.getG() + action.getH());
							openList.insert(action);
							p("==============================");
							System.out.print("OpenList:");
//							openList.printHeap();
							System.out.print("ClosedList:");
							closeList.iterateForward();
							p("==============================");

						}
					}
				}
			}
		}
	}

	public void setBlocked(ArrayList<GridNode> adj) {
		for (int i = 0; i < adj.size(); i++) {
			if (adj.get(i).getStatus().equals("X")) {
				adj.get(i).setBlocked(true);
			}
		}
	}

	public boolean listContains(BH<GridNode> list, GridNode obj) {
		if (list.size() == 0) {
			return false;
		}
		int j;
		for (j = 0; j < list.size(); j++) {
			// p("j:"+j+" "+"size:"+list.size());
			// p(list.getI(j).getnID()+" "+obj.getnID());
			if (list.getByIndex(j).getnID() == obj.getnID()) {
				p("i am in!");
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
