package implentations;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTest {

    void isConnected(DirectedWeightedGraphAlgorithms g) {
        System.out.println(g.isConnected());
    }

    void shortestPathDist(DirectedWeightedGraphAlgorithms g,int src,int dest) {
        g.shortestPathDist(src,dest);
    }

    void shortestPath(DirectedWeightedGraphAlgorithms g,int src,int dest) {
        g.shortestPathDist(src,dest);
    }

    void center(DirectedWeightedGraphAlgorithms g) {
        g.center();
    }


    void tsp(DirectedWeightedGraphAlgorithms g,List<NodeData> cities) {
        g.tsp(cities);
    }


    @Test()
    public void test1000(){
        DirectedWeightedGraph g = GraphGenerator.createRandomGraph(1000,20);
        DirectedWeightedGraphAlgorithms ga = new DirectedWeightedGraphAlgorithmsImpl();
        ga.init(g);
        System.out.println("\n\nTest 1000 Began");
        long start = System.currentTimeMillis();
        isConnected(ga);
        long stop = System.currentTimeMillis();
        System.out.println("isConnected took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPath(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPathDist(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        center(ga);
        stop = System.currentTimeMillis();
        System.out.println("center took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        tsp(ga,new ArrayList<>(Arrays.asList(g.getNode(0),g.getNode(100),g.getNode(200),g.getNode(300),g.getNode(700))));
        stop = System.currentTimeMillis();
        System.out.println("tso took " + (stop - start) + " MilliSeconds");
    }

    @Test
    public void test10000(){
        DirectedWeightedGraph g = GraphGenerator.createRandomGraph(10000,20);
        DirectedWeightedGraphAlgorithms ga = new DirectedWeightedGraphAlgorithmsImpl();
        ga.init(g);
        System.out.println("\n\nTest 10000 Began");
        long start = System.currentTimeMillis();
        isConnected(ga);
        long stop = System.currentTimeMillis();
        System.out.println("isConnected took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPath(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPathDist(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        tsp(ga,new ArrayList<>(Arrays.asList(g.getNode(0),g.getNode(100),g.getNode(200),g.getNode(300),g.getNode(700))));
        stop = System.currentTimeMillis();
        System.out.println("tso took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        center(ga);
        stop = System.currentTimeMillis();
        System.out.println("center took " + (stop - start) + " MilliSeconds");
    }

    @Test
    public void test100000(){
        DirectedWeightedGraph g = GraphGenerator.createRandomGraph(100000,20);
        DirectedWeightedGraphAlgorithms ga = new DirectedWeightedGraphAlgorithmsImpl();
        ga.init(g);
        System.out.println("\n\nTest 100000 Began");
        long start = System.currentTimeMillis();
        isConnected(ga);
        long stop = System.currentTimeMillis();
        System.out.println("isConnected took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPath(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPathDist(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        tsp(ga,new ArrayList<>(Arrays.asList(g.getNode(0),g.getNode(100),g.getNode(200),g.getNode(300),g.getNode(700))));
        stop = System.currentTimeMillis();
        System.out.println("tso took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        center(ga);
        stop = System.currentTimeMillis();
        System.out.println("center took " + (stop - start) + " MilliSeconds");
    }

    @Test
    public void test1000000(){
        DirectedWeightedGraph g = GraphGenerator.createRandomGraph(1000000,20);
        DirectedWeightedGraphAlgorithms ga = new DirectedWeightedGraphAlgorithmsImpl();
        ga.init(g);
        System.out.println("\n\nTest 1000000 Began");
        long start = System.currentTimeMillis();
        isConnected(ga);
        long stop = System.currentTimeMillis();
        System.out.println("isConnected took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPath(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        shortestPathDist(ga,0,950);
        stop = System.currentTimeMillis();
        System.out.println("shortestPath took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        tsp(ga,new ArrayList<>(Arrays.asList(g.getNode(0),g.getNode(100),g.getNode(200),g.getNode(300),g.getNode(700))));
        stop = System.currentTimeMillis();
        System.out.println("tso took " + (stop - start) + " MilliSeconds");

        start = System.currentTimeMillis();
        center(ga);
        stop = System.currentTimeMillis();
        System.out.println("center took " + (stop - start) + " MilliSeconds");
    }



}
