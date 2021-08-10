package browserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxHeadlessDriverManger extends DriverManager {
    @Override
    protected void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOpt = new FirefoxOptions();
        firefoxOpt.setHeadless(true);
//                firefoxOpt.addArguments("-headless");
        firefoxOpt.addArguments("window-size=1920x1080");
        driver = new FirefoxDriver(firefoxOpt);

    }
}
