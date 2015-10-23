package tree_mining.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import tree_mining.util.Pair;

/**
 * Function class : In this class, provide the core algorithm of PrefixTreeESpan
 * @author Administrator
 *
 */
public class Finder {
	//store the tree set, from which we find the sub-tree
	private List<TreeStruct> treeList = null;
	//store given support value
	int support;
	
	public Finder(){
		this(null, 1);
	}
	/**
	 * @param list : tree set
	 */
	public Finder(List<TreeStruct> list){
		this(list, 1);
	}
	/**
	 * @param list : tree set
	 * @param support : support value
	 */
	public Finder(List<TreeStruct> list, int support){
		this.treeList = list;
		this.support = support;
	}
	/**
	 * set the tree set
	 * @param list : tree set list
	 */
	public void setTreeList(List<TreeStruct> list){
		this.treeList = list;
	}
	/**
	 * set the support value
	 * @param support : support value
	 */
	public void setSupport(int support){
		this.support = support;
	}
	/**
	 * get all frequent sub-tree that meet the support value
	 * @return a list that contains all the sub-tree sequences
	 */
	public List<String> getFrequenceSubTree(){
		List<String> result;
		
		List<Pair<TreeStruct, List<Integer>>> parentTreeList = new ArrayList<>();
		
		for(TreeStruct tree : this.treeList)
		{
			Pair<TreeStruct, List<Integer>> pair = new Pair<TreeStruct, List<Integer>>(tree, new ArrayList<>());
			parentTreeList.add(pair);
		}
		
		SubTreeStruct subTree = new SubTreeStruct(parentTreeList);
		result = frequenceSubTree(subTree);
		
		return result;
	}
	
	public List<String> frequenceSubTree(SubTreeStruct subTree){
		List<String> result = new ArrayList<>();
		List<SubTreeStruct> subList = new ArrayList<>();
		Set<GrowthElement> growthElementSet = subTree.getGrowthElement();
		
//		System.out.println("========================================");
//		System.out.println("当前的子树序列为："+subTree.getSubTreeSequence());
//		System.out.println("当前计算所有可扩展节点如下：");
//		for(GrowthElement g : growthElementSet){
//			System.out.println(g);
//		}
//		
//		System.out.println("当前拥有此子树序列的所有树如下");
//		
//		for(Pair<TreeStruct, List<Integer>> pair : subTree.getParentTreeMsg()){
//			System.out.println(pair.getFirst().toString()+"\\\\"+pair.getSecond());
//		}
		
		for(GrowthElement ge : growthElementSet){
			
			SubTreeStruct sub = subTree.expandSubtree(ge);
			if(sub.getSupport() < this.support)
				continue;
//			result.add(sub.getSubTreeSequence());
			subList.add(sub);
//			List<String> l = frequenceSubTree(sub);
//			for(String s : l){
//				result.add(s);
//			}
		}
		if(subList.isEmpty() && subTree.getSupport() >= this.support){
			result.add(subTree.getSubTreeSequence());
		}
		for(SubTreeStruct subtree : subList){
			List<String> l = frequenceSubTree(subtree);
			for(String s : l){
				result.add(s);
			}
		}
		return result;
	}
}
