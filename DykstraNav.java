import java.util.LinkedList;
import java.util.ArrayList;

public class DykstraNav {

	public LinkedList<NewNode> getShortestPathFromTo(NewGraph graph, String fromName, String toName) {
		LinkedList<NewNode> result = new LinkedList<NewNode>();
		
		//double distance = graph.getDistanceBetween(fromName, toName);
		//NewNode startingNode = graph.getNode(fromName);
		//System.out.println("distance: " + distance);
			
		// start at from node, for each connection mark distance, mark node as "processed" and move to first "unprocessed"
		// connection, repeat
		// if a distance is reduced, mark who reduced the distance as "who made me smaller"

		// pass in the first node and an empty done list
		ArrayList<NewNode> doneList = new ArrayList<NewNode>();
        NewNode fromNode = graph.getNode(fromName);
        fromNode.setDistance(0);
		markShortestDistances(fromNode, doneList, graph);

		// get the destination node, find who marked him shortest, go to him, repeat, output linked list, reverse list
		DistWrapper totalDistance = new DistWrapper();
		result = buildShorteningList(graph.getNode(toName), result, graph, totalDistance);		
		System.out.println("total distance: " + totalDistance.getDist());		

		// reverse result
		// result.add(new NewNode(0, 0, "El Paso"));
		return result;
	}

	private LinkedList<NewNode> buildShorteningList(NewNode currentNode, LinkedList<NewNode> list, NewGraph graph, DistWrapper dist) {
		//System.out.println("iter...");
		/*
		for(Object obj : list) {
  			System.out.println(obj);
		}
		*/
		if(!currentNode.getShorterNodeName().equals("")) {
			NewNode nextNode = graph.getNode(currentNode.getShorterNodeName());
			//System.out.println("adding " + currentNode.getName());
			list.add(currentNode);
			dist.addDist(currentNode.getDistance());
			list = buildShorteningList(nextNode, list, graph, dist);
		} else {
			//System.out.println(currentNode.getName() + " had no shortening name");
		}
		return list;
	}

	private void markShortestDistances(NewNode currentNode, ArrayList<NewNode> doneList, NewGraph graph) {
		System.out.println("NODE " + currentNode.getName());
		// for each connection, calculate distance and mark down distance on connection
		ArrayList<String> connectionNameList = currentNode.getConnectionNames();	
		for(int i = 0; i < connectionNameList.size(); i++) {
			NewNode currentConnection = graph.getNode(connectionNameList.get(i));
			double dist = graph.getDistanceBetween(currentNode.getName(), connectionNameList.get(i)) + currentNode.getDistance();
			double currentDist = currentConnection.getDistance();
			if(dist < currentDist) {
				System.out.println("found a shorter distance to " + currentConnection.getName() + ": " + dist);
				currentConnection.setShorterNodeName(currentNode.getName());
				currentConnection.setDistance(dist);
			} else {
                System.out.println("the new path ("+currentDist+") was not shorter to " + currentConnection.getName() + ": " + dist);
            }
		}
		doneList.add(currentNode);
		for(int j = 0; j < connectionNameList.size(); j++) {
			NewNode currentConnection = graph.getNode(connectionNameList.get(j));
			if(!isNodeInList(currentConnection, doneList)) {
				markShortestDistances(currentConnection, doneList, graph);
			}
		}
	}

	private boolean isNodeInList(NewNode node, ArrayList<NewNode> listToCheck) {
		boolean result = false;
		for(int i = 0; i < listToCheck.size(); i++) {
			if(listToCheck.get(i).getName().equals(node.getName())) {
				result = true;
			}
		}
		return result;
	}
	
}

class DistWrapper {

	private double mDist;

	public DistWrapper() {
		mDist = 0;
	}
	
	public void addDist(double dist) {
		mDist += dist;
	}

	public double getDist() {
		return mDist;
	}
}
