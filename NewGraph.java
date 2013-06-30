
import java.util.ArrayList;

public class NewGraph {

	private ArrayList<NewNode> mNodes;

	private String[][] mCartMap;

	private String[] mCartMapLegend;

	private int mCurrentLegendIndex = 0;

	public NewGraph(int mapXDim, int mapYDim) {
		mNodes = new ArrayList<NewNode>();
		mCartMap = new String[mapYDim][mapXDim];
		for(int i = 0; i < mapYDim; i++) {
			mCartMap[i] = new String[mapXDim];
			for(int j = 0; j < mapXDim; j++) {
				mCartMap[i][j] = " ";
			}
		}
		mCartMapLegend = new String[mapXDim*mapYDim];
	}

	public ArrayList<String> getConnectionNames(String nodeName) {
		return getNode(nodeName).getConnectionNames();
	}

	public void addNode(int x, int y, String name) {
		mNodes.add(new NewNode(x, y, name));
		mCartMapLegend[mCurrentLegendIndex] = name;
		mCartMap[y][x] = Integer.toString(mCurrentLegendIndex);
		mCurrentLegendIndex++;
	}

	public void makeConnectionBetween(String nameA, String nameB) {
		for(int i = 0; i < mNodes.size(); i++) {
			if(mNodes.get(i).getName().equals(nameA)) {
				mNodes.get(i).addConnection(nameB);
			} else if(mNodes.get(i).getName().equals(nameA)) {
				mNodes.get(i).addConnection(nameA);
			}	
		}
	}

	public NewNode getNode(String nodeName) {
		NewNode result = null;
		for(int i = 0; i < mNodes.size(); i++) {
			if(mNodes.get(i).getName().equals(nodeName)) {
				result = mNodes.get(i);
			}
		}
		return result;
	}

	public double getDistanceBetween(String nodeNameA, String nodeNameB) {
		NewNode nodeA = getNode(nodeNameA);
		NewNode nodeB = getNode(nodeNameB);
		return Math.pow(Math.pow(nodeA.getX() - nodeB.getX(), 2) + Math.pow(nodeA.getY() - nodeB.getY(), 2), 0.5); 
	}

	public void printMap() {
		System.out.println("MAP: " + mCartMap[0].length + " x " + mCartMap.length);
		for(int i = 0; i < mCartMap.length; i++) {
			for(int j = 0; j < mCartMap[i].length; j++) {
				System.out.print("["+mCartMap[i][j]+"]");
			}
			System.out.print("\n");
		}

		for(int k = 0; k < mCartMapLegend.length; k++) {
			if(mCartMapLegend[k] != null) {
				System.out.println(k + ": " + mCartMapLegend[k]);
			}
		}
	}

}


