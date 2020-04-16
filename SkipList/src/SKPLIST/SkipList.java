package SKPLIST;

import java.util.Arrays;
import java.util.Random;

public class SkipList implements SkipList_Interface {
	private SkipList_Node root;
	private SkipList_Node tail;
	private final Random rand;
	private double probability;
	private final int MAXHEIGHT = 30; // the most links that a data cell may contain
	private int size;

	public SkipList(int maxHeight) {
		root = new SkipList_Node(Double.NaN, maxHeight);
		rand = new Random();
		probability = 0.5;
		tail = new SkipList_Node(Double.NaN, maxHeight);
		clear();
	}

	@Override
	public void setSeed(long seed) {
		rand.setSeed(seed);
	}

	@Override
	public void setProbability(double probability) {
		this.probability = probability;
	}

	private boolean flip() {
		// use this where you "roll the dice"
		// call it repeatedly until you determine the level
		// for a new node
		return rand.nextDouble() < probability;
	}

	@Override
	public SkipList_Node getRoot() {
		return root;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		int levels;
		for (levels = 0; levels < root.getNext().length && root.getNext(levels) != null; levels++)
			;

		StringBuilder[] sbs = new StringBuilder[levels];

		for (int i = 0; i < sbs.length; i++) {
			sbs[i] = new StringBuilder();
			sbs[i].append("level ").append(i).append(":");
		}

		SkipList_Node cur = root;

		while (cur.getNext(0) != null) {
			cur = cur.getNext(0);
			for (int i = levels - 1; i >= cur.getNext().length; i--) {
				sbs[i].append("\t");
			}
			for (int i = cur.getNext().length - 1; i >= 0; i--) {
				if (cur.getNext(i) == null) {
					levels--;
				}
				sbs[i].append("\t").append(cur.getValue());
			}
		}

		for (int i = sbs.length - 1; i >= 0; i--) {
			sb.append(sbs[i]).append("\n");
		}

		return sb.toString();
	}

	@Override
	public boolean insert(double value) {
		if (contains(value))
			return false;
		int level = 0;
		while (flip() & level < root.getLevel()-1)
			level++;

		SkipList_Node insert_node = new SkipList_Node(value, level+1);
		for (int i = level ; i > -1; i--) {
			SkipList_Node node = root;
			while (node.getNext(i).getValue() < value) {
				node = node.getNext(i);
			}
			insert_node.getNext()[i] = node.getNext(i);
			node.getNext()[i] = insert_node;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(double value) {
		if (!contains(value))
			return false;

		for (int i = 0; i < root.getLevel(); i++) {
			SkipList_Node n = root;
			while (n != tail) {
				if (n.getNext(i).getValue() == value)
					n.getNext()[i] = n.getNext(i).getNext(i);
				n = n.getNext(i);
			}
		}
		size--;
		return true;
	}

	@Override
	public boolean contains(double value) {
		SkipList_Node search_node = root;
		for (int lvl = root.getLevel() - 1; lvl > -1; lvl--) {
			while (search_node.getNext(lvl).getValue() < value)
				search_node = search_node.getNext(lvl);
			if (search_node.getNext(lvl).getValue() == value)
				return true;
		}
		return false;
	}

	@Override
	public double findMin() {
		if (size == 0)
			return Double.NaN;
		return root.getNext(0).getValue();
	}

	@Override
	public double findMax() {
		if (size == 0)
			return Double.NaN;
		SkipList_Node max = root;
		for (int i = root.getLevel() - 1; i > -1; i--) {
			while (max.getNext(i) != tail)
				max = max.getNext(i);
		}
		return max.getValue();
	}

	@Override
	public boolean empty() {
		if (size == 0)
			return false;

		return true;
	}

	@Override
	public void clear() {
		SkipList_Node[] next = root.getNext();
		for (int i = 0; i < root.getLevel(); i++) {
			next[i] = tail;
		}
		size =0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int level() {
		for (int level = root.getLevel() - 1; level > -1; level--) {
			if (root.getNext(level) != tail)
				return level;
		}
		return -1;
	}

	@Override
	public int max() {
		// TODO Auto-generated method stub
		return root.getLevel();
	}

	// ---------------------------------------------------------
	// student code follows
	// implement the methods of the interface
	// ---------------------------------------------------------

}
