package tdp;

public class tester {
	public static void main(String [] args) {
		Graph g = new Graph();
		//Agrego nodos.
		g.addNode(2);
		g.addNode(4);
		g.addNode(5);
		g.addNode(7);
		g.addNode(5);
		//Agrego arcos.
		g.addEdge(2,4);
		g.addEdge(2,5);
		g.addEdge(4,5);
		g.addEdge(7,3);
		g.addEdge(5,2);
		//Elimino nodos.
		g.removeNode(5);
		g.removeNode(3);
		//Elimino arcos.
		g.removeEdge(2,4);
		g.removeEdge(5,2);
		g.removeEdge(7,4);
	}
}
