package selenium.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import selenium.configuration.WebDriverProvider;
import selenium.pages.PreconditionPage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class TestBase {

    public static WebDriver driver;
    public PreconditionPage preconditionPage;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        String testName = getTestName();
        log.info(testName + ": Starting setup");
        driver = WebDriverProvider.chromeDriver();
        log.info(testName + ": WebDriver properly created");
        driver.get("https://hello.friday.de/quote/selectPrecondition");
        preconditionPage = PreconditionPage.initialize(driver);
    }

    @AfterMethod
    public void cleanUp (ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("screenshots\\testFailed\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
        }
        driver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.close();
            log.info(getTestName() + ": WebDriver properly closed");
        }
    }

    private String getTestName() {
        return getClass().getCanonicalName();
    }
}
