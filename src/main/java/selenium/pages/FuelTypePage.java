package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FuelTypePage extends Page {

    private static final String FUEL_TYPE_PAGE_LOCATOR = "//font[text()='What are you filling up']//ancestor::form";

    @Override
    public By getPageLocator() {
        return By.xpath(FUEL_TYPE_PAGE_LOCATOR);
    }

    private FuelTypePage(WebDriver driver) {
        super(driver);
    }

    public static FuelTypePage initialize(WebDriver driver) {
        FuelTypePage page = new FuelTypePage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public EnginePowerPage chooseFuelType(String fuelType) {
        WebElement fuel = waitForElement("//font[text()='" + fuelType + "']//ancestor::button");
        fuel.click();
        return EnginePowerPage.initialize(driver);
    }
}
