package browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeHeadlessDriverManger  extends DriverManager {
    @Override
    protected void createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.setHeadless(true);
//            options.addArguments("headless");
        chromeOpt.addArguments("window-size=1920x1080");
        driver = new ChromeDriver(chromeOpt);
    }
}
