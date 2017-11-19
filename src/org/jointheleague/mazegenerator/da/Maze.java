package org.jointheleague.mazegenerator.da;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Maze extends JPanel implements Runnable{
	
	static final int ROWS = 4;
	static final int COLUMNS = 6;
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	private Node[][] nodes;
	private Edge[][] horizontalEdges;
	private Edge[][] verticalEdges;
	
	public static void main(String[] args) {
		Maze m = new Maze();
		SwingUtilities.invokeLater(m);
	}
	
	private void setUpGUI() {
		JFrame frame = new JFrame();
		frame.add(this);
		int cellSize = 100;
		Dimension d = new Dimension(cellSize*COLUMNS, cellSize*ROWS);
		this.setPreferredSize(d);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
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

	@Override
	public void run() {
		setUpGUI();
	}
}