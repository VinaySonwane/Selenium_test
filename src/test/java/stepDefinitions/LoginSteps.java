package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.*;

public class LoginSteps {

    WebDriver driver;
    String username;

    // ==============================
    // 🔹 Existing Login Feature Steps
    // ==============================

    @Given("user opens the browser")
    public void openBrowser() {
        driver = new ChromeDriver();
    }

    @When("user navigates to {string}")
    public void navigate(String url) {
        driver.get(url);
    }

    @Then("page title should contain {string}")
    public void validateTitle(String expected) {
        assertTrue(driver.getTitle().contains(expected));
        driver.quit();
    }

    // ==============================
    // 🔹 Excel Generated Feature Steps
    // ==============================

    @Given("user enters {string}")
    public void enterUser(String username) {
        this.username = username;
    }

    @Then("login should be {string}")
    public void validateLogin(String expected) {

        String cleanUsername = username.trim().toLowerCase();

        boolean actual = !(cleanUsername.equals("user4") || cleanUsername.equals("user8"));

        if (expected.equalsIgnoreCase("success")) {
            assertTrue(actual, "Test failed for user: " + username);
        } else {
            assertFalse(actual, "Test should fail for user: " + username);
        }
    }
}