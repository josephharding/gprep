
import java.util.ArrayList;

public class NewNode {

	/* the position in the x */
	private int mX;

	/* the position in the y */
	private int mY;

	/* generic name to uniquely identify node friendly */
	private String mName;

	/* list of node names this node is connected to */
	private ArrayList<String> mConnectionNames;
	
	/* the name of node that reduced the distance this node is from a target node */
	private String mShorterNodeName;

	/* the distance this node is from the target node */
	private double mDistance;
	
	public NewNode(int x, int y, String name) {
		mConnectionNames = new ArrayList<String>();
		mName = name;
		mX = x;
		mY = y;
		mShorterNodeName = "";
		mDistance = 99999;
	}

	public void addConnection(String nodeName) {
		mConnectionNames.add(nodeName);
	}

	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}

	public String getName() {
		return mName;
	}

	public ArrayList<String> getConnectionNames() {
		return mConnectionNames;
	}
		
	public void setShorterNodeName(String nodeName) {
		mShorterNodeName = nodeName;
	}

	public String getShorterNodeName() {
		return mShorterNodeName;
	}

	public void setDistance(double dist) {
		mDistance = dist;
	}

	public double getDistance() {
		return mDistance;
	}
	
	public String toString() {
		return getName();
	}	 

}
