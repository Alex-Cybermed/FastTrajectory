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
		setHvalue(gridMap, T);
		while (ptr.getnID() != T.getnID()) {
//			p("===========================================================================================");
//			p("=======================================New Procedure=======================================");
//			p("===========================================================================================");
			counter = counter + 1;
//			resetGvalue(gridMap);
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
			resetVisited(gridMap);
			p("Ptr = "+ ptr.getnID());
			ComputerPath(gridMap, ptr, T);
			// board.printAIMap(gridMap);
//			System.out.print("Before check empty:");
//			openList.printHeap();
			if (openList.size() == 0) {
//				System.out.print("After check empty:");
//				openList.printHeap();
//				closeList.iterateForward();
				System.out.println("Fail");
				return;
			}
			// wonderland loop
//			DoubleLL<GridNode>.Node n = closeList.head;
//			for (n = closeList.head; !n.next.element.getStatus().equals("X"); n = n.next) {
//				setBlocked(ADJ(gridMap, ptr));
//				ptr = n.element;
//				System.out.println("========wonderland=========");
//				board.printAIMap(gridMap, ptr);
//				System.out.println("========wonderland END=====");
//
//			}
//			GridNode m = closeList.tail.element;
//			p("THE PATH:");
//			while (m.getParent() != null) {
//				p(m.getParent().getnID() + "");
//				m = m.getParent();
//			}
//			while(m.prev.element.getParent()!=null) {
//				
//				p("888888 "+m.element.getnID()+" ");
//				m = m.element.getParent();
//			}
//			p("Print wonderland loop XXXXXXXXXXXXXXXXXX");
//			while (n != null) {
//				System.out.println("node: " + n.element.getnID() + " " + " Parent: " + n.element.getParent() + "");
//				n = n.next;
//			}

			ArrayList<GridNode> path = new ArrayList<GridNode>();
			GridNode L = closeList.tail.element;
//			p(path.size()+"");
//			for (int u = 0; u < path.size(); u++) {
//				p(path.get(u) + "");
//			}
			while (L != null) {
//				p(L.getnID()+"");
				path.add(L);
				L = L.getParent();
			}
//			p("++++++======++++++======++++++======++++++======");
			
			for (int pt = path.size() - 1; pt >= 0; pt--) {
				if (path.get(pt).getStatus().equals("X")) {
					break;
				} else {
					ptr = path.get(pt);
				}
			}
			
//			p("");
//			p("=============Beginning of Last Call================");
//			board.printAIMap(gridMap, ptr);
//			p("=========================END=======================");
//			p("");
//			int len = gridMap.length;
//			p("length is "+len);
			if(ptr.getH()==1) {
				break;
			}
		}
		System.out.println("success");

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode ptr, GridNode T) {
//		p("");
//		p("=============Beginning of ComputerPath=============");
//		board.printAIMap(gridMap, ptr);
//		p("=========================END=======================");
//		p("");
		while (T.getG() > openList.getByIndex(0).getF()) {
//			System.out.println("Print Heap at the beginning of ComputerPath: ");
//			openList.printHeap();
//			System.out.println("IN WHILE LOOP");
//			board.printAIMap(gridMap, ptr);
//			// openList.printHeap();
//			openList.printHeap();
//			p(openList.size() + "");
//			p("");
//			p("=============Beginning of While Loop=============");
//			board.printAIMap(gridMap, ptr);
//			p("=======================END=======================");
//			p("");
			GridNode visited = openList.delete();
			visited.setVisited(true);
			closeList.addLast(visited);
			ArrayList<GridNode> adj = ADJ(gridMap, visited);
//			System.out.print("Current Node's neigbhors: ");
//			for (int w = 0; w < adj.size(); w++) {
//				System.out.print(adj.get(w).getnID() + " ");
//			}
//			p("");
//			System.out.print("CloseList: ");
//			closeList.iterateForward();
//			p("");
			for (int i = 0; i < adj.size(); i++) {
				if (adj.get(i).isVisited() || adj.get(i).isBlocked()) {
					if(i==(adj.size()-1)) {
						if(openList.size()==0) {
							return;
						}
					}
				} else {
					GridNode action = adj.get(i);
					if (action.getSearch() < counter) {
						action.setG(Inf);
						action.setSearch(counter);
					}
					if (action.getG() > (visited.getG() + cost)) {
						action.setG(visited.getG() + cost);
						action.setParent(visited);
//						System.out.println("action:" + action.getX() + "" + action.getY() + " " + "ptr:"
//								+ visited.getX() + "" + visited.getY());
						if (listContains(openList, action)) {
							openList.remove(action);
//							System.out.println("Deleting " + action.getX() + action.getY());
						} else {
							action.setF(action.getG() + action.getH());
							openList.insert(action);
//							p("");
//							System.out.println("============End of Path==================");
////							System.out.println("OpenList:");
//							openList.printHeap();
//							System.out.print("ClosedList:");
//							closeList.iterateForward();
//							System.out.println("================End======================");
//							p("");


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
	
//	public void

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
			// System.out.println("j:"+j+" "+"size:"+list.size());
			// System.out.println(list.getI(j).getnID()+" "+obj.getnID());
			if (list.getByIndex(j).getnID() == obj.getnID()) {
//				System.out.println("i am in!");
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
