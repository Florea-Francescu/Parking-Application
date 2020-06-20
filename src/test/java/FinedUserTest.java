import main.java.entities.FinedUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class FinedUserTest extends ApplicationTest {
    FinedUser test;

    @Before
    public void before(){
        test = new FinedUser("", "", "", "");
    }

    @Test
    public void testGettersAndSetters(){
        test.setEmail("test@gmail.com");
        test.setFstName("Prenume");
        test.setLstName("Nume");
        test.setRegNumber("TEST");

        Assert.assertEquals("Prenume", test.getFstName());
        Assert.assertEquals("Nume", test.getLstName());
        Assert.assertEquals("test@gmail.com", test.getEmail());
        Assert.assertEquals("TEST", test.getRegNumber());
    }
}
