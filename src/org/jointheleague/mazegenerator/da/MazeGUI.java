package org.jointheleague.mazegenerator.da;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MazeGUI extends JPanel implements Runnable, ActionListener {

	private final int ROWS = 100;
	private final int COLUMNS = 100;
	private final int cellSize = 10;
	List<Edge> edges;
	Timer ticker;
	int numEdges = 1;
	BufferedImage image;
	private Graphics2D imageGraphics;

	public static void main(String[] args) {
		MazeGUI m = new MazeGUI();
		SwingUtilities.invokeLater(m);
	}

	private void setUpGUI() {
		JFrame frame = new JFrame();
		frame.add(this);
		int width = cellSize * COLUMNS + 1;
		int height = cellSize * ROWS + 1;
		Dimension d = new Dimension(width, height);
		this.setPreferredSize(d);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		edges = new Maze(ROWS, COLUMNS).generateMST();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		imageGraphics = image.createGraphics();
		imageGraphics.setColor(Color.BLACK);
		imageGraphics.fillRect(0, 0, getWidth(), getHeight());
		ticker = new Timer(10, this);
		ticker.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, null);
	}

	private void drawMaze(Graphics2D g2) {
		Stroke stroke = new BasicStroke(cellSize - 2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
		g2.setStroke(stroke);
		g2.setColor(Color.WHITE);
		int offSet = cellSize / 2 + 1;
		Edge e = edges.get(numEdges);
		int x1 = e.getNode1().getColumn() * cellSize + offSet;
		int y1 = e.getNode1().getRow() * cellSize + offSet;
		int x2 = e.getNode2().getColumn() * cellSize + offSet;
		int y2 = e.getNode2().getRow() * cellSize + offSet;
		g2.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void run() {
		setUpGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		drawMaze(imageGraphics);
		repaint();
		if (numEdges == edges.size()) {
			ticker.stop();
		} else {
			numEdges++;
		}
	}

}
