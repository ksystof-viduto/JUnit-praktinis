package lt.techin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By shopCart = By.className("shopping_cart_link");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        driver.findElement(this.username).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(this.password).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isShopCartDisplayed() {
        return driver.findElement(shopCart).isDisplayed();
    }
    public boolean isErrorMessageDisplayed(String errorMessage) {
        return driver.getPageSource().contains(errorMessage);
    }
}
