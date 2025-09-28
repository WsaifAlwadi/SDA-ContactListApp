package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By registerBtn = By.id("signup");
    By firstNameInput = By.id("firstName");
    By lastNameInput = By.id("lastName");
    By emailInput = By.id("email");
    By passwordInput = By.id("password");
    By submitRegister = By.id("submit");

    By loginEmail = By.id("email");
    By loginPassword = By.id("password");
    By loginBtn = By.id("submit");

    public void registerUser(String fn, String ln, String email, String pwd) {
        driver.findElement(registerBtn).click();
        driver.findElement(firstNameInput).sendKeys(fn);
        driver.findElement(lastNameInput).sendKeys(ln);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(pwd);
        driver.findElement(submitRegister).click();
    }

    public void login(String email, String pwd) {
        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(pwd);
        driver.findElement(loginBtn).click();
    }
}