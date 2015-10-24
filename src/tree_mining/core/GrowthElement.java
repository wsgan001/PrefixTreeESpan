package tree_mining.core;

/**
 * This is the structure of growth element in the PrefixTreeESpan algorithm
 * @author Jiajun
 *
 */
public class GrowthElement {

	//the grow element's int value
	private int nodeValue;
	//the position from which this grow element grows
	private int growPosition;
	/**
	 * 
	 * @param value the grow element's int value
	 */
	public GrowthElement(int value){
		this(value, 0);
	}
	/**
	 * 
	 * @param value the grow element's int value
	 * @param parentPos the position from which this grow element grows
	 */
	public GrowthElement(int value, int parentPos){
		this.nodeValue = value;
		this.growPosition = parentPos;
	}
	/**
	 * set the grow element's int value
	 * @param value
	 */
	public void setNodeValue(int value){
		this.nodeValue = value;
	}
	/**
	 * get the grow element's int value 
	 * @return
	 */
	public int getNodeValue(){
		return this.nodeValue;
	}
	/**
	 * get the grow element's parent position
	 * @return
	 */
	public int getGrowPosition() {
		return growPosition;
	}
	/**
	 * set the grow element's parent position
	 * @param growPosition
	 */
	public void setGrowPosition(int growPosition) {
		this.growPosition = growPosition;
	}
	
	@Override
	public String toString() {
		return "[GrowthElement: <value,"+this.nodeValue+">,<growth-position,"+this.growPosition+"> ]";
	}
	
}
