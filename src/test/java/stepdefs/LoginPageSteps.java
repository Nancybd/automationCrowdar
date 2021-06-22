package stepdefs;
import ApplicationPages.LoginPage;
import WebConnector.Page;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class LoginPageSteps extends Page {
    private String scenDesc;
    public LoginPage loginPage;
    @Before
    public void before(Scenario scenario) {
        this.scenDesc = scenario.getName();
        setUpDriver();
    }

    @After
    public void after(Scenario scenario){
        closeDriver(scenario);
    }

    @BeforeStep
    public void beforeStep() throws InterruptedException {
        Thread.sleep(2000);
    }

    public LoginPageSteps() {
        this.loginPage = new LoginPage();
    }

    @Given("I am in Login page")
    public void iAmInLoginPage() throws IOException, InvalidFormatException {
        this.loginPage.open();
    }

    @When("I enter {string} in Username field")
    public void iEnterInUsernameField(String user) {
        this.loginPage.enterUserName(user);
    }

    @And("I enter {string} in Password field")
    public void iEnterInPasswordField(String pass) {
        this.loginPage.enterPass(pass);
    }

    @And("I click on Login button")
    public void iClickOnLoginButton() {
        this.loginPage.clickLoginButton();
    }

    @Then("I should be able to see {string} title")
    public void iShouldBeAbleToSeeTitle(String title) {
        this.loginPage.verifyTitle(title);
    }

    @When("I click on burger menu")
    public void iClickOnBurgerMenu() {
        this.loginPage.clickOnBurgerMenu();
    }

    @And("I click on Logout link")
    public void iClickOnLogoutLink() {
        this.loginPage.clickOnLogoutLnk();
    }

    @Then("I should be able to see Login page")
    public void iShouldBeAbleToSeeLoginPage() {
        this.loginPage.verifyURL();
    }

    @Then("I should be able to see warning message {string}")
    public void iShouldBeAbleToSeeWarningMessage(String msg) {
        this.loginPage.validateWarningMsg(msg);
    }
}
