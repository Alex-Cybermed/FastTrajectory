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
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				gridMap[i][j].setnID(i*gridMap.length+j);
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
		while (ptr.getnID() != T.getnID()) {
			System.out.println("++++++++++++++++_____________++++++++++++++++++");
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
			setBlocked(ADJ(gridMap,ptr));
			ComputerPath(gridMap, ptr, T);
			System.out.print("Before check empty:");
			openList.printHeap();
			if (openList.size()==0) {
				System.out.print("After check empty:");			
				openList.printHeap();
				closeList.iterateForward();
				System.out.println("Fail");
				return;
			}
			//wonderland loop
			DoubleLL<GridNode>.Node n;
			for (n = closeList.head; !n.next.element.getStatus().equals("X"); n = n.next) {
				ptr = n.element;
			}

		}
		System.out.println("success");

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode ptr, GridNode T) {
		
		while (T.getG() > openList.findMin().getF()) {
			//openList.printHeap();
			GridNode visited =openList.deleteMin();
			visited.setVisited(true);
			closeList.addLast(visited);
			ArrayList<GridNode> adj = ADJ(gridMap, visited);
			for (int i = 0;i<adj.size();i++) {
				if(adj.get(i).isVisited() || adj.get(i).isBlocked()) {
					
				}else {
				GridNode action = adj.get(i);
				if (action.getSearch() < counter) {
					action.setG(Inf);
					action.setSearch(counter);
				}
				if (action.getG() > (visited.getG() + cost)) {
					action.setG(visited.getG() + cost);
					action.setParent(visited);
					System.out.println("action:"+action.getX()+""+action.getY()+" "+"ptr:"+visited.getX()+""+visited.getY());
					if (listContains(openList,action)) {
						openList.remove(action);
						System.out.println("Deleting "+ action.getX()+action.getY());
					} else {
						action.setF(action.getG() + action.getH());
						openList.insert(action);
						System.out.println("==============================");
						System.out.print("OpenList:");
						openList.printHeap();
						System.out.print("ClosedList:");
						closeList.iterateForward();
						System.out.println("==============================");

					}
				}
				}
			}
		}
	}
	
	public void setBlocked(ArrayList<GridNode> adj) {
		for(int i = 0; i<adj.size();i++) {
			if(adj.get(i).getStatus().equals("X")) {
				adj.get(i).setBlocked(true);
			}else {
				//nothing~~~~~
			}
		}
	}
	
	public boolean listContains(BiHeap<GridNode> list, GridNode obj) {
		if(list.size()==0) {
			return false;
		}
		int j;
		for(j=0; j<list.size();j++) {
//			System.out.println("j:"+j+" "+"size:"+list.size());
//			System.out.println(list.getI(j).getnID()+" "+obj.getnID());
			if(list.getI(j).getnID()==obj.getnID()) {
				System.out.println("i am in!");
				return true;}
		}
		return false;
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
