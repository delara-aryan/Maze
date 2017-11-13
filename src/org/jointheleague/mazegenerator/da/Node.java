package org.jointheleague.mazegenerator.da;

import java.util.ArrayList;

public class Node {
	
	private boolean connected;
	private ArrayList<Edge> edges;
	
	public Node() {
		edges = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public ArrayList<Edge> getAdjacentEdges() {
		return edges;
	}
	
	public void setConnected(boolean b) {
		connected = b;
	}
	
	public boolean isConnected() {
		return connected;
	}
}
