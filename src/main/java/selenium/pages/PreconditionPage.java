package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.pages.common.ForWholeApp;

public class PreconditionPage extends Page {

    ForWholeApp forWholeApp = new ForWholeApp(driver);

    private static final String PRECONDITION_PAGE_LOCATOR = "//font[text()='Calculate your personal offer in 90 seconds.']//ancestor::form";
    private static final String KEEPING_CAR = "//label[contains(@data-testid,'keepingCarquoting')]";
    private static final String BUYING_CAR = "//label[contains(@data-testid,'buyingCarquoting')]";
    private static final String KEEPING_CAR_INPUT = "//input[@value='keepingCar']";
    private static final String BUYING_CAR_INPUT = "//input[@value='buyingCar']";
    private static final String DATE_INPUT = "//input[@placeholder='DD.MM.YYYY']";
    private static final String CAR_ALREADY_INSURED_TEXT = "//font[text()='The car is already insured']";
    private static final String CHANGE_INSURER_TEXT = "//font[text()='(Change of insurer)']";
    private static final String INSURER_START_TEXT = "//font[text()='When should your FRIDAY insurance start?']";
    private static final String FRIDAY_ECO_TEXT = "//font[text()='Compensate for the CO2 emissions of the kilometers you drive with FRIDAY ECO. ']";
    private static final String CLIMATE_PROTECTION_TEXT = "//font[text()='Bookable as a climate protection contribution to your car insurance.']";

    @Override
    public By getPageLocator() {
        return By.xpath(PRECONDITION_PAGE_LOCATOR);
    }

    private PreconditionPage(WebDriver driver) {
        super(driver);
    }

    public static PreconditionPage initialize(WebDriver driver) {
        PreconditionPage page = new PreconditionPage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public boolean checkIsCarAlreadyInsuredSelected() {
        WebElement carInsured = driver.findElement(By.xpath(KEEPING_CAR + "/p"));
        wait.until(ExpectedConditions.visibilityOf(carInsured));
        return isValueOfAttributes(carInsured, "aria-checked");
    }

    public boolean checkIsCarStillBeingRegisteredSelected() {
        WebElement carInsured = driver.findElement(By.xpath(BUYING_CAR + "/p"));
        wait.until(ExpectedConditions.visibilityOf(carInsured));
        return isValueOfAttributes(carInsured, "aria-checked");
    }


    public PreconditionPage fillInsuranceStartDate(String date) {
        WebElement startDate = waitForElement(DATE_INPUT);
        fillText(startDate, date);
        return this;
    }

    public String getStartDate() {
        WebElement startDate = waitForElement(DATE_INPUT);
        String date = startDate.getText();
        return date;
    }

    public PreconditionPage selectCarAlreadyInsured() {
        WebElement carInsured = waitForElement(KEEPING_CAR_INPUT);
        carInsured.click();
        return this;
    }

    public PreconditionPage selectCarStillBeingRegistered() {
        WebElement carStillRegistered = waitForElement(BUYING_CAR_INPUT);
        carStillRegistered.click();
        return this;
    }

    public RegisteredOwnerPage goToCarOwnerPage() {
        forWholeApp.clickOnFurtherButton();
        return RegisteredOwnerPage.initialize(driver);
    }

    public PreconditionPage checkTextsForPreconditionPage() {
        waitForElement(CAR_ALREADY_INSURED_TEXT);
        waitForElement(CHANGE_INSURER_TEXT);
        waitForElement(INSURER_START_TEXT);
        waitForElement(FRIDAY_ECO_TEXT);
        waitForElement(CLIMATE_PROTECTION_TEXT);
        return this;
    }
}
