package maman15sinaya;

public class BinaryTree {

	private TreeNode root; //tree root

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	/* gets the key as parameter and add it to the tree.
	 * if node with the same key exists- count is increased by 1.
	 * else, new node created and is added to the tree, keeping the binary search tree's nodes order
	 * returns the new/updated node
	 */
	public TreeNode insertNode(double key){
		TreeNode curr = this.getRoot();	
		if(curr==null){
			curr= new TreeNode(key);
			this.setRoot(curr);
			return curr;
		}
		boolean isLeft = false;
		TreeNode parent = null;
        while( curr != null )
        {
            if(curr.getKey()<key){
            	isLeft=false;
            	parent = curr;
            	curr= curr.getRight();
            }
            else if(curr.getKey()>key){
            	isLeft=true;
            	parent = curr;
            	curr= curr.getLeft();
            }
            else{
            	curr.setCount(curr.getCount()+1);
            	return curr; //in case the keys are the same - there is no need to create new node. just to update.
            }
        }
        TreeNode newNode = new TreeNode(key);
        newNode.setParent(parent);
        if(isLeft)
        	parent.setLeft(newNode);
        else
        	parent.setRight(newNode);
        return newNode;
    }
	
	/*gets a node as parameter and return its successor in the tree*/
	public TreeNode successor(TreeNode x){
		if(x.getRight()!=null)
			return minimum(x.getRight());
		TreeNode y = x.getParent();
		while(y!=null && x==y.getRight()){
			x=y;
			y= y.getParent();
		}
		return y;
	}
	/*gets a root node as parameter and returns the minimum node in the tree*/
	public TreeNode minimum(TreeNode root){
		TreeNode min = root;
		while(min.getLeft()!=null)
			min= min.getLeft();
		return min;
	}
	/*gets a root node as parameter and returns the maximum node in the tree*/
	public TreeNode maximum(TreeNode root){
		TreeNode max = root;
		while(max.getRight()!=null)
			max= max.getRight();
		return max;
	}
	
	/* gets a node to remove.
	 * count is decreased by 1
	 * if count equals to zero after decreasing - the node is removed from the tree and null is returned
	 * if count greater than zero - the updated node is returned
	 */
	public TreeNode removeNode(TreeNode node){
        if(node!=null){
        	node.setCount(node.getCount()-1);
        	if(node.getCount()==0){
        		TreeNode y;
        		TreeNode x;
        		if(node.getLeft()==null || node.getRight()==null)
        			y=node;
        		else
        			y= successor(node);
        		if(y.getLeft()!=null)
        			x= y.getLeft();
        		else
        			x= y.getRight();
        		if(x!=null)
        			x.setParent(y.getParent());
        		if(y.getParent()==null)
        			this.setRoot(x);
        		else if(y==y.getParent().getLeft())
        			y.getParent().setLeft(x);
        		else
        			y.getParent().setRight(x);
        		if(y!=node){
        			node.setKey(y.getKey());
        			node.setCount(y.getCount());
        			node.setPtr(y.getPtr());
        		}
        		return  null;
        	}
        	else	
        		return node; 
        }
        return null;
	}
	/* gets key and search for node in the tree with the same value.
	 * if node is found  - the node is returned
	 * else, null is returned
	 */
	public TreeNode searchNode(double key){
		TreeNode curr = this.getRoot();	
        return isNodeExistBetween(curr, key, key);
	}
	/* gets minimum key value, maximum key value and search for a node in the tree which its value matched.
	 * if node is found  - the node is returned
	 * else, null is returned
	 */
	public TreeNode isNodeExistBetween(TreeNode curr, double min, double max){
		while( curr != null )
        {
            if(curr.getKey()<min){
            	curr= curr.getRight();
            }
            else if(curr.getKey()>max){
            	curr= curr.getLeft();
            }
            else{
            	return curr;
            }
        }
        return null;
	}
}
