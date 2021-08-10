package browserFactory;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class DriverMangerFactory {
    public enum Browser {
        CHROME, FIREFOX, CHROMEHEADLESS, FIREFOXHEADLESS;
    }
    public static DriverManager getBrowserDriver(String browserName){
        Browser browser=Browser.valueOf(browserName.toUpperCase());
        DriverManager driverManager;

           switch (browser){
               case CHROME:
                   driverManager = new ChromeDriverManager();
                   break;
               case FIREFOX  :
                   driverManager= new FirefoxDriverManager();
                   break;
               case CHROMEHEADLESS  :
                   driverManager=new ChromeHeadlessDriverManger();
                   break;
               case FIREFOXHEADLESS  :
                   driverManager= new FirefoxHeadlessDriverManger();
                   break;

               default:
                   throw new RuntimeException("Please choose browser name");

           }

           return driverManager;
        }

    }

