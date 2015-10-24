package tree_mining.core;

/**
 * Tree node structure
 * store some info of tree node
 * @author Jiajun
 *
 */
public class TreeNode {
	//node value
	private int value;
	//self position in the tree
	private int selfPos;
	//parent position in the tree
	private int parentPos;
	//the minus before the node
	private int preMinusCount;
	
	public TreeNode(){
	}
	/**
	 * 
	 * @param value
	 * @param selfPos
	 * @param parentPos
	 * @param minusNum
	 */
	public TreeNode(int value, int selfPos, int parentPos, int minusNum){
		this.value = value;
		this.selfPos = selfPos;
		this.parentPos = parentPos;
		this.preMinusCount = minusNum;
	}
	/**
	 * get node value
	 * @return
	 */
	public int getValue() {
		return value;
	}
	/**
	 * set node value
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * get node self position
	 * @return
	 */
	public int getSelfPos() {
		return selfPos;
	}
	/**
	 * set node self position
	 * @param selfPos
	 */
	public void setSelfPos(int selfPos) {
		this.selfPos = selfPos;
	}
	/**
	 * get parent's position
	 * @return
	 */
	public int getParentPos() {
		return parentPos;
	}
	/**
	 * set parent's position
	 * @param parentPos
	 */
	public void setParentPos(int parentPos) {
		this.parentPos = parentPos;
	}
	/**
	 * get the number of previous minus
	 * @return
	 */
	public int getPreMinusCount() {
		return preMinusCount;
	}
	/**
	 * set the number of previous minus
	 * @param preMinusCount
	 */
	public void setPreMinusCount(int preMinusCount) {
		this.preMinusCount = preMinusCount;
	}
	
}
