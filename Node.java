/*
Node.java

Represents a node that has a value, and connections for the purposes of simulating a graph.

*/

import java.util.ArrayList;

public class Node {

	private int mXPos;

	private int mYPos;

	private int mID;

	private ArrayList<Integer> mConnections = new ArrayList<Integer>(); 	

	private String mValue = "";

	private boolean mActive = false;

	public Node(int id, int x, int y) {
		mID = id;
		mXPos = x;
		mYPos = y;
	}

	public void addConnection(int id) {
		mConnections.add(id);
	}

	public boolean connectedTo(int index) {
		boolean result = false;
		for(int i = 0; i < mConnections.size(); i++) {
			if(mConnections.get(i) == index) {
				result = true;
			}
		}
		return result;
	}

	public ArrayList<Integer> getConnections() {
		return mConnections;
	}

	public int getId() {
		return mID;
	}

	public int[] getConnectionsAsArray() {
		int size = mConnections.size();
		int[] result = new int[size];
		for(int i = 0; i < size; i++) {
			result[i] = mConnections.get(i);
		}
		return result;
	}

	public String getValue() {
		return mValue;
	}

	public void setValue(String val) {
		mValue = val;
	}

	public int getX() {
		return mXPos;
	}

	public int getY() {
		return mYPos;
	}

	public void setActive() {
		mActive = true;
	}

	public boolean isActive() {
		return mActive;
	}
}
