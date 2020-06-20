import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.Main;
import main.java.controllers.LoginController;
import org.json.simple.JSONObject;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;

public class LoginControllerTest extends ApplicationTest {
    private LoginController controller;

    @Before
    public void before(){
        controller = new LoginController();
        controller.emailInput = new TextField();
        controller.passInput = new PasswordField();
        controller.errorLabel = new Label();
        controller.driverButton = new RadioButton();
        controller.managerButton = new RadioButton();
    }

    @Test
    public void testSameAccount(){
        JSONObject jo1 = new JSONObject();
        JSONObject jo2 = new JSONObject();

        jo1.put("Email", "test@gmail.com");
        jo1.put("Password", "test");
        jo2.put("Email", "test@gmail.com");
        jo2.put("Password", "test");

        Assert.assertEquals(true, controller.sameAccount(jo1, jo2));
    }

    @Test
    public void testLoginActionHandleFail(){
        controller.driverButton.setSelected(true);
        controller.emailInput.setText("test@gmail.com");
        controller.passInput.setText("test");

        controller.loginActionHandle();
        Assert.assertEquals("Username or password are incorrect!", controller.errorLabel.getText());
    }

    @Test
    public void testLoginActionHandleSuccess(){
        controller.driverButton.setSelected(true);
        controller.emailInput.setText("sebiu@gmail.com");
        controller.passInput.setText("0000");

        Assert.assertEquals("", controller.errorLabel.getText());
    }
}
