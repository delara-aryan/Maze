package org.jointheleague.mazegenerator.da;

public class Edge {
	
	private final Node node1;
	private final Node node2;
	private final int cost;
	
	public Edge(Node node1, Node node2, int cost) {
		this.node1 = node1;
		this.node2 = node2;
		this.cost = cost;
	}

}
