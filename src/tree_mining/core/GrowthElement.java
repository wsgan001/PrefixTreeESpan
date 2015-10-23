package tree_mining.core;

public class GrowthElement {

	private int nodeValue;
	private int growPosition;
	private int count;
	
	public GrowthElement(int value){
		this(value, 0);
	}
	
	public GrowthElement(int value, int parentPos){
		this.nodeValue = value;
		this.growPosition = parentPos;
	}
	
	public void setNodeValue(int value){
		this.nodeValue = value;
	}
	
	public int getNodeValue(){
		return this.nodeValue;
	}
	
	public int getGrowPosition() {
		return growPosition;
	}

	public void setGrowPosition(int growPosition) {
		this.growPosition = growPosition;
	}

	public void incrementCount(){
		this.count ++;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public int getCount(){
		return this.count;
	}
	
	@Override
	public String toString() {
		return "[GrowthElement: <value,"+this.nodeValue+">,<growth-position,"+this.growPosition+"> ]";
	}
	
}
