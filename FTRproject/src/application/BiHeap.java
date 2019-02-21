package application;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BiHeap {
	/** The number of children each node has **/
	private static final int d = 2;
	private int heapSize;
	private ArrayList<GridNode> heap;

	/** Constructor **/
	public BiHeap() {
		heapSize = 0;
		heap = new ArrayList<GridNode>();
	}

	/** Function to check if heap is empty **/
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/** Clear heap */
	public void makeEmpty() {
		heapSize = 0;
	}

	/** Function to get index parent of i **/
	private int parent(int i) {
		return (i - 1) / d;
	}

	/** Function to get index of k th child of i **/
	private int kthChild(int i, int k) {
		return d * i + k;
	}

	/** Function to insert element */
	public void insert(GridNode x) {
		/** Percolate up **/
		heap.add(x);
		heapifyUp(heapSize - 1);
	}

	/** Function to find least element **/
	public GridNode findMin() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return heap.get(0);
	}

	/** Function to delete min element and return **/
	public GridNode deleteMin() {
		GridNode keyItem = heap.get(0);
		delete(0);
		return keyItem;
	}

	/** Function to delete element at an index **/
	public GridNode delete(int ind) {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		GridNode keyItem = heap.get(ind);
		heap.set(ind, heap.get(heap.size() - 1));
		heapSize--;
		heapifyDown(ind);
		return keyItem;
	}

	/** Function heapifyUp **/
	private void heapifyUp(int childInd) {
		GridNode tmp = heap.get(childInd);
		while (childInd > 0 && tmp.getF() < heap.get(parent(childInd)).getF()) {
			heap.set(childInd, heap.get(parent(childInd)));
			childInd = parent(childInd);
		}
		heap.set(childInd, tmp);
	}

	/** Function heapifyDown **/
	private void heapifyDown(int ind) {
		int child;
		GridNode tmp = heap.get(ind);
		while (kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if (heap.get(child).getF() < tmp.getF())
				heap.set(ind, heap.get(child));
			else
				break;
			ind = child;
		}
		heap.set(ind, tmp);
	}

	/** Function to get smallest child **/
	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if (heap.get(pos).getF() < heap.get(bestChild).getF())
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}

	/** Function to print heap **/
	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < heapSize; i++)
			System.out.print(heap.get(i) + " ");
		System.out.println();
	}
}
