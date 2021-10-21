package selenium.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverProvider {

    private static final String DRIVER_PATH_CHROME = "web_driver\\chromedriver.exe";

    public static WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions());
        return chromeDriver;
    }

    public static WebDriver edgeDriver(){
        WebDriverManager.edgedriver().setup();

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("CAPABILITY_AUTOCLOSE", false);

        EdgeDriver edgeDriver = new EdgeDriver(edgeOptions);
        edgeDriver.manage().window().maximize();
        edgeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return edgeDriver;
    }

    private static ChromeOptions chromeOptions() {
        File file = new File(DRIVER_PATH_CHROME);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-gpu");
        return chromeOptions;
    }
}
