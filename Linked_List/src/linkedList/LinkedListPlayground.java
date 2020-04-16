package linkedList;

import java.util.Scanner;

public class LinkedListPlayground {

	public static void main(String[] args) {
		/*
		 * here you can instantiate your LinkedList and play around with it to check
		 * correctness. We've graciously also provided you a bit of extra test data for
		 * debugging. It doesn't matter what you have in here. We will not grade it.
		 * This is for your use in testing your implementation.
		 */
		// test1();
		// test2();
		LinkedListImpl L = new LinkedListImpl();
		L.insort(1);
		printList(L);
		//test3();
		 //testr();

	}

	public static void testr() {

		Scanner scan = new Scanner(System.in);
		LinkedListImpl L = new LinkedListImpl();
		int i = 0;
		while (true) {

			int random = (int) (Math.random() * Math.random() * 100);
			randOp(random, L);

			// System.out.println("LIST CLEAR!");
			// L.clear();
		}

	}

	public static void randOp(int i, LinkedListImpl L) {
		int ind = (int) (Math.random() * L.size());
		switch ((int) (Math.random() * 5)) {

		case 1:
			System.out.println("Remove at " + ind);
			L.remove(ind);
			break;
		case 2:
			System.out.println("insort");

			L.insort(i);
			break;
		case 3:
			System.out.println("insert at " + ind);
			L.insert(i, ind);
			break;
		case 4:
			System.out.println("insort");

			L.insort(i);
			break;
		case 5:
			System.out.println("insert at " + ind);
			L.insert(i, ind);
			break;

		default:
			break;
		}
	
		printList(L);
		if(L.size()> 9)L.clear();
	}

	public static void test1() {
		// example test cases
		LinkedListImpl L = new LinkedListImpl();
		System.out.println(L.isEmpty());
		printList(L);
		L.clear();
		System.out.println(L.isEmpty());
		printList(L);
		L.insert(3.3, 0);
		System.out.println(L.isEmpty());
		printList(L);
		L.insert(3.4, 0);
		printList(L);

		L.insert(3.5, 0);
		printList(L);

		L.insert(3.67, 1);
		printList(L);

		L.insert(3.357, 0);
		printList(L);

		L.insert(3.333, 4);
		printList(L);

		System.out.println(L.size());
		printList(L);
		L.remove(3);
		System.out.println(L.size());
		printList(L);
		L.clear();
		L.insert(3.4, 0);
		L.insert(3.5, 0);
		L.insert(3.67, 1);
		L.insert(3.357, 0);
		L.insert(3.333, 3);
		L.remove(0);
		System.out.println(L.size());
		printList(L);
	}

	public static void test2() {
		// example test cases
		LinkedListImpl L = new LinkedListImpl();
		L.insert(3.4, 0);
		L.insert(3.5, 1);
		L.insert(3.67, 2);
		L.remove(0);
		System.out.println(L.size());
		printList(L);
	}

	public static void test3() {
		Scanner scan = new Scanner(System.in);

		LinkedListImpl L = new LinkedListImpl();
		char i = '0';
		while (i != 'e') {

			System.out.println("z = insort / x = insert");
			i = scan.next().charAt(0);
			switch (i) {
			case 'r':
				L.remove(scan.nextInt());
				break;
			case 'c':
				L.clear();
				break;
			case 'z':
				L.insort(scan.nextInt());
				break;
			case 'x':
				L.insert(scan.nextInt(), scan.nextInt());
				break;
			default:
				break;
			}
			if (L.size() > 9)
				L.clear();
			printList(L);

		}
	}

	public static void printList(LinkedListImpl L) {
		// note that this is a good example of how to iterate through your linked list
		// since we know how many elements are in the list we can use a for loop
		Node curr = L.headCell; // the first data node in the list... might be null
		System.out.print("List: ");
		for (int i = 0; i < L.size(); i++) {
			System.out.print(" --> " + L.get(i));
			curr = curr.next;
		}
		System.out.println();
	}
}