import java.util.Random;

import java.util.ArrayList;

public class Prep {

	public static int sMinArgs = 3;
	
	private static int sMaxNum = 100;

	private static int sTotalNum = 1000;

	public static void main(String [] args) {
		if(args.length >= sMinArgs) {
			
			Stopwatch myWatch = new Stopwatch();	
		
			/*
			ArrayList<Integer> sortMe = new ArrayList<Integer>();
			sortMe = resetList(sortMe, sTotalNum, sMaxNum);
			printArray(sortMe);

			QuickSorter myQS = new QuickSorter();
			myWatch.start();
			// sort using Quick sort
			sortMe = myQS.sort(sortMe);
			myWatch.stop();
			System.out.println("Elapsed time for quick sort: " + myWatch.getElapsedTime());
			//printArray(sortMe);
			myWatch.reset();

			sortMe = resetList(sortMe, sTotalNum, sMaxNum);
			BubbleSorter myBS = new BubbleSorter();
			myWatch.start();
			// sort using bubble sort	
			sortMe = myBS.sort(sortMe);
			myWatch.stop();
			System.out.println("Elapsed time for bubble sort: " + myWatch.getElapsedTime());
			//printArray(sortMe);
			myWatch.reset();
			
			//sortMe = resetList(sortMe, sTotalNum, sMaxNum);
			//MergeSorter myMS = new MergeSorter();
			//myWatch.start();
			// sort using bubble sort	
			//sortMe = myMS.sort(sortMe);
			//myWatch.stop();
			//System.out.println("Elapsed time for bubble sort: " + myWatch.getElapsedTime());
			//printArray(sortMe);
			//myWatch.reset();
			
			myWatch.start();
			int arraySize = Integer.parseInt(args[0]);
			JHash a = new JHash(arraySize);
			a.add("key1", "val1");
			a.add("key2", "val2");
			System.out.println("My key1 has value: " + a.get("key1"));
			
			myWatch.stop();
			System.out.println("Elapsed Time for a: " + myWatch.getElapsedTime());
			myWatch.reset();
			myWatch.start();

			JHash2 b = new JHash2(arraySize);
			b.add("key1", "val1");
			b.add("key2", "val2");
			System.out.println("My key1 has value: " + b.get("key1"));
			myWatch.stop();
			System.out.println("Elapsed Time for b: " + myWatch.getElapsedTime());
			*/

			//Graph problems - IN PROGRESS, FRAMEWORK COMPLETE, NOW THE HARD PART :)
			Graph myG = new Graph(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			// after we create the 10 x 10 grid we set a certain cercentage of the nodes to be active 
			//myG.seedRandomNodes(.5,Integer.parseInt(args[2]));
			myG.constructStartingNodes();
			// choose one random node from the field (not equal to home, which is 0,0 and is active)						
			//int targetIndex = myG.pickTarget();
			// find quickest path to this target
			myG.printStartingBoard();
			// print out the route and tell me the distance it would take
			myG.findShortestPath();
			//myG.printLongestPath();
			//myG.printFastestPath();
			// bonus, find paths that hit X,Y node (or any number of nodes) on the way

			
		} else {
			System.out.println("Must enter at least " + sMinArgs + " arguments...");
		}
	}

	public static void printArray(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println("");
	}

	public static ArrayList<Integer> resetList(ArrayList<Integer> sortMe, int totalNum, int maxNum) {
		sortMe = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i = 0; i < totalNum; i++) {
			sortMe.add(rand.nextInt(maxNum));
		}
		return sortMe;
	}
	
}