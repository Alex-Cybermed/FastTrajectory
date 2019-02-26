package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class board {

	static int x = 10;
	static int numOfMap = 9;
	// A function used by DFS 
//    void DFSUtil(GridNode[][] map, int A, boolean  visited[] ) 
//    { 
//        // Mark the current node as visited and print it 
//        visited[A] = true; 
//        System.out.print(A+" "); 
//  
//        // Recur for all the vertices adjacent to this vertex 
//        Iterator<GridNode> i = adj[A].listIterator(); 
//        while (i.hasNext()) 
//        { 
//            int n = i.next(); 
//            if (!visited[n]) 
//                DFSUtil(n,visited); 
//        } 
//    } 
//  
//    // The function to do DFS traversal. It uses recursive DFSUtil() 
//    GridNode[][] DFS(GridNode[][] map, GridNode A) 
//    { 
//        // Mark all the vertices as not visited(set as 
//        // false by default in java) 
//    		int si = map.length-1;
//    		int a = A.getnID();
//        boolean visited[] = new boolean[si]; 
//  
//        // Call the recursive helper function to print DFS traversal 
//        // starting from all vertices one by one 
//        for (int i=0; i<si; ++i) 
//            if (visited[i] == false) 
//                DFSUtil(map,i, visited); 
//    } 
	static void DFSUtil(GridNode A,boolean visited[], GridNode[][] map) 
	    { 
	        // Mark the current node as visited and print it 
	        visited[A.getnID()] = true; 
//	        System.out.print(v+" "); 
	  
	        // Recur for all the vertices adjacent to this vertex 
//	        Iterator<GridNode> i = adj[v].listIterator(); 
	        ArrayList<GridNode> adj = ADJ(map,A);
	        for (int i = 0; i < adj.size(); i++) {
	        	//visiting nebor
	        		GridNode n = adj.get(i);
	        		n.setStatus("_");
				if (Math.random() <= 0.3) {
					n.setStatus("X");
				}
				visited[n.getnID()] = true;
				DFSUtil(n,visited, map);
	        	
	        }
			
	        
//	        while (i.hasNext()) 
//	        { 
//	            int n = i.next(); 
//	            if (!visited[n]) 
//	                DFSUtil(n, visited); 
//	        } 
	    } 
	  
		public static ArrayList<GridNode> ADJ(GridNode[][] gridMap, GridNode curr) {
			ArrayList<GridNode> adjacent = new ArrayList<GridNode>();
			int x = gridMap.length;
			if (curr.getX() + 1 < x)  {
				adjacent.add(gridMap[curr.getX() + 1][curr.getY()]);
				
			}
			if (curr.getX() - 1 >= 0) {
				adjacent.add(gridMap[curr.getX() - 1][curr.getY()]);
			}
			if (curr.getY() - 1 >= 0 ) {
				adjacent.add(gridMap[curr.getX()][curr.getY() - 1]);
			}
			if (curr.getY() + 1 < x) {
				adjacent.add(gridMap[curr.getX()][curr.getY() + 1]);
			}
			return adjacent;
		}
	    // The function to do DFS traversal. It uses recursive DFSUtil() 
	    static GridNode[][] DFS(GridNode A, GridNode[][] map) 
	    { 
	        // Mark all the vertices as not visited(set as 
	        // false by default in java) 
	        boolean visited[] = new boolean[x*x]; 
	  
	        // Call the recursive helper function to print DFS traversal 
	        DFSUtil(A, visited, map); 
	        return map;
	    } 
    public static GridNode[][] initial() {
		GridNode[][] gridBoard = new GridNode[x][x];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				GridNode n = new GridNode(i, j, "_");
//				if (Math.random() <= 0.3) {
//					n.setStatus("X");
//				}
				gridBoard[i][j] = n;
			}
		}
		//set A first
		Random r = new Random();
		GridNode A = gridBoard[0][0];
		A.setStatus("A");
		//DFS 
		gridBoard = DFS(A, gridBoard);
//		for (int i = 0; i < x; i++) {
//			for (int j = 0; j < x; j++) {
//				GridNode n = new GridNode(i, j, "_");
//				if (Math.random() <= 0.3) {
//					n.setStatus("X");
//				}
//				gridBoard[i][j] = n;
//			}
//		}
//		randomAT(gridBoard,"A").setStatus("A");
//		randomAT(gridBoard,"T").setStatus("T");
		return gridBoard;
	}
    
    
