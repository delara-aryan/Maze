package org.jointheleague.mazegenerator.da;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Maze {
	
	static final int ROWS = 4;
	static final int COLUMNS = 6;
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	private Node[][] nodes;
	private Edge[][] horizontalEdges;
	private Edge[][] verticalEdges;
	
	public void initialize() {
		// Initialize nodes and edges
		nodes = new Node[ROWS][COLUMNS];
		horizontalEdges = new Edge[ROWS][COLUMNS - 1];
		verticalEdges = new Edge[ROWS - 1][COLUMNS];
		
		// Create nodes
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				nodes[i][j] = new Node();
			}
		}
		
		Random rand = new Random();
		// Create and set costs of horizontal edges
		for (int i = 0; i < horizontalEdges.length; i++) {
			for (int j = 0; j < horizontalEdges[i].length; j++) {
				Edge e = new Edge(nodes[i][j], nodes[i][j + 1], rand.nextInt(10) + 1);
				horizontalEdges[i][j] = e;
				nodes[i][j].addEdge(e);
				nodes[i][j + 1].addEdge(e);
			}
		}
		
		// Create and set costs of vertical edges
		for (int i = 0; i < verticalEdges.length; i++) {
			for (int j = 0; j < verticalEdges[i].length; j++) {
				Edge e = new Edge(nodes[i][j], nodes[i + 1][j], rand.nextInt(10) + 1);
				verticalEdges[i][j] = e;
				nodes[i][j].addEdge(e);
				nodes[i + 1][j].addEdge(e);
			}
		}
	}
	
	public void generateMST() {
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		for (Edge e : nodes[0][0].getAdjacentEdges()) {
			queue.add(e);
		}
		int i = 0;
		while (!queue.isEmpty()) {
			Edge currentEdge = queue.remove();
			if (currentEdge.getNode1().isConnected() && currentEdge.getNode2().isConnected()) {
				continue;
			}
			edges.add(currentEdge);
			edges.get(i).getNode1().setConnected(true);
			edges.get(i).getNode2().setConnected(true);
			for (Edge e : edges.get(i).getNode1().getAdjacentEdges()) {
				if (!(edges.contains(e) || queue.contains(e))) {
					queue.add(e);
				}
			}
			for (Edge e : edges.get(i).getNode2().getAdjacentEdges()) {
				if (!(edges.contains(e) || queue.contains(e))) {
					queue.add(e);
				}
			}
			i++;
		}
	}
	
	public Edge[][] getHorizontalEdges() {
		return horizontalEdges;
	}

	public Edge[][] getVerticalEdges() {
		return verticalEdges;
	}

	public Node[][] getNodes() {
		return nodes;
	}
}