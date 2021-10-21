package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.pages.common.ForWholeApp;

public class RegisteredOwnerPage extends Page {

    ForWholeApp forWholeApp = new ForWholeApp(driver);

    private static final String _PAGE_LOCATOR = "//font[text()='Are you the owner of the car (registered to you)?']//ancestor::form";
    private static final String CAR_WAS_USED = "//label[contains(@data-testid,'selectRegisteredOwner.used')]";
    private static final String CAR_WAS_NEW = "//label[contains(@data-testid,'selectRegisteredOwner.brandNew')]";
    private static final String OWNER_YES = "//label[@data-testid='shared.yes']";
    private static final String OWNER_NO = "//label[@data-testid='shared.no']";
    private static final String ARE_YOU_OWNER_TEXT = "//font[text()='Are you the owner of the car (registered to you)?']";
    private static final String COMMERCIAL_PURPOSE_TEXT = "//font[text()='Do you use your car for commercial purposes? ']";
    private static final String NOT_FOR_YOU_TEXT = "//font[text()='Sorry, then FRIDAY is unfortunately not for you. ']";
    private static final String PRIVATE_INDIVIDUALS_TEXT = "//font[text()='We only insure private individuals.']";
    private static final String CAR_WAS_TEXT = "//font[text()='The car was ...']";
    private static final String USED_CAR_TEXT = "//font[text()='used when buying']";
    private static final String WHEN_BUYING_NEW_TEXT = "//font[text()='when buying new']";

    @Override
    public By getPageLocator() {
        return By.xpath(_PAGE_LOCATOR);
    }

    private RegisteredOwnerPage(WebDriver driver) {
        super(driver);
    }

    public static RegisteredOwnerPage initialize(WebDriver driver) {
        RegisteredOwnerPage page = new RegisteredOwnerPage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public RegisteredOwnerPage checkTextsOnCarOwnerPage() {
        waitForElement(ARE_YOU_OWNER_TEXT);
        waitForElement(COMMERCIAL_PURPOSE_TEXT);
        waitForElement(NOT_FOR_YOU_TEXT);
        waitForElement(PRIVATE_INDIVIDUALS_TEXT);
        waitForElement(CAR_WAS_TEXT);
        waitForElement(USED_CAR_TEXT);
        waitForElement(WHEN_BUYING_NEW_TEXT);
        return this;
    }

    public boolean checkIsYesForOwnerTheCarSelected() {
        WebElement owner = driver.findElement(By.xpath(OWNER_YES + "/p"));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return isValueOfAttributes(owner, "aria-checked");
    }

    public boolean checkIsNoForOwnerTheCarSelected() {
        WebElement owner = driver.findElement(By.xpath(OWNER_NO + "/p"));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return isValueOfAttributes(owner, "aria-checked");
    }

    public boolean checkIsUsedWhenBuyingSelected() {
        WebElement carWas = driver.findElement(By.xpath(CAR_WAS_USED + "/p"));
        wait.until(ExpectedConditions.visibilityOf(carWas));
        return isValueOfAttributes(carWas, "aria-checked");
    }

    public boolean checkIsNewWhenBuyingSelected() {
        WebElement carWas = driver.findElement(By.xpath(CAR_WAS_NEW + "/p"));
        wait.until(ExpectedConditions.visibilityOf(carWas));
        return isValueOfAttributes(carWas, "aria-checked");
    }

    public VehiclePage goToVehiclePage() {
        forWholeApp.clickOnFurtherButton();
        return VehiclePage.initialize(driver);
    }
}
