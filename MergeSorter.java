/*
MergeSorter.java

Basic idea here is that you reconstruct a larger list in order from two smaller lists

Is there a way to have ONE recurise algo that would handle this?

*/

import java.lang.Integer;
import java.util.ArrayList;

public class MergeSorter {


    public ArrayList<Integer> sortv2(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int index1 = 0;
        int index2 = 0;
        while(index1 < list1.size() || index2 < list2.size()) {
            int value = -1;
            // if we have elements in both list, do sorting
            if(index1 < list1.size() && index2 < list2.size() ) {
                if(list2.get(index2) < list1.get(index1)) {
                    value = list2.get(index2);
                    index2++;
                } else {
                    value = list1.get(index1);
                    index1++;
                }
            } else if(index1 < list1.size()) {
                value = list1.get(index1);
                index1++;
            } else {
                value = list2.get(index2);
                index2++;
            }
            result.add(value);
        }
        return result;
    }


    public ArrayList<Integer> doSort(ArrayList<Integer> startList) {
        ArrayList<ArrayList<Integer>> convertedStartList = convertListToListofList(startList);
        ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();

        boolean done = false;
        while(!done) {
            for(int i = 0; i < convertedStartList.size(); i = i + 2) {
                if(i + 1 < convertedStartList.size()) {
                    returnList.add(sort(convertedStartList.get(i), convertedStartList.get(i+1)));
                } else {
                    returnList.add(convertedStartList.get(i));
                }
            }
            if(returnList.size() == 1) {
                done = true;
            } else {
                convertedStartList = returnList;
                returnList = new ArrayList<ArrayList<Integer>>();
            }
        }

        return returnList.get(0);
    }

    public ArrayList<ArrayList<Integer>> convertListToListofList(ArrayList<Integer> original) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < original.size(); i++) {
            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.add(original.get(i));
            result.add(newList);
        }
        return result;
    }

    public ArrayList<Integer> sort(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        // go through elements of each list, comparing first entries, add smaller one first, repeat until
        // both lists are empty
        int index1 = 0;
        int index2 = 0;
        while(index1 < list1.size() || index2 < list2.size()) {
            if(index1 < list1.size() && index2 < list2.size()) {
                if(list1.get(index1) < list2.get(index2)) {
                    //System.out.println("A: " + list1.get(index1));
                    resultList.add(list1.get(index1));
                    index1++;
                } else {
                    //System.out.println("B: " + list2.get(index2));
                    resultList.add(list2.get(index2));
                    index2++;
                }
            } else if(index1 >= list1.size()) {
                //System.out.println("C: " + list2.get(index2));
                resultList.add(list2.get(index2));
                index2++;
            } else {
                //System.out.println("D: " + list1.get(index1));
                resultList.add(list1.get(index1));
                index1++;
            }
            //System.out.println("end of cycle: ");
            printArray(resultList);
        }
        System.out.println("DONE");
        return resultList;
    }

    public static void printArray(ArrayList<Integer> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println("");
    }
}
