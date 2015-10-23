package tree_mining.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
	
	public Test(){
		List<Integer> set = new ArrayList<>();
		
		set.add(100);
		set.add(90);
		set.add(10);
		set.add(20);
		set.add(10);
		
		set.sort(new Compare());
		
		for(Integer ge : set){
			System.out.println(ge.toString());
		}
		
		
		String str = "1,2,";
		String s[]  = str.split(",");
		for(String a : s){
			System.out.println(a);
		}
		
	}
	
	public static void main(String[] args) {
		new Test();
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
