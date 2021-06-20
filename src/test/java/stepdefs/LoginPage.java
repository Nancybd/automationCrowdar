package stepdefs;

import WebConnector.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static WebConnector.Page.driver;
public class LoginPage {
    Page wc= new Page();
    String DEMO_URL      = "http://saucedemo.com";
    String productsTitle = "//*[@class='title']";
    String error         = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]";

    public void open() {
        String URL = wc.prop.getProperty("url");
        driver = new ChromeDriver();
        driver.get(DEMO_URL);
    }

    public void enterUserName(String userText){
        WebElement userInput = driver.findElement(By.id("user-name"));
        userInput.sendKeys(userText);
    }
    public void enterPass(String pass){
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(pass);
    }

    public void clickLoginButton(){
        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();
    }

    public void verifyURL(){
        String currentURL= driver.getCurrentUrl();
        Assert.assertEquals(currentURL,DEMO_URL);
    }

    public void verifyTitle(String title){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement productsTitleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productsTitle)));
        productsTitleElement.isDisplayed();
    }

    public void clickOnBurgerMenu(){
        WebElement burgerMenu = driver.findElement(By.id("menu_button_container"));
        burgerMenu.click();
    }

    public void clickOnLogoutLnk() {
        WebElement logoutLnk = driver.findElement(By.id("logout_sidebar_link"));
        logoutLnk.click();
    }

    public void validateWarningMsg(String msg) {
    WebElement errorMessage = driver.findElement(By.xpath("error"));
    String innerMsg  = errorMessage.getText();
    Assert.assertTrue(innerMsg.contains(msg));
    }
}
