package tree_mining.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * tree struct class : include tree data and some functions  used while matching subTree
 * @author Administrator
 *
 */
public class TreeStruct {
	//store tree in the String form
	private String treeSequence = null;
	//tree date in node form, each node is an Integer form (the backward path '-1' included) 
	private ArrayList<TreeNode> treeList = new ArrayList<>();
	//store the sequence that have been matched currently
	private String matchedSequence = null;
	
	public TreeStruct(String treeSequence){
		this.treeSequence = treeSequence;
		String str[] = treeSequence.split(" ");
		int preMinusCount = 0;
		int nodeCount = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(nodeCount);
		for(String s : str){
			if(s.equals("-1"))
			{
				preMinusCount ++;
				stack.pop();
				continue;
			}
			this.treeList.add(new TreeNode(Integer.parseInt(s), nodeCount+1, stack.peek(), preMinusCount));
			nodeCount ++;
			stack.push(nodeCount);
		}
	}
	
	public int getTreeLength(){
		return this.treeList.size();
	}
	
	public TreeNode getTreeNode(int index){
		return this.treeList.get(index);
	}
	
	public List<TreeNode> getTreeNodeList(){
		return this.treeList;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(TreeNode node : this.getTreeNodeList()){
			sb.append(node.getValue()+"\\"+node.getParentPos()+",");
		}
		
		return new String(sb);
	}
}
