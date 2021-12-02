package implentations;

import api.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.naming.ldap.HasControls;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {

    DirectedWeightedGraph graph;
    GeoLocation min,max;


    /**
     * Inits the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        graph = g;
    }

    /**
     * Returns the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     *
     * @return
     */
    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from each node to each
     * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
     *
     * This algorithm,uses a DFS travel twice, once on the original graph and once on the trasposed graph,if both ot them
     * returns A tree in the size of our graph,then we return true
     * @return
     */
    @Override
    public boolean isConnected() {
        NodeData firstNode = graph.nodeIter().next();
        HashSet<NodeData> visited = new HashSet<>();
        Stack<NodeData> nextNodes = new Stack<>();
        nextNodes.push(firstNode);
        while(!nextNodes.isEmpty()){
            NodeData n = nextNodes.pop();
            Iterator<EdgeData> edges = graph.edgeIter(n.getKey());
            while (edges.hasNext()){
                EdgeData edge = edges.next();
                int dest = edge.getDest();
                if(!visited.contains(graph.getNode(dest)))
                    nextNodes.push(graph.getNode(dest));
            }
            visited.add(n);
        }

        if(visited.size() != graph.nodeSize()){
            return false;
        }

        visited = new HashSet<>();
        nextNodes.push(firstNode);

        while(!nextNodes.isEmpty()){
            NodeDataImpl n = (NodeDataImpl) nextNodes.pop();
            Iterator<EdgeData> edges = n.getTransposedEdgeIter();
            while (edges.hasNext()){
                EdgeData edge = edges.next();
                int src = edge.getSrc();
                if(!visited.contains(graph.getNode(src)))
                    nextNodes.push(graph.getNode(src));
            }
            visited.add(n);
        }

        if(visited.size() != graph.nodeSize()){
            return false;
        }

        return true;
    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        List<NodeData> path = shortestPath(src,dest);

        if(path == null)
            return -1;

        double ret = 0;
        for(int i = 1; i < path.size();i++){
            EdgeData e= getGraph().getEdge(path.get(i-1).getKey(),path.get(i).getKey());
            ret += e.getWeight();
        }

        return ret;
    }

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        HashMap<Integer,Double> dist = new HashMap<>();
        HashMap<Integer,Integer> prev = new HashMap<>();
        PriorityQueue<Integer> Q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (int)(dist.get(o1) - dist.get(o2));
            }
        });

        Q.add(src);
        while (!Q.isEmpty()){
            int n = Q.poll();
            Iterator<EdgeData> edges = graph.edgeIter(n);
            while (edges.hasNext()){
                EdgeData e = edges.next();
                int neighbor = e.getDest();
                double alt = dist.get(n) + e.getWeight();
                if (alt < dist.get(neighbor)){
                    dist.put(neighbor,alt);
                    prev.put(neighbor,n);
                }
            }
        }


        return null;
    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     *
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        return null;
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     *
     * @param cities
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return false;
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        double minx = Double.MAX_VALUE,miny = Double.MAX_VALUE,maxx= Double.MIN_VALUE,maxy = Double.MIN_VALUE;
        DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
        try {
            File input = new File(file);
            JsonElement elements = JsonParser.parseReader(new FileReader(input));
            JsonObject object = elements.getAsJsonObject();
            JsonArray nodes = object.get("Nodes").getAsJsonArray();
            JsonArray edges = object.get("Edges").getAsJsonArray();

            for (JsonElement n : nodes){
                String pos = n.getAsJsonObject().get("pos").getAsString();
                int id = n.getAsJsonObject().get("id").getAsInt();

                String [] positions = pos.split(",");
                double posx = Double.parseDouble(positions[0]);
                double posy = Double.parseDouble(positions[1]);
                double posz = Double.parseDouble(positions[2]);

                if(posx < minx)
                    minx = posx;
                else if(posx > maxx)
                    maxx  = posx;

                if(posy < miny)
                    miny = posy;
                else if(posy > maxy)
                    maxy  = posy;

                NodeData node = new NodeDataImpl(id,0,new GeoLocationImpl(posx,posy,posz),"");
                node.setInfo(String.valueOf(id));
                g.addNode(node);
            }

            for (JsonElement e : edges){
                int src = e.getAsJsonObject().get("src").getAsInt();
                int dest = e.getAsJsonObject().get("dest").getAsInt();
                double w = e.getAsJsonObject().get("w").getAsInt();

                g.connect(src,dest,w);
            }
            min = new GeoLocationImpl(minx,miny,0);
            max = new GeoLocationImpl(maxx,maxy,0);
            init(g);
            return true;
        }catch (FileNotFoundException fnfe){
            return false;
        }
    }

    public GeoLocation getMin() {
        return min;
    }

    public GeoLocation getMax() {
        return max;
    }

}
