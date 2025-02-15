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

    @FindBy(xpath = "//button[contains(@class, 'uitk-menu-trigger')] | //button[contains(@data-stid, 'header-menu')] | //button[contains(@aria-label, 'Hesap')]")
    private WebElement accountMenuButton;

    @FindBy(xpath = "//a[contains(@data-stid, 'link-header-account-signin')] | //a[contains(text(), 'Giriş')] | //a[contains(@href, 'signin')] | //button[contains(text(), 'Giriş yap')]")
    private WebElement signInButton;

    @FindBy(xpath = "//a[contains(@data-stid, 'link-header-account-signup')] | //a[contains(text(), 'Kaydol')] | //a[contains(@href, 'signup')] | //a[contains(text(), 'Hesap oluştur')] | //button[contains(text(), 'Hesap oluştur')] | //a[contains(@data-stid, 'button-sign-up')] | //button[contains(text(), 'Kaydol')] | //a[contains(@class, 'signup-link')] | //a[contains(@data-stid, 'header-account-signup')]") 
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
        try {
            // First click the account menu button
            WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(accountMenuButton));
            System.out.println("Found account menu button with text: " + menuButton.getText());
            menuButton.click();
            
            try {
                // Wait a bit for the menu to appear
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted: " + e.getMessage());
            }
            
            // Then click the sign in link
            WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
            System.out.println("Found sign in link with text: " + signIn.getText());
            signIn.click();
        } catch (Exception e) {
            System.out.println("Error in sign in process: " + e.getMessage());
            throw e;
        }
    }

    public void clickCreateAccountLink() {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(createAccountLink));
            System.out.println("Found create account link with text: " + link.getText());
            System.out.println("Link attributes - class: " + link.getAttribute("class") + ", href: " + link.getAttribute("href"));
            link.click();
        } catch (Exception e) {
            System.out.println("Error clicking create account link: " + e.getMessage());
            // Print page source for debugging
            System.out.println("Current page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
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
