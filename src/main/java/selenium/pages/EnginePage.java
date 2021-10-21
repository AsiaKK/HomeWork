package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnginePage extends Page {

    private static final String ENGINE_PAGE_LOCATOR = "//font[text()='Is your car with you?']//ancestor::form";

    @Override
    public By getPageLocator() {
        return By.xpath(ENGINE_PAGE_LOCATOR);
    }

    private EnginePage(WebDriver driver) {
        super(driver);
    }

    public static EnginePage initialize(WebDriver driver) {
        EnginePage page = new EnginePage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public RegistrationDatePage clickOnEngine(String engine) {
        WebElement chosenEngine = waitForElement("//font[text()='" + engine + "']//ancestor::button");
        chosenEngine.click();
        return RegistrationDatePage.initialize(driver);
    }
}
