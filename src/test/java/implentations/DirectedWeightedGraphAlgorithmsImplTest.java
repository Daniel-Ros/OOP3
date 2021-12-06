package implentations;

import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphAlgorithmsImplTest {

    DirectedWeightedGraphAlgorithms dwga = new DirectedWeightedGraphAlgorithmsImpl();

    @Test
    void init() {
        assertEquals(dwga.getGraph(),null);
        dwga.load("data/G1.json");
        assertNotEquals(dwga.getGraph(),null);
    }

    @Test
    void getGraph() {
        assertEquals(dwga.getGraph(),null);
        dwga.load("data/G1.json");
        assertNotEquals(dwga.getGraph(),null);
    }

    @Test
    void copy() {
        //TODO: create a copy
    }

    @Test
    void isConnected() {
        assertEquals(dwga.getGraph(),null);
        dwga.load("data/G1.json");
        assertTrue(dwga.isConnected());
        dwga.getGraph().addNode(new NodeDataImpl(9999,new GeoLocationImpl(0,0,0)));
        assertTrue(!dwga.isConnected());
    }

    @Test
    void shortestPathDist() {
        assertEquals(dwga.getGraph(),null);
        dwga.load("data/G1.json");
        double d = dwga.shortestPathDist(14,12);
        double test = dwga.getGraph().getEdge(14,13).getWeight() + dwga.getGraph().getEdge(13,12).getWeight();
        assertEquals(d,test);
    }

    @Test
    void shortestPath() {
        assertEquals(dwga.getGraph(),null);
        dwga.load("data/G1.json");
        List<NodeData> nodes = dwga.shortestPath(14,12);
        assertEquals(nodes.get(0),dwga.getGraph().getNode(14));
        assertEquals(nodes.get(1),dwga.getGraph().getNode(13));
        assertEquals(nodes.get(2),dwga.getGraph().getNode(12));
    }

    @Test
    void center() {
        assertEquals(dwga.getGraph(),null);
        dwga.load("data/G1.json");
        assertEquals(dwga.center(),8);
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }

    @Test
    void getMin() {
    }

    @Test
    void getMax() {
    }
}