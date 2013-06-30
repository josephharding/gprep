import java.util.Iterator;
import java.util.LinkedList;

public class GraphTester {

    public GraphTester() {

        NewGraph myGraph = new NewGraph(40, 20);

		/*

		New York -> Chicago
		New York -> Houston
		New York -> Miami

		Chicago -> Houston
		Chicago -> New York
		Chicago -> Denver

		Houston -> Chicago
		Houston -> New York
		Houston -> Austin
		Houston -> Miami

		Miami -> New York
		Miami -> Houston

		Austin -> San Francisco
		Austin -> Houston

		Denver -> San Francisco
		Denver -> Chicago

		*/

        myGraph.addNode(0, 0, "San Francisco");

        myGraph.addNode(17, 5, "Chicago");
        myGraph.addNode(30, 1, "Houston");

        myGraph.addNode(7, 5, "Austin");

        myGraph.addNode(18, 18, "Miami");
        myGraph.addNode(20, 19, "Denver");

        myGraph.addNode(39, 10, "New York");

        myGraph.makeConnectionBetween("New York", "Houston");
        myGraph.makeConnectionBetween("New York", "Austin");
        myGraph.makeConnectionBetween("New York", "Denver");

        myGraph.makeConnectionBetween("Houston", "Chicago");
        myGraph.makeConnectionBetween("Miami", "Denver");

        myGraph.makeConnectionBetween("San Francisco", "Chicago");
        myGraph.makeConnectionBetween("San Francisco", "Austin");
        myGraph.makeConnectionBetween("San Francisco", "Miami");

        myGraph.printMap();

        DykstraNav dNav = new DykstraNav();
        LinkedList<NewNode> path = dNav.getShortestPathFromTo(myGraph, "New York", "San Francisco");

        //LinkedList<NewNode> path = new LinkedList<NewNode>();
        //path.add(new NewNode(2, 4, "El Paso"));
        //path.add(new NewNode(6, 7, "Amarillo"));
        printLinkedList(path);
    }

    private void printLinkedList(LinkedList<NewNode> list) {
        System.out.println("PRINT");
		/*
		Iterator itr = list.iterator();
      	while(itr.hasNext()) {
       		NewNode node = (NewNode)itr.next();
         	System.out.println(node.getName());
      	}
		*/

        for(Object obj : list) {
            System.out.println(obj);
        }
    }

}