//	public static GridNode[][] initial() {
//		GridNode[][] gridBoard = new GridNode[x][x];
//		for (int i = 0; i < x; i++) {
//			for (int j = 0; j < x; j++) {
//				GridNode n = new GridNode(i, j, "_");
//				if (Math.random() <= 0.3) {
//					n.setStatus("X");
//				}
//				gridBoard[i][j] = n;
//			}
//		}
//		randomAT(gridBoard,"A").setStatus("A");
//		randomAT(gridBoard,"T").setStatus("T");
//		return gridBoard;
//	}

	public static GridNode randomAT(GridNode[][] gridBoard, String AT) {
		Random r = new Random();
		int row = r.nextInt(x);
		int col = r.nextInt(x);
		if(!gridBoard[row][col].getStatus().equals("X")) {
			if(gridBoard[row][col].getStatus().equals("A")) {
				return randomAT(gridBoard,"A");
			}else if(gridBoard[row][col].getStatus().equals("T")) {
				return randomAT(gridBoard,"T");
			}else {
				return gridBoard[row][col];
			}
		}
		return randomAT(gridBoard,AT);
	}

	public static void printMap(GridNode[][] gridBoard) {
		for (int k = 0; k < x; k++) {
			System.out.print(k+" ");
//			System.out.print("- ");
		}
		System.out.println();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				if (gridBoard[i][j].getStatus().equals("A")) {
					System.out.printf("A ");
				} else if (gridBoard[i][j].getStatus().equals("T")) {
					System.out.printf("T ");
				} else if (gridBoard[i][j].getStatus().equals("X")) {
					System.out.printf("X ");
				} else if (gridBoard[i][j].getStatus().equals("_")) {
					System.out.printf("_ ");
				}
			}
			System.out.print(i);
			System.out.println();
		}
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
	}
	public static  void printAIMap(GridNode[][] gridBoard, GridNode A, GridNode T) {
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
		System.out.println();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
//				if (gridBoard[i][j].getnID()==A.getnID()) {
//					System.out.printf("A ");
//				} else if (gridBoard[i][j].getnID()==T.getnID()) {
//					System.out.printf("T ");
//				} else if (gridBoard[i][j].isBlocked()) {
//					System.out.printf("X ");
//				}  else if(gridBoard[i][j].getStatus().equals("*")) {
//					System.out.printf("* ");
////					System.out.print(gridBoard[i][j].getParent().getnID()+" ");
//				}  else if (!gridBoard[i][j].isBlocked()) {
//					System.out.printf("_ ");
////					System.out.print(gridBoard[i][j].getH()+" ");
//				}
				System.out.print("ID:"+gridBoard[i][j].getnID());
				System.out.print(" h"+gridBoard[i][j].getH()+"   ");
				System.out.print(" g"+gridBoard[i][j].getG()+"   ");

			}
//			System.out.print(i);
			System.out.println();
		}
		for (int k = 0; k < x; k++) {
			System.out.print("--");
		}
		System.out.println();
	}

	public static void FileOut(GridNode[][] gridBoard, int k) throws IOException {
		try {
			String s = "src/TestCases/Test" + k + ".txt";
//			System.out.println("File Test" + k + ".txt successfully created!");
			PrintWriter fileWriter = new PrintWriter(s, "UTF-8");
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
//					if ([i][j].getStatus() == 'A') {
//						String msg = gridBoard[i][j].getStatus() + " ";
//						fileWriter.write(msg);
//					} else if (gridBoard[i][j].getStatus() == 'T') {
//						String msg = gridBoard[i][j].getStatus() + " ";
//						fileWriter.write(msg);
//					} else {
					// print node index
//					fileWriter.write(i + " " + j + " ");
//					//print node status
					fileWriter.write(gridBoard[i][j].getStatus() + " ");
//					//print visited status
//					if (!gridBoard[i][j].isVisited()) {
//						fileWriter.write("Unvisit ");
//					} else {
//						fileWriter.write("Visited ");
//					}
				}
				fileWriter.write("\n");
			}
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println("IOException");
		}
	}

	public static GridNode[][] fileIn(String address) throws IOException {
		GridNode[][] testCase = new GridNode[x][x];
		try {
			Scanner fr = new Scanner(new File(address));
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
					String status = fr.next();
//					System.out.println(status);
					testCase[i][j] = new GridNode(i, j, status);
//					System.out.print(testCase[i][j].getStatus());
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		printMap(testCase);
		return testCase;
	}

	public static void main(String[] args) throws IOException {
		ForwardAStar fa = new ForwardAStar();
		BackwardAStar ba = new BackwardAStar();
//		/*
//		Generate new maps
//		*/
		for (int i = numOfMap; i <= numOfMap; i++) {
			GridNode[][] gridBoard = initial();
			FileOut(gridBoard, i);
		}
		
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		int n = numOfMap;
//		System.out.println("Input the number of map you want to test:" + n);
		String fileAddress = "src/TestCases/Test" + n + ".txt";
		GridNode[][] out = fileIn(fileAddress);
		long fstime = System.nanoTime();
		fa.Forward(out);
		long fetime = System.nanoTime();
		long bstime = System.nanoTime();
		ba.Backward(out);
		long betime = System.nanoTime();
		long fnstime =(fetime-fstime);
		long bnstime =(betime-bstime);
		double fsecond = Math.pow(10, -9)*fnstime;
		double bsecond = Math.pow(10, -9)*bnstime;
		System.out.println("Forward time:"+fnstime);
		System.out.println("Backward time:"+bnstime);
		System.out.println("Forward second time:"+fsecond);
		System.out.println("Backward second time:"+bsecond);
	}


}
