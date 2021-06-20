package runner;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(tags =  {"@login"},glue = {"stepdefs"},
        plugin = {"html:target/cucumber-reports/LoginPage/cucumber-pretty",
                "json:target/json-cucumber-reports/LoginPage/cukejson.json",
                "testng:target/testng-cucumber-reports/LoginPage/cuketestng.xml" },
        features = {"src/test/resources/features/"})

public class LoginPageRunner {
    private static long duration;

    @BeforeClass
    public static void before() {
        duration = System.currentTimeMillis();
        System.out.println("Thread Id  | Scenario Num       | Step Count");
    }

    @AfterClass
    public static void after() {
        duration = System.currentTimeMillis() - duration;
        System.out.println("DURATION - "+ duration);
    }
}
