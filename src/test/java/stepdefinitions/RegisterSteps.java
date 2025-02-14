package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import pages.RegisterPage;

public class RegisterSteps {
    private WebDriver driver;
    private RegisterPage registerPage;
    private Faker faker;

    @Given("user is on Hotels.com homepage")
    public void userIsOnHotelsHomepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://tr.hotels.com/");
        registerPage = new RegisterPage(driver);
        try {
            Thread.sleep(2000); // Wait for initial page load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("user clicks on sign in button")
    public void userClicksOnSignInButton() {
        registerPage.clickSignInButton();
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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
