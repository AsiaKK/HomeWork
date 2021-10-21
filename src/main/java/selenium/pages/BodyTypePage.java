package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BodyTypePage extends Page {

    private static final String BODY_TYPE_PAGE_LOCATOR = "//font[text()='What shape is the car?']//ancestor::form";
    private static final String SHAPE_CAR_TEXT = "//font[text()='What shape is the car?']";

    @Override
    public By getPageLocator() {
        return By.xpath(BODY_TYPE_PAGE_LOCATOR);
    }

    private BodyTypePage(WebDriver driver) {
        super(driver);
    }

    public static BodyTypePage initialize(WebDriver driver) {
        BodyTypePage page = new BodyTypePage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public BodyTypePage checkTextOnShapeCarPage() {
        waitForElement(SHAPE_CAR_TEXT);
        return this;
    }

    public FuelTypePage chooseBodyType(String bodyType) {
        WebElement type = waitForElement("//font[text()='" + bodyType + "']//ancestor::button");
        type.click();
        return FuelTypePage.initialize(driver);
    }
}
