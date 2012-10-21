/**
QuickSorter.java

Recursively sorts a list by picking the first item, and comparing all the rest of the items in the 
list to this first item, if those items are greater than the first, they go into the greater than
list, and if less they go into the less than list, those lists are then run through the same sort
function recursively, before they are concatenated together with the lesser than list, then the 
first element, then the greater than list.

n*log(n) order of convergence

*/


import java.util.ArrayList;

public class QuickSorter {
	
	public ArrayList<Integer> sort(ArrayList<Integer> list) {
		int listLen = list.size();	
		if(listLen > 0) {
			int comp = list.get(0);
			ArrayList<Integer> lesserList = new ArrayList<Integer>();
			ArrayList<Integer> greaterList = new ArrayList<Integer>();
			for(int i = 1; i < listLen; i++) {
				if(list.get(i) < comp) {
					//System.out.println("comparing " + comp  + " placing " + list.get(i) + " in lesser list");
					lesserList.add(list.get(i));
				} else {
					//System.out.println("comparing " + comp  + " placing " + list.get(i) + " in greater list");
					greaterList.add(list.get(i));
				}
			}
			return concat(sort(lesserList), comp, sort(greaterList));
		} else {
			return list;
		} 
	}

	public ArrayList<Integer> concat(ArrayList<Integer> a, int comp, ArrayList<Integer> b) {
		int aLen = a.size();
		int bLen = b.size();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < aLen; i++) {
			result.add(a.get(i));
		}
		result.add(comp);
		for(int j = 0; j < bLen; j++) {
			result.add(b.get(j));
		}
		return result;
	}

}
