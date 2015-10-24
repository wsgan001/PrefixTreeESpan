package tree_mining.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tree_mining.util.Pair;
/**
 * This is the sub-tree structure which store a state of a expanded node
 * some essential info are stored 
 * @author Jiajun
 *
 */
public class SubTreeStruct {
	//store the current sub tree state: all the trees are stored who contain the current sub-tree
	//the list in the Pair store the sequence of the grow position
	private List<Pair<TreeStruct, List<Integer>>> parentTreeList;
	//store the sub-tree's string value
	private String subTreeSequence;
	/**
	 * 
	 * @param parentTreeList
	 */
	public SubTreeStruct(List<Pair<TreeStruct, List<Integer>>> parentTreeList) {
		this("", parentTreeList);
	}
	/**
	 * 
	 * @param treeSequence
	 * @param parentTreeList
	 */
	public SubTreeStruct(String treeSequence, List<Pair<TreeStruct, List<Integer>>> parentTreeList){
		this.subTreeSequence = treeSequence;
		this.parentTreeList = parentTreeList;
	}
	/**
	 * get the frequency of current sub-tree
	 * @return
	 */
	public int getSupport(){
		return this.parentTreeList.size();
	}
	/**
	 * get current sub-tree's string value
	 * @return
	 */
	public String getSubTreeSequence(){
		return this.subTreeSequence;
	}
	/**
	 * get the parent trees that contain the current sub-tree
	 * @return
	 */
	public List<Pair<TreeStruct, List<Integer>>> getParentTreeMsg(){
		return this.parentTreeList;
	}
	/**
	 * get current sub-tree's available grow elements
	 * @return
	 */
	public List<GrowthElement> getGrowthElement(){
		List<GrowthElement> growthElementList = new ArrayList<>();
		Map<Integer, String> nodeMap = new HashMap<>();
		//initial state, the subTreeSequence is empty
		if(this.subTreeSequence.equals("")){
			for(Pair<TreeStruct, List<Integer>> p : parentTreeList){
				TreeStruct tree = p.getFirst();
				for(TreeNode node : tree.getTreeNodeList()){
					nodeMap.put(Integer.valueOf(node.getValue()), "-1,");
				}
			}
		}else{
			for(Pair<TreeStruct, List<Integer>> p : parentTreeList){
				TreeStruct tree = p.getFirst();
				List<Integer> matchPointList = p.getSecond();
				int len = matchPointList.size();
				TreeNode node = null;
				//find the available element whose grow position must in the candidates
				for(int i = matchPointList.get(len-1); i < tree.getTreeLength(); i++){
					node = tree.getTreeNode(i);
					if(node.getParentPos() < matchPointList.get(0) || node.getParentPos() > matchPointList.get(len-1))
						continue;
					if(matchPointList.contains(node.getParentPos())){
						int index = 0;
						for(; index < len; index ++){
							if(node.getParentPos() == matchPointList.get(index))
								break;
						}
						if(nodeMap.containsKey(node.getValue())){
							String s = nodeMap.get(node.getValue());
							if(!s.contains(String.valueOf(index))){
								nodeMap.put(Integer.valueOf(node.getValue()), s+index+",");
							}
						}else{
							nodeMap.put(Integer.valueOf(node.getValue()), index+",");
						}
					}
				}
			}
		}
		//change the storage form of the grow elements.
		for(Entry<Integer, String> entry : nodeMap.entrySet()){
			String str[] = entry.getValue().split(",");
			for(String s : str){
				growthElementList.add(new GrowthElement(entry.getKey(), Integer.parseInt(s)));
			}
		}
		
		return growthElementList;
	}
	/**
	 * expand the current sub-tree using the given grow element 
	 * @param ge grow element
	 * @return
	 */
	public SubTreeStruct expandSubtree(GrowthElement ge){
		List<Pair<TreeStruct, List<Integer>>> list = new ArrayList<>();
		StringBuffer newSequence = new StringBuffer();
		int pos = ge.getGrowPosition();
		//initial state
		if(pos == -1){
			newSequence.append(this.subTreeSequence +ge.getNodeValue()+",-1,");
		}else{ //insert the grow element into the sub-tree string value
			String str[] = this.subTreeSequence.split(",");
			
			int index = 0;
			//find the parent's position
			for(; index < str.length; index++){
				if(pos == 0)
					break;
				if(str[index].equals("-1")){
					continue;
				}
				pos --;
			}
			
			int minus = 1;
			//match the '-1' counting
			for(index++; index < str.length; index++){
				if(str[index].equals("-1")){
					minus --;
				}else{
					minus++;
				}
				if(minus == 0)
					break;
			}
			for(int i = 0; i < str.length; i++){
				if(i == index){
					newSequence.append(ge.getNodeValue()+",-1,");
				}
				newSequence.append(str[i]+",");
			}
			
		}
		
		//loop try to expand the tree from the current tree set 
		for(Pair<TreeStruct, List<Integer>> treePair : parentTreeList){
			TreeStruct tree = treePair.getFirst();
			List<Integer> matchPoint = treePair.getSecond();
			List<TreeNode> nodeList = tree.getTreeNodeList();
			int len = matchPoint.size();
			if(len == 0){
				for(TreeNode node : nodeList){
					if(node.getValue() == ge.getNodeValue()){
						List<Integer> l = new ArrayList<>();
						l.add(node.getSelfPos());
						Pair<TreeStruct, List<Integer>> pair = new Pair<TreeStruct, List<Integer>>(tree, l); 
						list.add(pair);
					}
				}
			}else{
				for(int i = matchPoint.get(len-1); i < tree.getTreeLength(); i++){
					TreeNode node = nodeList.get(i);
					if(!(node.getValue() == ge.getNodeValue()))
						continue;
					int index = 0;
					for(; index < matchPoint.size(); index++){
						if(matchPoint.get(index) == node.getParentPos())
							break;
					}
					if(index < matchPoint.size() && index == ge.getGrowPosition()){
						List<Integer> l = new ArrayList<>(matchPoint);
						l.add(node.getSelfPos());
						list.add(new Pair<TreeStruct, List<Integer>>(tree, l));
						break;
					}
				}
			}
		}
		
		return new SubTreeStruct(new String(newSequence), list);
	}

}
