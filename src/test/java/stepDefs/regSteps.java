package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class regSteps {

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @Given("user is on registration page")
    public void user_is_on_registration_page() throws InterruptedException {
    }

    @When("user enters valid details")
    public void user_enters_valid_details() throws InterruptedException {
    }

    @And("clicks register")
    public void clicks_register() throws InterruptedException {
        joinButton.click();
        Thread.sleep(3000);
    }

    @Then("account should be created successfully")
    public void account_should_be_created_successfully() {
        System.out.println("Registration test completed");
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }
}