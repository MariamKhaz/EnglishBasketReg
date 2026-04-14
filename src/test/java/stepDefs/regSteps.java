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

import static org.junit.Assert.assertEquals;

public class regSteps {

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @Given("user is on registration page")
    public void user_is_on_registration_page() throws InterruptedException {
        driver.get("file:///C:/Users/maria/Documents/MVT-25/4.%20Testauto.%20och%20progr/Register%20Basketball/Register.html");
        Thread.sleep(2000);
    }

    @When("user enters valid details")
    public void user_enters_valid_details() throws InterruptedException {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("User");
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys("testuser12345@gmail.com");
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys("testuser12345@gmail.com");
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[name='DateOfBirth']")).sendKeys("01/01/2000");

        Thread.sleep(1000);

        driver.findElement(By.cssSelector("label[for='sign_up_25'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box")).click();

        Thread.sleep(1000);
    }

    @When("user enters details without last name")
    public void user_enters_details_without_last_name() throws InterruptedException {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys("test1@gmail.com");
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys("test1@gmail.com");
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[name='DateOfBirth']")).sendKeys("01/01/2000");

        driver.findElement(By.cssSelector("label[for='sign_up_25'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box")).click();

        Thread.sleep(1000);
    }

    @When("user enters mismatching passwords")
    public void user_enters_mismatching_passwords() throws InterruptedException {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("User");
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys("test2@gmail.com");
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys("test2@gmail.com");
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys("WrongPass123!");
        driver.findElement(By.cssSelector("input[name='DateOfBirth']")).sendKeys("01/01/2000");

        driver.findElement(By.cssSelector("label[for='sign_up_25'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box")).click();

        Thread.sleep(1000);
    }

    @When("user enters valid details without accepting terms")
    public void user_enters_valid_details_without_accepting_terms() throws InterruptedException {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("User");
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys("test3@gmail.com");
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys("test3@gmail.com");
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[name='DateOfBirth']")).sendKeys("01/01/2000");

        driver.findElement(By.cssSelector("label[for='sign_up_26'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box")).click();

        Thread.sleep(1000);
    }

    @And("clicks register")
    public void clicks_register() throws InterruptedException {
        WebElement joinButton = driver.findElement(By.cssSelector("input[name='join']"));

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", joinButton);

        Thread.sleep(1000);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", joinButton);

        Thread.sleep(3000);
    }

    @Then("account should be created successfully")
    public void account_should_be_created_successfully() {
        System.out.println("Registration test completed");
    }

    @Then("error message {string} should be shown")
    public void error_message_should_be_shown(String expected) {
        String actual = "";

        if (expected.equals("Last Name is required")) {
            actual = driver.findElement(By.cssSelector("span[data-valmsg-for='Surname']")).getText();
        } else if (expected.equals("Password did not match")) {
            actual = driver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword']")).getText();
        } else if (expected.equals("You must confirm that you have read and accepted our Terms and Conditions")) {
            actual = driver.findElement(By.cssSelector("span[for='TermsAccept']")).getText();
        }

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }
}