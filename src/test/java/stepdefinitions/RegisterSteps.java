package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import pages.RegisterPage;

public class RegisterSteps {
    private WebDriver driver;
    private RegisterPage registerPage;
    private Faker faker;

    @Given("user is on Hotels.com homepage")
    public void userIsOnHotelsHomepage() {
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            
            driver.get("https://tr.hotels.com/");
            registerPage = new RegisterPage(driver);
            
            // Wait for page to be fully loaded
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .equals("complete"));
            
            Thread.sleep(3000); // Additional wait for dynamic content
        } catch (Exception e) {
            System.out.println("Error during browser initialization: " + e.getMessage());
            if (driver != null) {
                driver.quit();
            }
            throw new RuntimeException(e);
        }
    }

    @When("user waits for the page to be fully loaded")
    public void userWaitsForPageToLoad() {
        try {
            Thread.sleep(2000);
            // Print page source for debugging
            System.out.println("Page source after loading:");
            System.out.println(driver.getPageSource());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("user clicks on sign in button")
    public void userClicksOnSignInButton() {
        try {
            registerPage.clickSignInButton();
            try {
                // Take screenshot after clicking sign in
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("target/screenshot-after-signin.png"));
                
                // Print page source for debugging
                System.out.println("Page source after clicking sign in:");
                System.out.println(driver.getPageSource());
            } catch (IOException e) {
                System.out.println("Error saving screenshot: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error clicking sign in button: " + e.getMessage());
            throw e;
        }
    }

    @And("user waits for the sign in page to load")
    public void userWaitsForSignInPageToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("user waits for the registration form to load")
    public void userWaitsForRegistrationFormToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("user clicks on create account link")
    public void userClicksOnCreateAccountLink() {
        registerPage.clickCreateAccountLink();
    }

    @And("user enters registration details with faker data")
    public void userEntersRegistrationDetailsWithFakerData() {
        faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 20, true, true, true);
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
    }

    @And("user accepts cookies if present")
    public void userAcceptsCookiesIfPresent() {
        registerPage.acceptCookies();
    }

    @And("user clicks on create account button")
    public void userClicksOnCreateAccountButton() {
        registerPage.clickCreateAccountButton();
    }

    @Then("user should see successful registration message")
    public void userShouldSeeSuccessfulRegistrationMessage() {
        // Implementation will depend on the actual success message shown
        try {
            Thread.sleep(2000); // Wait for success message
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error during driver cleanup: " + e.getMessage());
            }
        }
    }
}
