import main.java.controllers.CheckProfileController;
import org.json.simple.JSONObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CheckProfileControllerTest {
    private JSONObject testUser;

    @Test
    public void testSetUser(){
        testUser = new JSONObject();
        testUser.put("FirstName", "Prenume");
        testUser.put("LastName", "Nume");
        testUser.put("Email", "Email");
        testUser.put("RegistrationNumber", "RE67NUM");

        CheckProfileController.setUser(testUser);

        assertEquals("Prenume", testUser.get("FirstName"));
        assertEquals("Nume", testUser.get("LastName"));
        assertEquals("Email", testUser.get("Email"));
        assertEquals("RE67NUM", testUser.get("RegistrationNumber"));
    }
}
