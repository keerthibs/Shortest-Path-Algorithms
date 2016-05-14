import java.util.Iterator;
/*
* This class implements the indexed heap which uses the priority as the distance field of the 
* vertices.
*
*/

public class MinBinaryHeap {

	Vertex[] pq;
	int counter;
	int size;

	/*
	 * Constructor that takes the graph and builds the priority queue that is
	 * based on the distance of the edges.
	 * 
	 */
	MinBinaryHeap(Graph g) {

		size = g.verts.size();
		pq = new Vertex[size];
		counter = 0;

		Iterator<Vertex> it = g.iterator();
		while (it.hasNext()) {
			Vertex v = it.next();
			this.insert(v);
		}

	}

	/*
	 * returns the first element of the Priority Queue
	 */
	public Vertex getFirst() {

		return pq[0];
	}

	/*
	 * This method inserts the given vertex into the priority queue based on the
	 * distance of the vertex.
	 */
	public void insert(Vertex v) {

		pq[counter] = v;
		pq[counter].putIndex(counter);

		int child = counter;
		int parent = (child - 1) / 2;

		// Heapify or Percolating Up
		while (parent >= 0) {

			if (v.compare(pq[child], pq[parent]) < 0) {
				swap(child, parent);
				child = parent;
				parent = (child - 1) / 2;
			} else {
				break;
			}
		}

		counter++;
	}

	/*
	 * This method removes the element with high priority and returns the Vertex
	 * If there is no element then it returns null.
	 */
	public Vertex removeMin() {
		// if there is no element in the heap
		if (counter == 0) {
			return null;
		}

		Vertex min = pq[0];
		swap(0, counter - 1);
		counter--;
		int parent = 0;

		// Percolating Down
		while (2 * parent + 1 < counter) {

			int child = 2 * parent + 1;

			// find the minimum from the left and right child

			if (child < counter - 1 && min.compare(pq[child], pq[child + 1]) > 0)
				child++;

			// if the child is less than parent, then swap
			if (min.compare(pq[child], pq[parent]) < 0)
				swap(parent, child);
			else
				break;

			parent = child;
		}

		return min;
	}

	/*
	 * This method decreases the key, that is , updates the priority of the
	 * given vertex with the given newDistance.
	 * 
	 * @param v: the Vertex for which the priority has to be changed
	 * 
	 * @param newDistance: the distance of the vertex which has to be updated to
	 * the priority
	 * 
	 */ public void decreaseKey(Vertex v, int newDistance) {

		int currentIndex = v.getIndex();
		pq[currentIndex].distance = newDistance;

		int child = currentIndex;
		int parent = (child - 1) / 2;

		// Heapify or Percolating Up
		while (parent >= 0) {

			// if(pq[child].distance<pq[parent].distance){
			if (v.compare(pq[child], pq[parent]) < 0) {
				swap(child, parent);
				child = parent;
				parent = (child - 1) / 2;
			} else {
				break;
			}
		}

	}

	/*
	 * Swap code that swaps the elements and their indexes in the priority queue
	 */
	private void swap(int i, int j) {
		Vertex temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;

		pq[i].putIndex(i);
		pq[j].putIndex(j);
	}

	/*
	 * The isEmpty method returns true if the Priority Queue is empty, otherwise
	 * returns false.
	 */ public boolean isEmpty() {
		if (counter == 0) {
			return true;
		}
		return false;
	}

}
