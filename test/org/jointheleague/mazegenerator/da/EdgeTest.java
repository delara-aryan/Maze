package org.jointheleague.mazegenerator.da;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class EdgeTest {

	@Test
	public void testImplementsComparable() {
		Edge edge = new Edge(new Node(), new Node(), 13);
		assertTrue("expected to implement Comaparable<Edge>", edge instanceof Comparable);
	}
	
	@Test
	public void testCompareTo() {
		Edge e1 = new Edge(new Node(), new Node(), 2);
		Edge e2 = new Edge(new Node(), new Node(), 7);
		Edge e3 = new Edge(new Node(), new Node(), 4);
		Edge[] edges = new Edge[]{e1, e2, e3};
		Arrays.sort(edges);
		Edge[] expected = new Edge[]{e1, e3, e2};
		assertArrayEquals(expected, edges);
	}

}
