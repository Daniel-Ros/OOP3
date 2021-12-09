package implentations;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void run() {
        DirectedWeightedGraphAlgorithms ga = new DirectedWeightedGraphAlgorithmsImpl();
        ga.load("data/G1.json");

        Dijkstra d = new Dijkstra(0, ga.getGraph());
        d.run();

        HashMap<Integer,Double> distMap = d.getDistMap();

        for (Double dist: distMap.values()){
            assertNotEquals(dist,Double.POSITIVE_INFINITY);
        }

        assertTrue(distMap.get(2) < distMap.get(3));
        assertEquals(d.getPrevMap().get(3),2);
        assertEquals(d.getMaximumDist(),distMap.get(10));
    }
}