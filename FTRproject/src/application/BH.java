package application;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import application.GridNode;

@SuppressWarnings("serial")
public class BH<E> extends ArrayList<E> {
	private ArrayList<GridNode> heap;

	public BH() {
		heap = new ArrayList<GridNode>();
	}

	public int size() {
		return heap.size();
	}

	public void clear() {
		heap.clear();
	}

	public int parent(int i) {
		return (i - 1) / 2;
	}

	public void insert(GridNode n) {
		heap.add(n);
		goUp(size() - 1);
	}

	public void removeByIndex(int index) {
		if (size() == 0) {
			throw new NoSuchElementException("Underflow Exception");
		} else {
			setByIndex(index, getByIndex(size() - 1));
//			System.out.println("IN removeBYINDEX: " + size());
			heap.remove(size() - 1);
			if (size() <= 1) {

			} else {
				goDown(index);
			}
		}
	}

	public GridNode delete() {
		GridNode key = top();
		removeByIndex(0);
		return key;
	}

	public GridNode top() {
		return getByIndex(0);
	}

	public GridNode getByIndex(int index) {
		return heap.get(index);
	}

	public void setByIndex(int index, GridNode node) {
		heap.set(index, node);
	}

	public int minChild(int parentIndex) {
		int minChild = 0;
		int leftChild = parentIndex * 2 + 1;
		int rightChild = parentIndex * 2 + 2;
		if (rightChild >= size()) {
			minChild = leftChild;
		} else {
			if (getByIndex(leftChild).getF() <= getByIndex(rightChild).getF()) {
				minChild = leftChild;
			} else {
				minChild = rightChild;
			}
		}
		return minChild;
	}

	public void goUp(int index) {
		GridNode tmp = getByIndex(index);
		while (index > 0 && tmp.getF() < getByIndex(parent(index)).getF()) {
			heap.set(index, getByIndex(parent(index)));
			index = parent(index);
		}
		heap.set(index, tmp);
	}

	public void goDown(int index) {
		int child;
//		System.out.println("IN GODOWN: " + size() + " " + index);
		GridNode temp = getByIndex(index);
		while ((index * 2 + 1) < size()) {
			child = minChild(index);
			if (getByIndex(child).getF() < temp.getF()) {
				setByIndex(index, getByIndex(child));
			} else {
				break;
			}
			index = child;
		}
		setByIndex(index, temp);
	}

	public void printHeap() {
		System.out.print("Heap List (nID): ");
		for (int i = 0; i < size(); i++) {
//			System.out.println("in printHeap:"+size());

			System.out.print(getByIndex(i).getnID() + " ");

		}
		System.out.println();
//		System.out.print("Heap(F-value): ");
//		for (int i = 0; i < size(); i++) {
////			System.out.println("in printHeap:"+size());
//
//			System.out.print(getByIndex(i).getF() + " ");
//
//		}
//		System.out.println();
	}
}
