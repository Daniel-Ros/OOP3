package implentations;

import api.GeoLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationImplTest {

    @Test
    void x() {
        GeoLocation g = new GeoLocationImpl(1,2,3);
        assertEquals(g.x(),1);
    }

    @Test
    void y() {
        GeoLocation g = new GeoLocationImpl(1,2,3);
        assertEquals(g.y(),2);
    }

    @Test
    void z() {
        GeoLocation g = new GeoLocationImpl(1,2,3);
        assertEquals(g.z(),3);
    }

    @Test
    void distance() {
        GeoLocation g = new GeoLocationImpl(3,4,0);
        GeoLocation z = new GeoLocationImpl(0,0,0);
        assertEquals(g.distance(z),5);
    }
}