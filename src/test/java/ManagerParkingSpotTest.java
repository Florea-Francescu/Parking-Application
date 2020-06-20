import main.java.entities.ManagerParkingSpot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class ManagerParkingSpotTest extends ApplicationTest {
    private ManagerParkingSpot test;

    @Before
    public void before(){
        test = new ManagerParkingSpot("", "", "", "", "");
    }

    @Test
    public void testGettersAndSetters(){
        test.setClaimed("TEST");
        test.setFloor("1");
        test.setId("D1");
        test.setPrice("123");
        test.setStatus("Available");

        Assert.assertEquals("TEST", test.getClaimed());
        Assert.assertEquals("1", test.getFloor());
        Assert.assertEquals("D1", test.getId());
        Assert.assertEquals("123", test.getPrice());
        Assert.assertEquals("Available", test.getStatus());
    }
}
