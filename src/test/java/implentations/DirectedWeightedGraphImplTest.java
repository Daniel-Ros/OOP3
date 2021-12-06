package implentations;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphImplTest {

    DirectedWeightedGraphAlgorithms dwga = new DirectedWeightedGraphAlgorithmsImpl();

    @Test
    void getNode() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();

        assertEquals(g.getNode(1).getTag(), 0);
        assertEquals(g.getNode(-1), null);
    }

    @Test
    void getEdge() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();

        assertEquals(g.getEdge(0,1).getTag(), 0);
        assertEquals(g.getEdge(0,1).getSrc(), 0);
        assertEquals(g.getEdge(0,1).getDest(), 1);
        assertEquals(g.getEdge(0,1).getWeight(), 1.232037506070033);
        assertEquals(g.getEdge(0,999), null);

    }

    @Test
    void addNode() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();
        int mc = g.getMC();
        g.addNode(new NodeDataImpl(100,new GeoLocationImpl(0,0,0)));
        assertEquals(mc +1 , g.getMC());
        assertEquals(g.getNode(100).getTag(), 0);
    }

    @Test
    void connect() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();
        int mc = g.getMC();
        g.connect(11,14,1);
        assertNotEquals(mc,g.getMC());
        assertEquals(g.getEdge(11,14).getWeight(), 1);
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();
        int mc = g.getMC();
        g.removeNode(1);
        assertNotEquals(mc,g.getMC());
        assertEquals(g.getEdge(0,1), null);
        assertEquals(g.getNode(1), null);

    }

    @Test
    void removeEdge() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();
        int mc = g.getMC();
        g.removeEdge(0,1);
        assertNotEquals(mc,g.getMC());
        assertEquals(g.getEdge(0,1), null);
    }

    @Test
    void nodeSize() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();
        int mc = g.getMC();
        assertEquals(g.nodeSize(), 17);
    }

    @Test
    void edgeSize() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();
        int mc = g.getMC();
        assertEquals(g.edgeSize(), 36);
    }

    @Test
    void getMC() {
        dwga.load("data/G1.json");
        DirectedWeightedGraph g = dwga.getGraph();
        int mc = g.getMC();
        g.removeNode(1);
        assertNotEquals(mc,g.getMC());
    }
}