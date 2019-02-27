package application;

import java.util.ArrayList;

public class BackwardAStar {
	public BH<GridNode> openList = new BH<GridNode>();
	public DoubleLL<GridNode> closeList = new DoubleLL<GridNode>();
	public int counter = 0;
	public int cost = 1;
	public Integer Inf = Integer.MAX_VALUE;

	public void Backward(GridNode[][] gridMap) {
		GridNode A = new GridNode(0, 0, null);
		GridNode T = new GridNode(0, 0, null);
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				gridMap[i][j].setnID(i * gridMap.length + j);
				gridMap[i][j].setSearch(0);
				gridMap[i][j].setBlocked(false);
				if (gridMap[i][j].getStatus().equals("A")) {
					A = gridMap[i][j];
				}
				if (gridMap[i][j].getStatus().equals("T")) {
					T = gridMap[i][j];
				}
			}
		}
		GridNode ptr = new GridNode(0, 0, null);
		GridNode computerPTR = new GridNode(0, 0, null);
		computerPTR = T;
		ptr = A;
//		setHvalue(gridMap, ptr);
		while (ptr.getnID() != T.getnID()) {
			
			counter = counter + 1;
			computerPTR.setG(0);
			computerPTR.setSearch(counter);
			A.setG(Inf);
			setHvalue(gridMap, ptr);
			A.setSearch(counter);
			openList.clear();
			closeList.reset();
			computerPTR.setF(computerPTR.getG() + computerPTR.getH());
			openList.insert(computerPTR);
			// openList.printHeap();
			ArrayList<GridNode> adj =ADJ(gridMap, ptr);
			setBlocked(adj);
//			sort(adj, 0, adj.size()-1);
//			System.out.println("adj list ="+ adj.toString());
			resetVisited(gridMap);
			ComputerPath(gridMap, computerPTR, A);
			if (openList.size() == 0) {
				System.out.println("Fail");
				return;
			}
			GridNode L = closeList.tail.element;
			while (L != null && !L.getStatus().equals("X")) {
//				p("ptr: "+ptr.getnID());
				ptr = L;
//				ptr.setStatus("*");
				L = L.getParent();
			}
			
//			closeList.iterateForward();
//			GridNode p = closeList.tail.element;
//			while(p!=null) {
//				p(p.getnID()+" "+p.getStatus());
//				p = p.getParent();
//			}


//			p("");
//			p("=============Beginning of Last Call================");
//			board.printAIMap(gridMap, ptr,T);
//			p("=========================END=======================");
//			p("");
//			int len = gridMap.length;
//			p("length is "+len);
			if (ptr.getG() == 0) {
				board.printAIMap(gridMap, ptr, T);
				break;
			}
//			p(ptr.getnID()+"");
//			break;
		}
		System.out.println("Backward A* Search success!");

	}

	public void ComputerPath(GridNode[][] gridMap, GridNode ptr, GridNode Target) {
//		p("");
//		p("=============Beginning of ComputerPath=============");
//		board.printAIMap(gridMap, ptr);
//		p("=========================END=======================");
//		p("");
		while (Target.getG() > openList.getByIndex(0).getF()) {
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
			board.printAIMap(gridMap, ptr, ptr);
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
					if (i == (adj.size() - 1)) {
						if (openList.size() == 0) {
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
//		GridNode parent = new GridNode(-1, "_");
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
//				gridMap[i][j].setBlocked(false);
				rowNum = Math.abs(Target.getX() - i);
				colNum = Math.abs(Target.getY() - j);
				gridMap[i][j].setH(rowNum + colNum);
//				gridMap[i][j].setParent(parent);
			}
		}
	}
	
	public void setBlocked(GridNode[][] gridMap, GridNode Target) {
		for (int i = 0; i < gridMap.length; i++) {
			for (int j = 0; j < gridMap.length; j++) {
				gridMap[i][j].setBlocked(false);
			}
		}
	}
	


    static int partition(ArrayList<GridNode> arr, int low, int high) 
    { 
        int pivot = arr.get(high).getH();  
        
        int i = (low-1); // index of smaller element 
      
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr.get(j).getH() <= pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                GridNode temp = arr.get(i); 
                arr.set(i, arr.get(j)); 
                arr.set(j, temp); 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        GridNode temp = arr.get(i+1); 
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);

  
        return i+1; 
    } 

    static void  sort(ArrayList<GridNode> arr, int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
            
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
//		sort(adjacent, 0, adjacent.size()-1);
		return adjacent;
	}

	public static void p(String s) {
		System.out.println(s);
	}
}
