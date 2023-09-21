package lt.testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import lt.techin.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class loginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void loginStandardUserCorrectTest(){
//        driver.get("https://www.saucedemo.com/");
//
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();
//
//        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
//        assertTrue(driver.findElement(By.className("shopping_cart_link")).isDisplayed());

        loginPage.openLoginPage("https://www.saucedemo.com/");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        assertEquals("https://www.saucedemo.com/inventory.html", loginPage.getCurrentUrl());
        assertTrue(loginPage.isShopCartDisplayed());
    }

    @Test
    void loginStandardUserIncorrectTest(){
        loginPage.openLoginPage("https://www.saucedemo.com/");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLoginButton();

        loginPage.isErrorMessageDisplayed("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    void loginLockedOutUserTest(){
        loginPage.openLoginPage("https://www.saucedemo.com/");
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        loginPage.isErrorMessageDisplayed("this user has been locked out");
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/logins.csv", numLinesToSkip = 1)
    void loginParamTest(String user, String password){
//        driver.get("https://www.saucedemo.com");
//
//        driver.findElement(By.id("user-name")).sendKeys(user);
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("login-button")).click();
//
//        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
//        assertTrue(driver.findElement(By.className("shopping_cart_link")).isDisplayed());

        loginPage.openLoginPage("https://www.saucedemo.com/");
        loginPage.enterUsername(user);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertEquals("https://www.saucedemo.com/inventory.html", loginPage.getCurrentUrl());
        assertTrue(loginPage.isShopCartDisplayed());
    }


    @Test
    void loginTimeoutTest(){
        loginPage.openLoginPage("https://www.saucedemo.com/");

        assertTimeout(Duration.ofSeconds(2), () -> {

            loginPage.enterUsername("performance_glitch_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLoginButton();

            assertEquals("https://www.saucedemo.com/inventory.html", loginPage.getCurrentUrl());
            assertTrue(loginPage.isShopCartDisplayed());
        } );

    }



}
