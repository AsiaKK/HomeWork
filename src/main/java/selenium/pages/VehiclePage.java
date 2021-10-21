package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehiclePage extends Page {

    private static final String VEHICLE_PAGE_LOCATOR = "//font[text()='Choose your car']//ancestor::form";
    private static final String HSN_TSN_BUTTON = "//button[@name='hsnTsn']";
    private static final String HSN_TSN_BUTTON_SELECTED = "//button[@name='hsnTsn' and @class[contains(.,'selected')]]";
    private static final String BRAND_AND_MODEL_BUTTON = "//button[@name='list']";
    private static final String BRAND_AND_MODEL_BUTTON_SELECTED = "//button[@name='list' and @class[contains(.,'selected')]]";
    private static final String CAR_BRAND_INPUT = "//input[@placeholder='Enter car brand']";
    private static final String CHOOSE_YOUR_CAR_TEXT = "//font[text()='Choose your car']";
    private static final String FIRST_REGISTERED_TEXT = "//font[text()='First registration']";
    private static final String HSN_TEXT = "//font[text()='HSN']";
    private static final String TSN_TEXT = "//font[text()='TSN']";
    private static final String FIND_VIA_BRAND_TEXT = "//font[text()='Find via brand & model']";
    private static final String FIND_VIA_HSN_TEXT = "//font[text()='Find via HSN / TSN']";
    private static final String HELP_TEXT = "//font[text()='You can find this information on the middle page of the vehicle registration document.']";
    private static final String POPULAR_MANUFACTURERS_TEXT = "//font[text()='Popular manufacturers']";
    private static final String ALL_MANUFACTURERS_TEXT = "//font[text()='All manufacturers']']";

    @Override
    public By getPageLocator() {
        return By.xpath(VEHICLE_PAGE_LOCATOR);
    }

    private VehiclePage(WebDriver driver) {
        super(driver);
    }

    public static VehiclePage initialize(WebDriver driver) {
        VehiclePage page = new VehiclePage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public VehiclePage checkHsnTsnButtonSelected() {
        waitForElement(HSN_TSN_BUTTON_SELECTED);
        return this;
    }

    public VehiclePage checkBrandAndModelButtonSelected() {
        waitForElement(BRAND_AND_MODEL_BUTTON_SELECTED);
        return this;
    }

    public VehiclePage clickOnHsnTsnButton() {
        WebElement hsnTsnButton = waitForElement(HSN_TSN_BUTTON);
        hsnTsnButton.click();
        return this;
    }

    public VehiclePage clickOnBrandAndModelButton() {
        WebElement brandAndModelButton = waitForElement(BRAND_AND_MODEL_BUTTON);
        brandAndModelButton.click();
        return this;
    }

    public VehiclePage checkTextsForHsnTsnSite() {
        waitForElement(FIRST_REGISTERED_TEXT);
        waitForElement(HSN_TEXT);
        waitForElement(TSN_TEXT);
        waitForElement(CHOOSE_YOUR_CAR_TEXT);
        waitForElement(FIND_VIA_BRAND_TEXT);
        waitForElement(FIND_VIA_HSN_TEXT);
        waitForElement(HELP_TEXT);
        return this;
    }

    public VehiclePage checkTextsForBrandAndModelSite() {
        waitForElement(POPULAR_MANUFACTURERS_TEXT);
        waitForElement(ALL_MANUFACTURERS_TEXT);
        return this;
    }

    public VehiclePage enterCarBrand(String brand) {
        WebElement brandField = waitForElement(CAR_BRAND_INPUT);
        fillText(brandField, brand);
        return this;
    }

    public ModelPage clickOnBrand(String brand) {
        WebElement chosenBrand = waitForElement("//font[text()='" + brand + "']//ancestor::button");
        chosenBrand.click();
        return ModelPage.initialize(driver);
    }
}
