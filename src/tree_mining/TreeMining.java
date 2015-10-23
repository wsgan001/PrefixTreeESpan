package tree_mining;

import java.util.List;

import tree_mining.core.Finder;
import tree_mining.core.TreeStruct;
import tree_mining.util.ReadFile;

public class TreeMining {

	public static void main(String[] args) {
		
		List<TreeStruct> list = ReadFile.readTreeData("test3.data");
		
		System.out.println("Read date finished!");
		
		long startMili=System.currentTimeMillis();
		
		Finder finder = new Finder(list, 2);
		
		List<String> subTreeList = finder.getFrequenceSubTree();
		
		long endMili=System.currentTimeMillis();
		
		System.out.println("��ʱ�䣺"+(endMili-startMili));
		
		for(String s : subTreeList){
			System.out.println(s);
		}
		
	}
}
