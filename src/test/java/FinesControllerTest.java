import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import main.java.Main;
import main.java.controllers.FinesController;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class FinesControllerTest extends ApplicationTest {
    private FinesController fines;
    private JSONObject jo;
    @Before
    public void setUp(){
        System.out.println("Before");
        fines = new FinesController();
        fines.errorLabel = new Label();
        fines.amountLabel = new Label();
        fines.halfButton = new RadioButton();
        fines.allButton = new RadioButton();
        jo = new JSONObject();
        jo.put("Email", "andiflorea16@yahoo.com");
        jo.put("FirstName", "Andi");
        jo.put("LastName", "Florea");
        jo.put("Currency", "0");
        jo.put("RegistrationNumber", "TM18TSM");
        jo.put("Password", "1234");

    }

    @After
    public void After()
    {
        System.out.println("After");
    }

    @Test
    public void testFineTransaction(){
        fines.amountLabel.setText("100");
        Main.currentUser = jo;
        fines.handlePayButton();
        if(fines.allButton.isSelected())
            Assert.assertNotEquals("All your debts were paid", fines.errorLabel.getText());
        else if(fines.halfButton.isSelected())
            Assert.assertNotEquals("Half of your debts were paid", fines.errorLabel.getText());
        System.out.println("ok");
    }
}
