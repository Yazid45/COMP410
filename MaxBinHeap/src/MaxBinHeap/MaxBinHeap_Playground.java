package MaxBinHeap;

public class MaxBinHeap_Playground {
	public static void main(String[] args) {
		// Add more tests as methods and call them here!!

		testa();

	}

	public static void testa() {
		MaxBinHeap heap = new MaxBinHeap();
		double[] elements = new double[9999];
		for (int i = 0; i < elements.length; i++) 
			elements[i] = i;
		heap.build(elements);
		printHeap(heap.getHeap(), heap.size());
		
	}

	public static void testr() {
		MaxBinHeap heap = new MaxBinHeap();

		while (true) {
			int l = (int) (10 * Math.random());
			double oarray[] = new double[l];
			for (int i = 0; i < oarray.length; i++)
				oarray[i] = (Math.random() * 100);
			double array[] = heap.sort(oarray);
			heap.build(oarray);
			heapcheck(heap, array, oarray);

		}
	}

	public static void heapcheck(MaxBinHeap heap, double array[], double[] oarray) {
		double l[] = heap.getHeap();
		int s = heap.size();
		for (int i = 1; i < s; i++) {
			if (l[i] < l[i * 2] | l[2 * i + 1] > l[i]) {
				printHeap(l, s);
				printArray(oarray);
				heap.build(oarray);
			}
		}
		for (int i = 0; i < array.length - 1; i++)
			if (array[i] > array[i + 1]) {
				printHeap(l, s);
				printArray(oarray);
				heap.build(oarray);
			}
	}

	public static void TestBuild() {
		// constructs a new maxbinheap, constructs an array of double,
		// passes it into build function. Then print collection and heap.
		MaxBinHeap mbh = new MaxBinHeap();
		double[] collection = new double[] { 3, 4, 2, 1, 7, 4, 5, 6 };
		mbh.build(collection);
		printHeapCollection(collection);
		printHeap(mbh.getHeap(), mbh.size());
	}

	public static void TestSort() {
		// constructs a new maxbinheap, constructs an array of double, passes
		// it into heapsort function. Then print collection and sorted array.

		MaxBinHeap mbh = new MaxBinHeap();
		double[] collection = new double[] { 3, 4, 2, 1, 7, 4, 5, 6 };
		double[] sorted = mbh.sort(collection);
		printSortCollection(collection);
		printHeap(mbh.getHeap(), mbh.size());
		printArray(sorted);
	}

	public static double[] charsToDoubles(char[] chars) {
		double[] ret = new double[chars.length];
		for (int i = 0; i < chars.length; i++) {
			ret[i] = charToInt(chars[i]);
		}
		return ret;
	}

	public static int charToInt(char c) {
		return c - 'a';
	}

	public static void printHeapCollection(double[] e) {
		// this will print the entirety of an array of doubles you will pass
		// to your build function.
		System.out.println("Printing Collection to pass in to build function:");
		for (int i = 0; i < e.length; i++) {
			System.out.print(e[i] + "\t");
		}
		System.out.print("\n");
	}

	public static void printHeap(double[] e, int len) {
		// pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th
		// index....
		System.out.println("Printing Heap");
		for (int i = 1; i < len + 1; i++) {
			System.out.print(e[i] + "\t");
		}
		System.out.print("\n");
	}

	public static void printSortCollection(double[] e) {
		// this will print the entirety of an array of doubles you will pass
		// to your build function.
		System.out.println("Printing Collection to pass in to heap sort function:");
		for (int i = 0; i < e.length; i++) {
			System.out.print(e[i] + "\t");
		}
		System.out.print("\n");
	}

	public static void printArray(double[] array) {
		System.out.println("Printing Array");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.print("\n");
	}
}