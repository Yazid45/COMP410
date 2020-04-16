package MaxBinHeap;

public class MaxBinHeap implements Heap_Interface {
	private double[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will initially
											// be null. This is ok! Just build out
											// from array[1]

	public MaxBinHeap() {
		this.array = new double[arraySize];
		array[0] = Double.NaN; // 0th will be unused for simplicity
								// of child/parent computations...
								// the book/animation page both do this.
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public double[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(double element) {
		array[size + 1] = element;
		size++;
		int n = size;
		while (n > 0) {
			if (array[n] > array[n / 2]) {
				double tmp = array[n / 2];
				array[n / 2] = array[n];
				array[n] = tmp;
			}
			n = n / 2;
		}
	}

	@Override
	public void delMax() {
		// TODO Auto-generated method stub
		if (size == 0)
			return;
		array[1] = array[size];
		array[size] = 0;
		int h = 1;
		while (array[h] < array[2 * h] | array[h] < array[(2 * h) + 1]) {
			boolean l = array[2 * h] > array[2 * h + 1];
			if (l) {
				double t = array[2 * h];
				array[2 * h] = array[h];
				array[h] = t;
				h = 2 * h;
			} else {
				double t = array[2 * h + 1];
				array[2 * h + 1] = array[h];
				array[h] = t;
				h = 2 * h + 1;
			}
		}
		size--;
	}

	@Override
	public double getMax() {
		if (size == 0)
			return Double.NaN;
		return array[1];
	}

	@Override
	public void clear() {
		this.array = new double[arraySize];
		array[0] = Double.NaN;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	public double pop() {
		double tmp = getMax();
		delMax();
		return tmp;
	}

	@Override
	public void build(double[] elements) {
		if (elements == null)
			return;
		clear();
		for (int i = 0; i < elements.length; i++) {
			array[i + 1] = elements[i];
		}
		size = elements.length;
		for (int i = size / 2; i > 0; i--) {
			int t = i;
			while (true & t*2< size) {
				boolean right = array[t*2] < array[t * 2 + 1];
				if (!right & array[t] < array[t * 2]) {
					double tmp = array[t];
					array[t] = array[t * 2];
					array[t * 2] = tmp;
					t = t * 2;
				} else if (array[t] < array[t * 2 + 1]) {
					double tmp = array[t];
					array[t] = array[t * 2+1];
					array[t * 2+1] = tmp;
					t = t * 2+1;
				} else {
					break;
				}
			}

		}
	}

	@Override
	public double[] sort(double[] elements) {
		if (elements == null)
			return new double[0];
		build(elements);
		double rarray[] = new double[elements.length];
		for (int i = elements.length - 1; i > -1; i--) {
			rarray[i] = getMax();
			delMax();
		}
		return rarray;

	}

}