package application;

public class GridNode {
	
	private int X;
	private int Y;
	private char Status;
	private boolean visited;
	private boolean blocked;
	private GridNode parent;
	private int g;
	private int f;
	private int h;
	
	
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
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public GridNode getParent() {
		return parent;
	}
	public void setParent(GridNode parent) {
		this.parent = parent;
	}
	

}
