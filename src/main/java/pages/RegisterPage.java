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

    @FindBy(xpath = "//button[contains(@class, 'uitk-button-has-text')] | //button[contains(@class, 'uitk-button')] | //a[contains(@data-stid, 'link-header-account-signin')]")
    private WebElement signInButton;

    @FindBy(xpath = "//a[contains(@data-stid, 'link-header-account-signup')] | //button[contains(text(), 'Kaydol')] | //a[contains(text(), 'Kaydol')]") 
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

    @FindBy(xpath = "//button[@id='accept-button'] | //button[contains(@class, 'accept')] | //button[contains(@class, 'cookie-consent')] | //button[contains(text(), 'Kabul')] | //button[contains(@aria-label, 'Çerezleri kabul et')]") 
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
