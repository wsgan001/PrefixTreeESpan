package tree_mining;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import tree_mining.core.Finder;
import tree_mining.core.TreeStruct;
import tree_mining.util.ReadFile;
/**
 * Frequent pattern mining of tree using PrefixTreeESpan algorithm.
 * @author Jiajun
 *
 */
public class TreeMining {

	public static void main(String[] args) {
		//file name that contains the data set
		//F5 100,000
		//D10 100,000
		//CSlog 60,000
		//T1M 1,000,000
		String fileName = "test2";
		//the minimal support value
		int support = 2;
		
		FileWriter fw = null;
		try {
			//open the target file to store running result
			fw = new FileWriter(new File("output/"+fileName+"_f_"+support+".out"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//read source file data
		List<TreeStruct> list = ReadFile.readTreeData("data/"+fileName+".data");
		//record the running start time
		long startMili=System.currentTimeMillis();
		//instantiate a new Finder to find the frequent pattern 
		Finder finder = new Finder(list, support, fw);
		//get the frequent sub-tree
		finder.getFrequenceSubTree();
		//record the ending time
		long endMili=System.currentTimeMillis();
		
		try {
			//output the total time used
			fw.write("Total time use : "+ (endMili-startMili)+"ms");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("run finish!");
		
	}
}
