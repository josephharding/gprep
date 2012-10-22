
import java.util.Random;
import java.util.ArrayList;
import java.math.*;

public class Graph {

	public static final int BIG_NUM = 9999999;

	private ArrayList<Node> mNodes = new ArrayList<Node>();

	private ArrayList<Integer> mProcessedNodes = new ArrayList<Integer>();

	private ArrayList<Integer> mUnprocessedNodes = new ArrayList<Integer>();

	private double[] mDistanceArray;

	private int[] mMadeMeSmaller;

	private boolean[] mVisited;

	public Graph(int numNodesWidth, int numNodesHeight) {
		int uniqueNodeId = 0;
		for(int i = 0; i < numNodesWidth; i++) {
			for(int j = 0; j < numNodesHeight; j++) {
				mNodes.add(new Node(uniqueNodeId, i, j));
				uniqueNodeId++;
			}
		}
		mNodes.get(0).setActive();
		
		int len = mNodes.size();
		mDistanceArray = new double[len];
		mVisited = new boolean[len];
		mMadeMeSmaller = new int[len];
		for(int k = 0; k < len; k++) {
			mDistanceArray[k] = BIG_NUM;
			mVisited[k] = false;
			mMadeMeSmaller[k] = -1;
		}
	}
	
// the key to this is when constructing the "memory" array "point towards who made you smaller", so each node who
// set it to it's current (and lowest) value, then you can pass in your destination and ask each connected point
// who made him smaller, that will be get the shortest path in reverse
	public void constructStartingNodes() {
		addConnectionCoord(0,0,0,1);
		addConnectionCoord(0,1,0,2);
		addConnectionCoord(0,2,0,3);
		addConnectionCoord(0,3,3,3);

		addConnectionCoord(0,0,1,0);
		addConnectionCoord(1,0,2,0);
		addConnectionCoord(2,0,3,0);
		addConnectionCoord(3,0,2,2);
		addConnectionCoord(2,2,3,3);

		addConnectionCoord(0,0,2,2);
	}
	
	public int getIndexFromCoord(int x,int y) {
		int result = 0;
		for(int i = 0; i < mNodes.size(); i++) {
			if(mNodes.get(i).getX() == x && mNodes.get(i).getY() == y) {
				result = i;
				break;
			}
		}
		return result;
	}

	public void addConnectionCoord(int ax,int ay,int bx,int by) {
		int a = getIndexFromCoord(ax,ay);
		int b = getIndexFromCoord(bx,by);
		mNodes.get(a).addConnection(b);
		mNodes.get(b).addConnection(a);
	}

	/*
	Select the active nodes and than make connections between them
	*/
	public void seedRandomNodes(double percentActive, int numConnections) {
		Random rand = new Random();
		int totalNumNodes = mNodes.size();
		int numToMakeActive = (int)Math.floor(totalNumNodes*(Math.max(Math.min(percentActive,1),0)));
		// cycle through require num
		for(int i = 0; i < numToMakeActive; i++) {
			boolean setNewActiveNode = false;
			while(setNewActiveNode == false) {
				int nextRand = rand.nextInt(totalNumNodes);
				if(mNodes.get(nextRand).isActive() == false) {
					mNodes.get(nextRand).setActive();
					setNewActiveNode = true;
				}
			}
		}
		// create connections
		for(int j = 0; j < numConnections; j++) {
			boolean validPair = false;
			while(validPair == false) {
				int startIndex = rand.nextInt(totalNumNodes);
				int endIndex = rand.nextInt(totalNumNodes);
				if(startIndex != endIndex && !mNodes.get(startIndex).connectedTo(endIndex)) {
					if(mNodes.get(startIndex).isActive() && mNodes.get(endIndex).isActive()) {
						validPair = true;
						mNodes.get(startIndex).addConnection(endIndex);
						mNodes.get(endIndex).addConnection(startIndex);
					}
				}
			}
		}
	}

	public int pickTarget() {
		Random rand = new Random();
		int totalNumNodes = mNodes.size();
		boolean foundTarget = false;
		int targetIndex = 0;
		while(foundTarget == false) {
			targetIndex = rand.nextInt(totalNumNodes);
			if(mNodes.get(targetIndex).isActive()) {
				foundTarget = true;
			}
		}
		return targetIndex;
	}

