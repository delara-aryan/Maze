package org.jointheleague.mazegenerator.da;

import java.util.ArrayList;

public class Node {
	
	private boolean connected;
	private ArrayList<Edge> edges;
	private final int row;
	private final int column;
	
	public Node(int row, int column) {
		this.row = row;
		this.column = column;
		edges = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
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
