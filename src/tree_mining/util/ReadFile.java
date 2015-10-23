package tree_mining.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tree_mining.core.TreeStruct;

/**
 * tool class : read tree data from file
 * @author Administrator
 *
 */
public class ReadFile {

	/**
	 * read tree data from specific file
	 * @param fileFullName : containing the tree data (absolute or relative path included)
	 * @return a list of TreeStruct built from the given file
	 */
	public static List<TreeStruct> readTreeData(String fileFullName){
		
		List<TreeStruct> mList = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileFullName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String tempStr = null;
		try {
			while((tempStr = br.readLine()) != null){
				mList.add(new TreeStruct(tempStr));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			br.close();
		} catch (IOException e) {
			System.err.println("Close file \""+fileFullName+"\" failed!");
			e.printStackTrace();
		}
		return mList;
	}
}
