package application;

public class GridNode {

	private int X;
	private int Y;
	private char status;
	private boolean visited;
	private boolean blocked;
	private GridNode parent;
	private int g;
	private int f;
	private int h;

	public GridNode(int X, int Y, char status) {
		this.X = X;
		this.Y = Y;
		this.status = status;
	}

	public GridNode(int X, int Y, char status, boolean visited, GridNode parent, int g, int f, int h) {
		this.X = X;
		this.Y = Y;
		this.status = status;
		this.visited = visited;
		this.parent = parent;
		this.g = g;
		this.f = f;
		this.h = h;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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
}
