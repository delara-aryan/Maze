package org.jointheleague.mazegenerator.da;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testNodeConstructor() {
		Node n = new Node(0, 0);
		assertEquals(0, n.getRow());
		assertEquals(0, n.getColumn());
	}

}
