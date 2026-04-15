package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class regSteps {

    WebDriver driver;

    @Given("user is on registration page in {string}")
    public void user_is_on_registration_page_in(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get("file:///C:/Users/maria/Documents/MVT-25/4.%20Testauto.%20och%20progr/Register%20Basketball/Register.html");
        waitForVisible(By.cssSelector("input#member_firstname"));
    }

    @When("user enters valid details")
    public void user_enters_valid_details() {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("User");
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys("testuser12345@gmail.com");
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys("testuser12345@gmail.com");
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[name='DateOfBirth']")).sendKeys("01/01/2000");

        driver.findElement(By.cssSelector("label[for='sign_up_25'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box")).click();
    }

    @When("user enters details without last name")
    public void user_enters_details_without_last_name() {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys("test1@gmail.com");
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys("test1@gmail.com");
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[name='DateOfBirth']")).sendKeys("01/01/2000");

        driver.findElement(By.cssSelector("label[for='sign_up_25'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='sign_up_26'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box")).click();
    }

    @When("user enters mismatching passwords")
    public void user_enters_mismatching_passwords() {
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
    }

    @When("user enters valid details without accepting terms")
    public void user_enters_valid_details_without_accepting_terms() {
        driver.findElement(By.cssSelector("input#member_firstname")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#member_lastname")).sendKeys("User");
        driver.findElement(By.cssSelector("input#member_emailaddress")).sendKeys("test3@gmail.com");
        driver.findElement(By.cssSelector("input#member_confirmemailaddress")).sendKeys("test3@gmail.com");
        driver.findElement(By.cssSelector("input#signupunlicenced_password")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input#signupunlicenced_confirmpassword")).sendKeys("Password123!");
        driver.findElement(By.cssSelector("input[name='DateOfBirth']")).sendKeys("01/01/2000");

        driver.findElement(By.cssSelector("label[for='sign_up_26'] > span.box")).click();
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > span.box")).click();
    }

    @And("clicks register")
    public void clicks_register() {
        WebElement joinButton = waitForClickable(By.cssSelector("input[name='join']"));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", joinButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", joinButton);
    }

    @Then("account should be created successfully")
    public void account_should_be_created_successfully() {
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        String actual = waitForVisible(By.cssSelector("h2.text-center")).getText();
        assertEquals(expected, actual);
    }

    @Then("error message {string} should be shown")
    public void error_message_should_be_shown(String expected) {
        String actual;

        if (expected.equals("Last Name is required")) {
            actual = waitForVisible(By.cssSelector("span[data-valmsg-for='Surname']")).getText();
        } else if (expected.equals("Password did not match")) {
            actual = waitForVisible(By.cssSelector("span[for='signupunlicenced_confirmpassword']")).getText();
        } else if (expected.equals("You must confirm that you have read and accepted our Terms and Conditions")) {
            actual = waitForVisible(By.cssSelector("span[for='TermsAccept']")).getText();
        } else {
            throw new IllegalArgumentException("Unexpected error message: " + expected);
        }

        assertEquals(expected, actual);
    }

    private WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    private WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}