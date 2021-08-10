package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public abstract class AbstractTest {
    //protected static WebDriver driver;
    private static ThreadLocal <WebDriver> threadDriver= new ThreadLocal<WebDriver>();

    public enum Browser {
        CHROME, FIREFOX, CHROMEHEADLESS, FIREFOXHEADLESS;
    }

    private String projectPath = System.getProperty("user.dir");

    public WebDriver getBrowserDriver(String browserName) {

        Browser browser = Browser.valueOf(browserName.toUpperCase());
        switch (browserName) {
            // case "chrome":
            case CHROME:
                WebDriverManager.chromedriver().setup();
//                System.setProperty("webdriver.chrome.driver", projectPath +
//                        "\\browserDriver\\chromedriver.exe");
                threadDriver.set(new ChromeDriver());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                threadDriver.set(new FirefoxDriver());
                break;

            case "chromeheadless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOpt = new ChromeOptions();
                chromeOpt.setHeadless(true);
//            options.addArguments("headless");
                chromeOpt.addArguments("window-size=1920x1080");
                threadDriver.set(new ChromeDriver(chromeOpt));
                break;
            case "firefoxheadless":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOpt = new FirefoxOptions();
                firefoxOpt.setHeadless(true);
//                firefoxOpt.addArguments("-headless");
                firefoxOpt.addArguments("window-size=1920x1080");
                threadDriver.set(new FirefoxDriver(firefoxOpt));
                break;
            default:
                throw new RuntimeException("Please choose browser name");
        }


        threadDriver.get().get("http://www.demo.guru99.com/v4/");
       // threadDriver.get().get(appUrl);
        threadDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        threadDriver.get().manage().window().maximize();
        return threadDriver.get();
    }
    protected void removeBrowserDriver(){
        threadDriver.get().quit();
        threadDriver.remove();
    }
}
