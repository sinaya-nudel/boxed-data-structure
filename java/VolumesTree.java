package maman15sinaya;

import java.util.Stack;

/*represents volumes tree with height tree per volume node*/
public class VolumesTree extends BinaryTree {
	
	/*gets box sizes and add the box to the trees.*/
	public void insertValues(double height , double side){
		double vol = height * side * side;
		TreeNode newNode = insertNode(vol);
		if(newNode.getPtr()==null){
			BinaryTree newTree = new BinaryTree();
			newNode.setPtr(newTree);
		}
		BinaryTree tree = (BinaryTree) newNode.getPtr();
		tree.insertNode(height);
	}
	/*gets box sizes and remove the box from the trees*/
	public void removeValues(double side, double height){
		double vol = height * side * side;	
		TreeNode tempNode = searchNode(vol);
		tempNode  = removeNode(tempNode);
		if(tempNode!=null){
			BinaryTree tempTree = (BinaryTree) tempNode.getPtr();
			tempNode = tempTree.searchNode(height);
			tempTree.removeNode(tempNode);
		}
	}
	/*get box sized and returns the matched box with the minimal volume
	 * if there is any - null is returned
	 */
	public Box getValues(double side, double height){
		double minVol = height * side * side;
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode currentNode=this.getRoot();
		while(!s.empty() || currentNode!=null){
			if(currentNode!=null){
				s.push(currentNode);
				currentNode=currentNode.getLeft();
			}
			else{
				TreeNode n=s.pop();
				if(n.getKey()>=minVol){
					double maxHeight = (n.getKey()/(side*side));
					TreeNode treeRoot = ((BinaryTree) n.getPtr()).getRoot();
					TreeNode temp= isNodeExistBetween(treeRoot,height,maxHeight);
					if(temp!=null){
						double sideCalc = Math.sqrt(n.getKey()/temp.getKey());
						return new Box(temp.getKey(),sideCalc);
					}				
				}
				currentNode=n.getRight();
			}
		}
		return null;
	}
	
}
