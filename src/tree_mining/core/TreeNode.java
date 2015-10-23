package tree_mining.core;

public class TreeNode {

	private int value;
	private int selfPos;
	private int parentPos;
	private int preMinusCount;
	
	public TreeNode(){
	}
	
	public TreeNode(int value, int selfPos, int parentPos, int minusNum){
		this.value = value;
		this.selfPos = selfPos;
		this.parentPos = parentPos;
		this.preMinusCount = minusNum;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getSelfPos() {
		return selfPos;
	}

	public void setSelfPos(int selfPos) {
		this.selfPos = selfPos;
	}

	public int getParentPos() {
		return parentPos;
	}

	public void setParentPos(int parentPos) {
		this.parentPos = parentPos;
	}

	public int getPreMinusCount() {
		return preMinusCount;
	}

	public void setPreMinusCount(int preMinusCount) {
		this.preMinusCount = preMinusCount;
	}
	
}
