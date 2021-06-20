package WebConnector;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.IOException;
import java.util.Properties;

public class  Page <V> {
        public static WebDriver driver=null;
        public SessionId session=null;
        public static Properties prop = new Properties();
        public String heightValue;
        public String widthValue;

        public Page(){
            try {
                prop.load( new FileInputStream("./src/test/config/application.properties") );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public WebDriver getDriver() {
            return this.getDriver();
        }

        public void setDriver(WebDriver driver){
            this.driver=driver;
        }

        public void setUpDriver(){
            String browser = prop.getProperty("browser");
            String windowSize = prop.getProperty("window-size");
            if (browser == null) {
                browser = "chrome";
            }
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver","./src/test/lib/chromedriver.exe");
                    //Resize the browser to the given window-size from application.properties file
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(windowSize);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver","./src/test/lib/geckodriver.exe");
                    String[] textElements = windowSize.split(",");
                    for(int i=0; i<textElements.length; i++) {
                        widthValue =textElements[0];
                        heightValue = textElements[1];
                    }
                    int width =  Integer.parseInt(widthValue);
                    int height = Integer.parseInt(heightValue);
                    Dimension d = new Dimension(width,height);
                    driver = new FirefoxDriver();
                    //Resize the current window to the given dimension
                    driver.manage().window().setSize(d);
                    FirefoxProfile profile = new ProfilesIni().getProfile("default");
                    profile.setPreference("network.cookie.cookieBehavior", 2);
                    driver = new FirefoxDriver((Capabilities) profile);
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", "./src/test/lib/msedgedriver.exe");
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    break;
                case "safari":
                    String[] textElementSafari = windowSize.split(",");
                    for(int i=0; i<textElementSafari.length; i++) {
                        widthValue =textElementSafari[0];
                        heightValue = textElementSafari[1];
                    }
                    int widthSafari =  Integer.parseInt(widthValue);
                    int heightSafari = Integer.parseInt(heightValue);
                    Dimension dSafari = new Dimension(widthSafari,heightSafari);
                    driver = new SafariDriver();
                    //Resize the current window to the given dimension
                    driver.manage().window().setSize(dSafari);
                    break;
                default:
                    throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
            }
        }

        public void closeDriver(Scenario scenario){
            if(scenario.isFailed()){
                // Code to capture and embed images in test reports (if scenario fails)
                saveScreenshotsForScenario(scenario);
            }
            driver.quit();
        }

        private void saveScreenshotsForScenario(final Scenario scenario) {
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }

        public void waitForPageLoad(int timeout){
            ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        }
}
