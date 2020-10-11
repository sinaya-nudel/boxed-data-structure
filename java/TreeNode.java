package maman15sinaya;

public class TreeNode {

	private double key; //key value
	private double max; //max value of secondary tree (in use in SizesTree)
	private int count; //key count
	private Object ptr; //pointer to object (for the secondary tree)
	private TreeNode left;
	private TreeNode right;
	private TreeNode parent;

	public TreeNode() {
	}
	
	public TreeNode(double key) {
		this.key = key;
		this.count = 1;
		this.max=0;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getKey() {
		return key;
	}
	public void setKey(double key) {
		this.key = key;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public Object getPtr() {
		return ptr;
	}

	public void setPtr(Object ptr) {
		this.ptr = ptr;
	}

	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

}
