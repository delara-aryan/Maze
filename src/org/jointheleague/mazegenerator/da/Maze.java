package org.jointheleague.mazegenerator.da;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Maze{
	
	private final int rows;
	private final int columns;	
	private Node[][] nodes;
	private Edge[][] horizontalEdges;
	private Edge[][] verticalEdges;
	
	public Maze(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		initialize();
	}

	public void initialize() {
		// Initialize nodes and edges
		nodes = new Node[rows][columns];
		horizontalEdges = new Edge[rows][columns - 1];
		verticalEdges = new Edge[rows - 1][columns];
		
		// Create nodes
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				nodes[i][j] = new Node(i, j);
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
	
	public List<Edge> generateMST() {
		List<Edge> edges = new ArrayList<Edge>();
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		for (Edge e : nodes[0][0].getAdjacentEdges()) {
			queue.add(e);
		}
		nodes[0][0].setConnected(true);
		while (!queue.isEmpty()) {
			Edge currentEdge = queue.remove();
			if (currentEdge.getNode1().isConnected() && currentEdge.getNode2().isConnected()) {
				continue;
			}
			final Node currentNode = currentEdge.getNode1().isConnected()
									? currentEdge.getNode2()
									: currentEdge.getNode1();
			currentNode.setConnected(true);
			edges.add(currentEdge);
			for (Edge e : currentNode.getAdjacentEdges()) {
				if (!(e.getNode1().isConnected() && e.getNode2().isConnected())) {
					queue.add(e);
				}
			}
		}
		return edges;
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
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
}