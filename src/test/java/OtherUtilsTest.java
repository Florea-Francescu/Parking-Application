import junit.framework.Assert;
import junit.framework.TestCase;
import main.java.utils.OtherUtils;
import org.json.simple.JSONObject;

public class OtherUtilsTest extends TestCase {
    public OtherUtilsTest(String s)
    {
        super(s);
    }

    public void testIsSuspectFalse()
    {
        OtherUtils obj = new OtherUtils();
        Assert.assertEquals(false, obj.isSuspect("abc"));
    }
    public void testIsSuspectTrue()
    {
        OtherUtils obj = new OtherUtils();
        Assert.assertEquals(true, obj.isSuspect("TM18TSM"));
    }

    public void testGetUser(){
        JSONObject j1 = new JSONObject();
        j1.put("Email", "andiflorea16@yahoo.com");
        j1.put("FirstName", "Andi");
        j1.put("LastName", "Florea");
        j1.put("Currency", "0");
        j1.put("RegistrationNumber", "TM18TSM");
        j1.put("Password", "1234");
        Assert.assertEquals(j1, OtherUtils.getUser("TM18TSM"));
    }
}
