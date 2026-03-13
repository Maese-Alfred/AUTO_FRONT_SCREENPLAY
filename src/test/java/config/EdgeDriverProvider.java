package config;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverProvider implements DriverSource {

    @Override
    public WebDriver newDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        String driverPath = System.getProperty("webdriver.edge.driver");
        if (driverPath != null && !driverPath.isEmpty()) {
            System.setProperty("webdriver.edge.driver", driverPath);
        }

        return new EdgeDriver(options);
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
