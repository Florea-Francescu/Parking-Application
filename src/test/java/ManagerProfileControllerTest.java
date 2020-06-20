import javafx.scene.control.Label;
import main.java.Main;
import main.java.controllers.ManagerProfileController;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class ManagerProfileControllerTest extends ApplicationTest {
    ManagerProfileController controller;
    JSONObject testUser;

    @Before
    public void before(){
        controller = new ManagerProfileController();
        testUser = new JSONObject();

        controller.fstNameLabel = new Label();
        controller.lstNameLabel = new Label();
        controller.emailLabel = new Label();

        testUser.put("FirstName", "Prenume");
        testUser.put("LastName", "Nume");
        testUser.put("Email", "Adresa");

        Main.currentUser = testUser;
    }

    @Test
    public void testManagerProfileController(){
        controller.initializeLabels();

        Assert.assertEquals("Prenume", controller.fstNameLabel.getText());
        Assert.assertEquals("Nume", controller.lstNameLabel.getText());
        Assert.assertEquals("Adresa", controller.emailLabel.getText());
    }
}
