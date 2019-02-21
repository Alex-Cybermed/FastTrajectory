package application;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BiHeap {
	/** The number of children each node has **/
	private static final int d = 2;
	private int heapSize;
	private GridNode[] heap;

	/** Constructor **/
	public BiHeap(int capacity) {
		heapSize = 0;
		heap = new GridNode[capacity + 1];
		Arrays.fill(heap, -1);
	}

	/** Function to check if heap is empty **/
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/** Check if heap is full **/
	public boolean isFull() {
		return heapSize == heap.length;
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
		if (isFull())
			throw new NoSuchElementException("Overflow Exception");
		/** Percolate up **/
		heap[heapSize++] = x;
		heapifyUp(heapSize - 1);
	}

	/** Function to find least element **/
	public GridNode findMin() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return heap[0];
	}

	/** Function to delete min element and return **/
	public GridNode deleteMin() {
		GridNode keyItem = heap[0];
		delete(0);
		return keyItem;
	}

	/** Function to delete element at an index **/
	public GridNode delete(int ind) {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		GridNode keyItem = heap[ind];
		heap[ind] = heap[heapSize - 1];
		heapSize--;
		heapifyDown(ind);
		return keyItem;
	}

	/** Function heapifyUp **/
	private void heapifyUp(int childInd) {
		GridNode tmp = heap[childInd];
		while (childInd > 0 && tmp.getF() < heap[parent(childInd)].getF()) {
			heap[childInd] = heap[parent(childInd)];
			childInd = parent(childInd);
		}
		heap[childInd] = tmp;
	}

	/** Function heapifyDown **/
	private void heapifyDown(int ind) {
		int child;
		GridNode tmp = heap[ind];
		while (kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if (heap[child].getF() < tmp.getF())
				heap[ind] = heap[child];
			else
				break;
			ind = child;
		}
		heap[ind] = tmp;
	}

	/** Function to get smallest child **/
	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if (heap[pos].getF() < heap[bestChild].getF())
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}

	/** Function to print heap **/
	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < heapSize; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
	}
}
