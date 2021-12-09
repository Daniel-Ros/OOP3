package implentations;

import api.DirectedWeightedGraph;
import api.GeoLocation;
import api.NodeData;

import java.util.Random;

public class GraphGenerator {
    public static DirectedWeightedGraph createRandomGraph(int nodes, int edgesFactor){
        DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
        Random r = new Random();
        r.setSeed(6942069);
        for (int i = 0; i < nodes; i++) {
            GeoLocation p = new GeoLocationImpl(r.nextInt() % 800,r.nextInt()%800,0);
            NodeData n = new NodeDataImpl(i,p);
            g.addNode(n);
        }

        for (int i = 0; i < nodes * edgesFactor; i++) {
            int src;
            int dest;
            do {
                src = r.nextInt() % nodes;
                dest = r.nextInt() % nodes;
            }while (src < 0 || dest < 0 || src == dest || g.getEdge(src,dest) != null);
            g.connect(src,dest,r.nextDouble()*2);
        }
        return g;
    }
}
