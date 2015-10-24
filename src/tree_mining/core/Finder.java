package tree_mining.core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import tree_mining.util.Pair;

/**
 * Function class : In this class, provide the core algorithm of PrefixTreeESpan
 * @author Jiajun
 *
 */
public class Finder {
	//store the tree set, from which we find the sub-tree
	private List<TreeStruct> treeList = null;
	//store given support value
	int support;
	//file writer handler
	FileWriter fw = null;

	/**
	 * @param list : tree set
	 */
	public Finder(List<TreeStruct> list){
		this(list, 1, null);
	}
	/**
	 * @param list : tree set
	 * @param support : support value
	 * @param fw : file writer handler
	 */
	public Finder(List<TreeStruct> list, int support, FileWriter fw){
		this.treeList = list;
		this.support = support;
		this.fw = fw;
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
	public void getFrequenceSubTree(){
		
		List<Pair<TreeStruct, List<Integer>>> parentTreeList = new ArrayList<>();
		
		for(TreeStruct tree : this.treeList)
		{
			Pair<TreeStruct, List<Integer>> pair = new Pair<TreeStruct, List<Integer>>(tree, new ArrayList<>());
			parentTreeList.add(pair);
		}
		
		SubTreeStruct subTree = new SubTreeStruct(parentTreeList);
		frequenceSubTree(subTree);
	}
	/**
	 * recursive function that using the given SubTreeStruct to expand
	 * @param subTree to be expanded
	 * @return the expended subTree list
	 */
	public List<String> frequenceSubTree(SubTreeStruct subTree){
		List<String> result = new ArrayList<>();
		List<SubTreeStruct> subList = new ArrayList<>();
		List<GrowthElement> growthElementSet = subTree.getGrowthElement();
		
		for(GrowthElement ge : growthElementSet){
			SubTreeStruct sub = subTree.expandSubtree(ge);
			if(sub.getSupport() < this.support){
				continue;
			}
			subList.add(sub);
		}
		
		if(subList.isEmpty() && subTree.getSupport() >= this.support){
//			System.out.println(subTree.getSubTreeSequence()+"\nfrequency = "+ subTree.getSupport());
			try {
				fw.write(subTree.getSubTreeSequence()+"\nfrequency = "+ subTree.getSupport()+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
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
