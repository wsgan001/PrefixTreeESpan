package tree_mining.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tree_mining.util.Pair;

import java.util.Set;

public class SubTreeStruct {

	private List<Pair<TreeStruct, List<Integer>>> parentTreeList;

	private String subTreeSequence;
	
	public SubTreeStruct(List<Pair<TreeStruct, List<Integer>>> parentTreeList) {
		this("", parentTreeList);
	}
	
	public SubTreeStruct(String treeSequence, List<Pair<TreeStruct, List<Integer>>> parentTreeList){
		this.subTreeSequence = treeSequence;
		this.parentTreeList = parentTreeList;
	}
	
	public int getSupport(){
		return this.parentTreeList.size();
	}
	
	public String getSubTreeSequence(){
		return this.subTreeSequence;
	}
	
	public List<Pair<TreeStruct, List<Integer>>> getParentTreeMsg(){
		return this.parentTreeList;
	}
	
	public Set<GrowthElement> getGrowthElement(){
		Set<GrowthElement> growthElementSet = new HashSet<GrowthElement>();
		Map<Integer, String> nodeMap = new HashMap<>();
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
		
		for(Entry<Integer, String> entry : nodeMap.entrySet()){
			String str[] = entry.getValue().split(",");
			for(String s : str){
				growthElementSet.add(new GrowthElement(entry.getKey(), Integer.parseInt(s)));
			}
		}
		
		return growthElementSet;
	}
	
	public SubTreeStruct expandSubtree(GrowthElement ge){
		List<Pair<TreeStruct, List<Integer>>> list = new ArrayList<>();
		StringBuffer newSequence = new StringBuffer();
//		newSequence.append(this.subTreeSequence +ge.getNodeValue()+",");
		int pos = ge.getGrowPosition();
		if(pos == -1){
			newSequence.append(this.subTreeSequence +ge.getNodeValue()+",-1,");
		}else{
			String str[] = this.subTreeSequence.split(",");
			for(int i = str.length; i >= 0 && pos > 0; i++){
				if(str[i].equals("-1")){
					pos--;
				}else{
					pos++;
				}
			}
		}
		
		
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
	
	class Compare implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			if(arg0 instanceof Integer && arg1 instanceof Integer){
				Integer a = (Integer) arg0;
				Integer b = (Integer) arg1;
				return a.intValue() - b.intValue();
			}
			return 0;
		}
	}

}
