package application;

import java.util.ArrayList;

public class GridNode {
	private int nID;
	private int X;
	private int Y;
	private String status;
	private boolean visited;
	private boolean isBlocked;
	private GridNode parent;
	private int g;
	private int f;
	private int h;
	private int search;
	private ArrayList<GridNode> adj;

	public GridNode(int X, int Y, String string) {
		this.X = X;
		this.Y = Y;
		this.status = string;
	}
	
	public GridNode(int nID, String string) {
		this.nID=nID;
		this.status = string;
	}

	public GridNode(int nID, int X, int Y, String status, boolean visited, GridNode parent, int g, int f, int h, int search) {
		this.X = X;
		this.Y = Y;
		this.status = status;
		this.visited = visited;
		this.parent = parent;
		this.g = g;
		this.f = f;
		this.h = h;
		this.search = search;
	}
	
	public ArrayList<GridNode> getAdj() {
		return adj;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public GridNode getParent() {
		return parent;
	}

	public void setParent(GridNode parent) {
		this.parent = parent;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getSearch() {
		return search;
	}

	public void setSearch(int search) {
		this.search = search;
	}

	public int getnID() {
		return nID;
	}

	public void setnID(int nID) {
		this.nID = nID;
	}

	public void setAdj(ArrayList<GridNode> adj) {
		this.adj = adj;
	}
	
	@Override
	public String toString() {
		return " "+nID ;
		
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
}
