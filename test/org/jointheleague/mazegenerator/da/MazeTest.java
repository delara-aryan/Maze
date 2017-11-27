package org.jointheleague.mazegenerator.da;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MazeTest {
	
	private Maze maze;
	
	@Before
	public void before() {
		maze = new Maze(6, 4);
	}

	@Test
	public void testInitializeHorizontal() {
		assertTrue("Number of rows should be greater than 0", maze.getRows() > 0);
		assertTrue(maze.getColumns() > 0);
		assertEquals(maze.getRows(), maze.getHorizontalEdges().length);
		for (Edge[] row: maze.getHorizontalEdges()) {
			assertEquals(maze.getColumns() - 1, row.length);
		}
	}
	
	@Test
	public void testInitalizeVertical() {
		assertTrue(maze.getRows() > 0);
		assertTrue(maze.getColumns() > 0);
		assertEquals(maze.getRows() - 1, maze.getVerticalEdges().length);
		for (Edge[] row: maze.getVerticalEdges()) {
			assertEquals(maze.getColumns(), row.length);
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
		List<Edge> edges = maze.generateMST();
		assertEquals(maze.getRows() * maze.getColumns() - 1, edges.size());
	}

}
