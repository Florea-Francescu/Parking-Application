import main.java.entities.ParkingSpot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class ParkingSpotTest extends ApplicationTest {
    ParkingSpot test;

    @Before
    public void before(){
        test = new ParkingSpot("", "", "");
    }

    @Test
    public void testGettersAndSetters(){
        test.setFloor("1");
        test.setId("A2");
        test.setPricePerHour("34");

        Assert.assertEquals("1", test.getFloor());
        Assert.assertEquals("A2", test.getId());
        Assert.assertEquals("34", test.getPricePerHour());
    }
}
