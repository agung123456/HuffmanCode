public class Node {
	int freq;
	char data;
	Node left, right, next;

	public Node(char data, int freq, Node left, Node right) {
		this.freq = freq;
		this.data = data;
		this.left = left;
		this.right = right;
	}

	boolean isLeaf() {
		return (left == null && right == null);
	}
}
