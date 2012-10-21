/**
BubbleSorter.java

Bubble Sort works by looping through the list at least once, start with the first item and switch it with the second
if the second is less than the first, else go to the second element in the list, if you are able to iterate through
the entire list without switching an element, then you have successfully sorted the array and you can exit.

*/


import java.util.ArrayList;

public class BubbleSorter {

	public ArrayList<Integer> sort(ArrayList<Integer> list) {
		int len = list.size();
		boolean sorted = true;
		int i = 0;
		do {
			sorted = true;
			while(i < len-1) {
				if(list.get(i) > list.get(i+1)) {
					//System.out.println("switching ["+i+"] = " + list.get(i) + ", with ["+(i+1)+"] = " + list.get(i+1));
					switchValue(list, i, i+1);
					sorted = false;		
				} else {
					i++;
				}
			}
			i = 0;
		} while(sorted == false);
		return list;
	}

	public void switchValue(ArrayList<Integer>list, int a, int b) {
		int valA = list.get(a);
		list.set(a, list.get(b));
		list.set(b, valA);
	}
}
