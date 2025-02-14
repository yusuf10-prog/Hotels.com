package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),'Uygulamayı edinin')]")
    private WebElement getAppButton;

    @FindBy(xpath = "//button[contains(text(),'Giriş')] | //button[contains(@class, 'sign-in')] | //a[contains(text(),'Giriş')]")
    private WebElement signInButton;

    @FindBy(xpath = "//a[contains(text(),'Ücretsiz hesap oluştur')]")
    private WebElement createAccountLink;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//button[contains(text(),'Hesap oluştur')]")
    private WebElement createAccountButton;

    @FindBy(xpath = "//button[contains(@id, 'accept-btn')] | //button[contains(text(),'Kabul')] | //button[contains(@class, 'accept-cookies')]")
    private WebElement acceptCookiesButton;

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void clickCreateAccountLink() {
        createAccountLink.click();
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already accepted");
        }
    }
}
