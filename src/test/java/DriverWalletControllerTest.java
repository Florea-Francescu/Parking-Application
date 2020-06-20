import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.Main;
import main.java.controllers.DriverWalletController;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class DriverWalletControllerTest extends ApplicationTest {

    private DriverWalletController wallet;
    private JSONObject jo;
    @Before
    public void setUp(){
        System.out.println("Before");
        wallet = new DriverWalletController();
        wallet.errorLabel = new Label();
        wallet.currencyLabel = new Label();
        wallet.codeField = new TextField();
        jo = new JSONObject();
        jo.put("Email", "andiflorea16@yahoo.com");
        jo.put("FirstName", "Andi");
        jo.put("LastName", "Florea");
        jo.put("Currency", "0");
        jo.put("RegistrationNumber", "TM18TSM");
        jo.put("Password", "1234");
    }

    @After
    public void after(){
        System.out.println("After");
    }

    @Test
    public void testTransaction(){
        wallet.codeField.setText("w3w");
        Main.currentUser = jo;
        wallet.handleOKbutton();
        Assert.assertNotEquals("Currency added in your wallet", wallet.errorLabel.getText());
        System.out.println("ok");
    }

}
