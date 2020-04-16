package BST;

public class BST_Playground {
	/*
	 * you will test your own BST implementation in here
	 *
	 * we will replace this with our own when grading, and will do what you should
	 * do in here... create BST objects, put data into them, take data out, look for
	 * values stored in it, checking size and height, and looking at the BST_Nodes
	 * to see if they are all linked up correctly for a BST
	 * 
	 */

	public static void main(String[] args) {

		// you should test your BST implementation in here
		// it is up to you to test it thoroughly and make sure
		// the methods behave as requested above in the interface

		// do not simple depend on the oracle test we will give
		// use the oracle tests as a way of checking AFTER you have done
		// your own testing

		// one thing you might find useful for debugging is a print tree method
		// feel free to use the printLevelOrder method to verify your trees manually
		// or write one you like better
		// you may wish to print not only the node value, and indicators of what
		// nodes are the left and right subtree roots,
		// but also which node is the parent of the current node

		BST tree = new BST();
		tree.insert("JK");
		tree.insert("UU");
		tree.insert("IW");
		tree.insert("AR");
		tree.remove("JK");

		printLevelOrder(tree);
		System.out.print(tree.height());

	}

	static void testr() {
		final int size=9;
		BST tree = new BST();
		int count = 0;
		String rm [] = new String[size+1];
		for(int i =0; i<size+1; i++)
			rm[i]= "";
		while (true) {
			char a[] = new char[2];
			a[0] = (char) ((int) 26 * Math.random() + 65);
			a[1] = (char) ((int) 26 * Math.random() + 65);
			rm[count] = new String(a);
			count = (count + 1) % size;
			randOp(tree, new String(a), rm);
			if (tree.size > size)
				tree = new BST();

		}

	}

	static void randOp(BST tree, String s, String rm[]) {
		int i = (int) (3 * Math.random());
		switch (i) {
		case 0:

			tree.insert(s);
			break;
		case 2:
		case 1:
			int r = (int) (Math.random()*9);
			tree.remove(rm[r]);
			break;
		}
		printLevelOrder(tree);
		System.out.print("\n");
	}

	static void printLevelOrder(BST tree) {
		// will print your current tree in Level-Order...
		// https://en.wikipedia.org/wiki/Tree_traversal
		int h = tree.height();
		for (int i = 0; i <= h; i++) {
			printGivenLevel(tree.getRoot(), i);
		}

	}

	static void printGivenLevel(BST_Node root, int level) {
		if (root == null)
			return;
		if (level == 0)
			System.out.print(root.data + " ");
		else if (level > 0) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}

	static void printInOrder(BST_Node root) {
		// will print your current tree In-Order
		if (root != null) {
			printInOrder(root.getLeft());
			System.out.print(root.getData() + " ");
			printInOrder(root.getRight());
		}
	}

}
