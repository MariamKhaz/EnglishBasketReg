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
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
    }

    @Given("user is on registration page")
    public void user_is_on_registration_page() throws InterruptedException {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        Thread.sleep(5000);
    }

    @When("user enters valid details")
    public void user_enters_valid_details() throws InterruptedException {
        driver.findElement(By.id("member_firstname")).sendKeys("Test");
        driver.findElement(By.id("member_lastname")).sendKeys("User");
        driver.findElement(By.id("member_emailaddress")).sendKeys("testuser12345@gmail.com");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("testuser12345@gmail.com");
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("Password123!");
        Thread.sleep(2000);
    }

    @And("clicks register")
    public void clicks_register() throws InterruptedException {
        WebElement joinButton = driver.findElement(By.name("join"));
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