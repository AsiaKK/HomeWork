package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnginePowerPage extends Page {

    private static final String ENGINE_POWER_PAGE_LOCATOR = "//font[text()='How much horsepower does your car have?']//ancestor::form";
    private static final String ENGINE_POWER_FILTER = "//input[@name='enginePowerFilter']";

    @Override
    public By getPageLocator() {
        return By.xpath(ENGINE_POWER_PAGE_LOCATOR);
    }

    private EnginePowerPage(WebDriver driver) {
        super(driver);
    }

    public static EnginePowerPage initialize(WebDriver driver) {
        EnginePowerPage page = new EnginePowerPage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public EnginePage enterEnginePower(String enginePower) {
        WebElement power = waitForElement(ENGINE_POWER_FILTER);
        fillText(power, enginePower);
        return EnginePage.initialize(driver);
    }
}
