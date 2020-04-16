package BST;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

	@Override
	public boolean insert(String s) {
		if(s==null)return false;
		if (root == null) {
			root = new BST_Node(s);
			size++;
			return true;
		}
		if (contains(s))
			return false;

		BST_Node insert_node = new BST_Node(s);
		BST_Node target_node = root;

		while (true) {
			int i = target_node.data.compareTo(s);
			if (i > 0) {
				if (target_node.left == null) {
					target_node.left = insert_node;
					break;
				} else {
					target_node = target_node.left;

				}

			}
			if (i < 0) {
				if (target_node.right == null) {
					target_node.right = insert_node;
					break;
				} else {
					target_node = target_node.right;

				}

			}

		}
		size++;
		return true;

	}

	@Override
	public boolean remove(String s) {
		if(s == null) return false;
		BST_Node node = findNode(s);
		BST_Node target_node = root;
		if (node == null)
			return false;
		
		//if the removed node is left to its parent
		boolean left= false;
		int i = target_node.data.compareTo(s);

		while (i!=0) {
			if (target_node.left == node | target_node.right == node)
				break;
			i = target_node.data.compareTo(s);
			if (i > 0)
				target_node = target_node.left;
			if (i < 0)
				target_node = target_node.right;
			
		}
		if (target_node.left == node) left = true;
		// leaf delete
		if (node.left == null & node.right == null) {
			if(node == root) {
				root= null;
			}else
			if(left) {
				target_node.left = null;
			}else {
				target_node.right = null;
			}
			size--;
		
		// one child delete
		} else if (node.left != null ^ node.right != null) {
			BST_Node child;
			if(node.left != null) {
				child = node.left;
			}else {
				child = node.right;
			}
			if(node == root) {
				root = child;
			}else
			if(left) {
				target_node.left = child;
			}else {
				target_node.right = child;
			}
			size--;
			// two child delete
		}else {
			BST_Node child = minNode(node.right);
			String tmp = child.data;
			remove(child.data);
			node.data = tmp;
		}
		
		
		
			
			return true;
	}

	private BST_Node minNode(BST_Node target_node) {
		while (target_node.left != null)
			target_node = target_node.left;
		return target_node;
	}

	public String findMin() {
		if(root == null) return null;
		return minNode(root).getData();
	}

	@Override
	public String findMax() {
		if(root == null) return null;
		return maxNode(root).getData();
	}

	private BST_Node maxNode(BST_Node target_node) {
		while (target_node.right != null)
			target_node = target_node.right;
		return target_node;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean contains(String s) {
		if (findNode(s) == null)
			return false;
		return true;
	}

	private BST_Node findNode(String s) {
		if (size == 0)
			return null;
		BST_Node target_node = root;

		for (int i = target_node.data.compareTo(s); i != 0; i = target_node.data.compareTo(s)) {
			if (i > 0)
				target_node = target_node.left;
			if (i < 0)
				target_node = target_node.right;
			if (target_node == null)
				return null;
		}
		return target_node;

	}
	@Override

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	private int dive(BST_Node node) {
		if(node == null) return 0;
		if(node.left==null & node.right == null) return 1;
		return 1+Integer.max(dive(node.left), dive(node.right));
	}
	@Override
	public int height() {

		
		return dive(root)-1;

	
	}

}
