package stepdefs;
import WebConnector.Page;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;


public class ServiceHooks extends Page {
    private String scenDesc;
    @Before
    public void before(Scenario scenario) {
        // this.scenDesc = scenario.getName();
        //setUpDriver();
    }
    @BeforeStep
    public void beforeStep() throws InterruptedException {
        Thread.sleep(2000);
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        //closeDriver(scenario);
    }

}
