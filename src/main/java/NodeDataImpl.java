import api.Drawable;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

public class NodeDataImpl implements NodeData, Drawable {
    private int id,tag;
    private double weight;
    private GeoLocation geoLocation;
    private String info;

    private HashMap<Integer, EdgeData> toEdges;
    private HashMap<Integer, EdgeData> fromEdges;

    public NodeDataImpl(int id, int tag, GeoLocation geoLocation, String info) {
        this.id = id;
        this.tag = tag;
        this.geoLocation = geoLocation;
        this.info = info;
        this.toEdges = new HashMap<>();
    }

    @Override
    public int getKey() {
        return id;
    }

    @Override
    public GeoLocation getLocation() {
        return geoLocation;
    }

    @Override
    public void setLocation(GeoLocation p) {
        geoLocation = p;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
        weight =w;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
        info = s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag = t;
    }

    public void addEdgeTo(EdgeData e){
        toEdges.put(e.getDest(),e);
    }

    public void addEdgeFrom(EdgeData e){
        toEdges.put(e.getSrc(),e);
    }

    public void removeEdgeTo(EdgeData e){
        toEdges.remove(e);
    }

    public void removeEdgeFrom(EdgeData e){
        toEdges.remove(e);
    }

    public EdgeData getEdgeByDest(int dest){
        return toEdges.get(dest);
    }

    public Iterator<EdgeData> getEdgeIter(){
        return toEdges.values().iterator();
    }

    @Override
    public void draw() {

    }
}
