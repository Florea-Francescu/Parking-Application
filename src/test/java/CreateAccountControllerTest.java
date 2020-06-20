import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.Main;
import main.java.controllers.CreateAccountController;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;


public class CreateAccountControllerTest extends ApplicationTest {

    private CreateAccountController c1;

    @Before
    public void Before()
    {
        System.out.println("Before");
        c1 = new CreateAccountController();
        c1.errorLabel = new Label();
        c1.emailInput = new TextField();
        c1.fstNameInput = new TextField();
        c1.lstNameInput = new TextField();
        c1.regNumberInput = new TextField();
        c1.passInput = new PasswordField();
        c1.confPassInput = new PasswordField();
    }

    @After
    public void After()
    {
        System.out.println("After");
    }

    @Test
    public void testEmail(){
        c1.emailInput.setText("test@gmail.co");
        c1.handleCreateAccountButton();
        assertEquals("Please type a valid email", c1.errorLabel.getText());
        System.out.println("ok");
    }

    @Test
    public void testName(){
        c1.emailInput.setText("test@gmail.com");
        c1.handleCreateAccountButton();
        assertEquals("Complete your name", c1.errorLabel.getText());
        System.out.println("ok");
    }

    @Test
    public void testPassword(){
        c1.emailInput.setText("test@gmail.com");
        c1.fstNameInput.setText("test");
        c1.lstNameInput.setText("test");
        c1.regNumberInput.setText("t");
        c1.handleCreateAccountButton();
        assertEquals("Please type your password", c1.errorLabel.getText());
        System.out.println("ok");
    }

    @Test
    public void testRegNumber(){
        c1.emailInput.setText("test@gmail.com");
        c1.fstNameInput.setText("test");
        c1.lstNameInput.setText("test");
        c1.passInput.setText("oo");
        c1.confPassInput.setText("oo");
        c1.handleCreateAccountButton();
        assertEquals("Please type car registration number", c1.errorLabel.getText());
        System.out.println("ok");
    }

}