	public void printStartingBoard() {
		int len = mNodes.size();
		for(int i = 0; i < len; i++) {
			if(mNodes.get(i).isActive()) {
				System.out.println("Node ("+mNodes.get(i).getX()+","+mNodes.get(i).getY()+") is active, connections:");
				ArrayList<Integer> nodeConnections = mNodes.get(i).getConnections();
				for(int j = 0; j < nodeConnections.size(); j++) {
					Node connection = mNodes.get(nodeConnections.get(j));
					System.out.print("(" + connection.getX() + ", " + connection.getY() + ")\n");
				}
			}
		}
	}

	public void findShortestPath(int targetX, int targetY) {
		int targetIndex = getIndexFromCoord(targetX, targetY);
		mDistanceArray[0] = 0.0;
		mUnprocessedNodes.add(0);
		calculateDistances(0);
		for(int j = 0; j < mDistanceArray.length; j++) {
			double distance = mDistanceArray[j];
			if(distance < BIG_NUM) {
				System.out.println("distance for index " + j + " (" + mNodes.get(j).getX() + 
				", " + mNodes.get(j).getY()  + ") : " + mDistanceArray[j]);
			}	
		}
		System.out.println("***Calculating Shortest Route***");
		printShortestRoute(targetIndex);
	}

	private void printShortestRoute(int targetIndex) {
		int madeMeSmallerIndex = mMadeMeSmaller[targetIndex];
		if(madeMeSmallerIndex>-1) {
			System.out.println("I'm index " + getXY(targetIndex) + " and " + getXY(madeMeSmallerIndex) + " made me smaller");
			printShortestRoute(madeMeSmallerIndex);
		}
	}

	private void dumpIntList(ArrayList<Integer> intList) {
		for(int i = 0; i < intList.size(); i++) {
			System.out.println("int list contains " + getXY(intList.get(i)));
		}
	}

	// this needs to be cleaned up big time, though it does appear to work
	private void calculateDistances(int currentNodeIndex) {
		while(mUnprocessedNodes.size() > 0) {
			int smallestDistanceIndex = getSmallestDistanceIndex();
			System.out.println("smallest distance node: " + getXY(smallestDistanceIndex));
			mProcessedNodes.add(smallestDistanceIndex);
			for(int k = 0; k < mUnprocessedNodes.size(); k++) {
				if(mUnprocessedNodes.get(k)==smallestDistanceIndex) {
					mUnprocessedNodes.remove(k);
				}
			}
			refreshSmallestDistances(smallestDistanceIndex);
			dumpIntList(mUnprocessedNodes);
		}
	}

	private void refreshSmallestDistances(int nodeIndex) {
		int[] connections = mNodes.get(nodeIndex).getConnectionsAsArray();
		for(int i = 0; i < connections.length; i++) {
			int connectionIndex = connections[i];
			if(!mProcessedNodes.contains(connectionIndex)) {
				double distance = getDistanceBetween(nodeIndex, connectionIndex);
				System.out.println("refreshing "+getXY(nodeIndex)+" distance: " + distance + " and existing distance is " + mDistanceArray[connectionIndex]);
				double newDist = distance + mDistanceArray[nodeIndex];
				if(mDistanceArray[connectionIndex] > newDist) {
					mDistanceArray[connectionIndex] = newDist;
					mMadeMeSmaller[connectionIndex] = nodeIndex;
					mUnprocessedNodes.add(connectionIndex);
				}
			}
		}
	}

	private int getSmallestDistanceIndex() {
		int smallestIndex = -1;
		double smallestDistance = 999999.0;
		for(int i = 0; i < mUnprocessedNodes.size(); i++) {
			int nodeIndex = mUnprocessedNodes.get(i);
			System.out.println("node index: " + nodeIndex + " has distance: " + mDistanceArray[nodeIndex]);
			if(smallestDistance > mDistanceArray[nodeIndex]) {
				smallestDistance = mDistanceArray[nodeIndex];
				smallestIndex = nodeIndex;
			}
		}
		return smallestIndex;
	}

	private void dumpOutBoolArray(boolean[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(getXY(i) + " has value: " + arr[i]);
		}
	}

	private void dumpOutIntArray(int[] arr) {
		for(int p = 0; p < arr.length; p++) {
			System.out.println("connection: " + p + " = " + getXY(arr[p])); 
		}
	}

	private double getDistanceBetween(int a, int b) {
		System.out.println("getting distance between " + getXY(a) + " and " + getXY(b));
		int aX = mNodes.get(a).getX();
		int aY = mNodes.get(a).getY();
		int bX = mNodes.get(b).getX();
		int bY = mNodes.get(b).getY();
		return Math.pow(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2) , 0.5);
	}

	private String getXY(int i) {
		return "(" + mNodes.get(i).getX()  + ", " + mNodes.get(i).getY()  + ")";
	}
}
