package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ModelPage extends Page {

    private static final String MODEL_PAGE_LOCATOR = "//font[text()='Choose your car model']//ancestor::form";
    private static final String MODEL_FIELD = "//input[@name='modelFilter']";
    private static final String MOST_POPULAR_TEXT = "//font[text()='Most popular models']";
    private static final String ALL_TYPES_TEXT = "//font[text()='All types']";
    private static final String MODEL_BUTTON = "//button[@name='model']";

    @Override
    public By getPageLocator() {
        return By.xpath(MODEL_PAGE_LOCATOR);
    }

    private ModelPage(WebDriver driver) {
        super(driver);
    }

    public static ModelPage initialize(WebDriver driver) {
        ModelPage page = new ModelPage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public ModelPage checkTextsForCarModelPage() {
        waitForElement(MOST_POPULAR_TEXT);
        waitForElement(ALL_TYPES_TEXT);
        return this;
    }

    public ModelPage enterModel(String model) {
        WebElement modelField = waitForElement(MODEL_FIELD);
        fillText(modelField, model);
        return this;
    }

    public BodyTypePage clickOnModel() {
        WebElement chosenModel = waitForElement(MODEL_BUTTON);
        chosenModel.click();
        return BodyTypePage.initialize(driver);
    }
}
