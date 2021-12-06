package implentations;

import api.GeoLocation;
import api.NodeData;

import static org.junit.jupiter.api.Assertions.*;

class NodeDataImplTest {

    @org.junit.jupiter.api.Test
    void getKey() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));
        NodeData n2 = new NodeDataImpl(10,new GeoLocationImpl(0D,0D,0D));
        NodeData n3 = new NodeDataImpl(115,new GeoLocationImpl(0D,0D,0D));

        assertEquals(n1.getKey(),1);
        assertEquals(n2.getKey(),10);
        assertEquals(n3.getKey(),115);
    }

    @org.junit.jupiter.api.Test
    void getLocation() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setLocation(new GeoLocationImpl(0,3,50));

        GeoLocation g = n1.getLocation();
        assertEquals(g.x(),0);
        assertEquals(g.y(),3);
        assertEquals(g.z(),50);
    }

    @org.junit.jupiter.api.Test
    void setLocation() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setLocation(new GeoLocationImpl(0,3,50));

        GeoLocation g = n1.getLocation();
        assertEquals(g.x(),0);
        assertEquals(g.y(),3);
        assertEquals(g.z(),50);
    }

    @org.junit.jupiter.api.Test
    void getWeight() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setWeight(10);
        assertEquals(n1.getWeight(),10);
    }

    @org.junit.jupiter.api.Test
    void setWeight() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setWeight(10);
        assertEquals(n1.getWeight(),10);
    }

    @org.junit.jupiter.api.Test
    void getInfo() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setInfo("test test");
        assertEquals(n1.getInfo(),"test test");
    }

    @org.junit.jupiter.api.Test
    void setInfo() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setInfo("test test");
        assertEquals(n1.getInfo(),"test test");
    }

    @org.junit.jupiter.api.Test
    void getTag() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setTag(10);
        assertEquals(n1.getTag(),10);
    }

    @org.junit.jupiter.api.Test
    void setTag() {
        NodeData n1 = new NodeDataImpl(1,new GeoLocationImpl(0D,0D,0D));

        n1.setTag(10);
        assertEquals(n1.getTag(),10);
    }

    @org.junit.jupiter.api.Test
    void addEdgeTo() {
    }

    @org.junit.jupiter.api.Test
    void addEdgeFrom() {
    }

    @org.junit.jupiter.api.Test
    void removeEdgeTo() {
    }

    @org.junit.jupiter.api.Test
    void removeEdgeFrom() {
    }

    @org.junit.jupiter.api.Test
    void getEdgeByDest() {
    }

    @org.junit.jupiter.api.Test
    void getEdgeIter() {
    }

    @org.junit.jupiter.api.Test
    void getTransposedEdgeIter() {
    }
}