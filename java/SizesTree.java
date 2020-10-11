package maman15sinaya;

import java.util.Stack;
/* represents sides tree with height tree per side node
 * each side node includes info about max value of height in the connected height tree*/
public class SizesTree extends BinaryTree{
	
	/* gets box sizes and add the box to the trees.
	 * changes max value if needed
	 */
	public void insertValues(double height , double side){
		TreeNode newNode = insertNode(side);
		if(newNode.getMax()<height)
			newNode.setMax(height);
		if(newNode.getPtr()==null){
			BinaryTree newTree = new BinaryTree();
			newNode.setPtr(newTree);
		}
		BinaryTree tree = (BinaryTree) newNode.getPtr();
		tree.insertNode(height);
	}
	
	/* gets box sizes and remove the box from the trees
	 * checks new max value if needed
	 */
	public void removeValues(double side, double height){
		TreeNode tempSideNode = searchNode(side);
		tempSideNode  = removeNode(tempSideNode);
		if(tempSideNode!=null){
			BinaryTree tempTree = (BinaryTree) tempSideNode.getPtr();
			TreeNode tempHeightNode = tempTree.searchNode(height);
			tempHeightNode = tempTree.removeNode(tempHeightNode);
			if(tempHeightNode==null && height==tempSideNode.getMax()){
				TreeNode newH= tempTree.maximum(tempTree.getRoot());
				tempSideNode.setMax(newH.getKey());
			}
		}
	}
	/*get box sized and returns true if there is any matched box
	 * if there is any - false is returned
	 */
	public boolean checkValues(double side, double height){
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode currentNode=this.getRoot();
		while(!s.empty() || currentNode!=null){
			if(currentNode!=null){
				s.push(currentNode);
				currentNode=currentNode.getLeft();
			}
			else{
				TreeNode n=s.pop();
				if(n.getKey()>=side){
					if(n.getMax()>=height){
						return true;
					}				
				}
				currentNode=n.getRight();
			}
		}
		return false;
	}
	
}
