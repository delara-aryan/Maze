package org.jointheleague.mazegenerator.da;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MazeTest {
	
	private Maze maze;
	
	@Before
	public void before() {
		maze = new Maze();
		maze.initialize();
	}

	@Test
	public void testInitializeHorizontal() {
		assertTrue(Maze.ROWS > 0);
		assertTrue(Maze.COLUMNS > 0);
		assertEquals(Maze.ROWS, maze.getHorizontalEdges().length);
		for (Edge[] row: maze.getHorizontalEdges()) {
			assertEquals(Maze.COLUMNS - 1, row.length);
		}
	}
	
	@Test
	public void testInitalizeVertical() {
		assertTrue(Maze.ROWS > 0);
		assertTrue(Maze.COLUMNS > 0);
		assertEquals(Maze.ROWS - 1, maze.getVerticalEdges().length);
		for (Edge[] row: maze.getVerticalEdges()) {
			assertEquals(Maze.COLUMNS, row.length);
		}
	}
	
	@Test
	public void testInitializeNodes() {
		assertNotNull(maze.getNodes());
	}
	
	@Test
	public void testAdjacentEdges() {
		Node [][] nodes = maze.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			for  (int j = 0; j < nodes[i].length; j++) {
				assertTrue(nodes[i][j].getAdjacentEdges().size() <= 4);
			}
		}
	}
	
	@Test
	public void testGenerateMST() {
		maze.generateMST();
		assertEquals(Maze.ROWS * Maze.COLUMNS - 1, maze.edges.size());
	}

}
