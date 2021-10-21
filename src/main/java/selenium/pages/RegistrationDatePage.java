package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.pages.common.ForWholeApp;

public class RegistrationDatePage extends Page {

    ForWholeApp forWholeApp = new ForWholeApp(driver);

    private static final String FIRST_REGISTRATION_PAGE_LOCATOR = "//font[text()='When was the first registration?']//ancestor::form";
    private static final String FIRST_REGISTRATION_INPUT = "//input[@name='monthYearFirstRegistered']";
    private static final String OWNER_REGISTRATION_INPUT = "//input[@name='monthYearOwnerRegistered']";
    private static final String FIRST_REGISTRATION_TEXT = "//font[text()='First registration (month and year)']";
    private static final String CAR_REGISTERED_TEXT = "//font[text()='When was the car registered for you? ']";
    private static final String DATE_OF_PURCHASE_TEXT = "//font[text()='Mostly corresponds to the date of purchase.']";
    private static final String ADMISSION_TEXT = "//font[text()='Admission to you (month and year)']";

    @Override
    public By getPageLocator() {
        return By.xpath(FIRST_REGISTRATION_PAGE_LOCATOR);
    }

    private RegistrationDatePage(WebDriver driver) {
        super(driver);
    }

    public static RegistrationDatePage initialize(WebDriver driver) {
        RegistrationDatePage page = new RegistrationDatePage(driver);
        page.waitUntilInitialized();
        return page;
    }

    public RegistrationDatePage enterFirstRegistrationDate(String firstRegistrationDate) {
        WebElement registrationDate = waitForElement(FIRST_REGISTRATION_INPUT);
        fillText(registrationDate, firstRegistrationDate);
        return this;
    }

    public RegistrationDatePage enterOwnerRegistrationDate(String firstRegistrationDate) {
        WebElement registrationDate = waitForElement(OWNER_REGISTRATION_INPUT);
        fillText(registrationDate, firstRegistrationDate);
        return this;
    }

    public BirthDatePage goToBornDatePage() {
        forWholeApp.clickOnFurtherButton();
        return BirthDatePage.initialize(driver);
    }

    public RegistrationDatePage checkTextsOnRegistrationDatePage() {
        waitForElement(FIRST_REGISTRATION_TEXT);
        waitForElement(CAR_REGISTERED_TEXT);
        waitForElement(DATE_OF_PURCHASE_TEXT);
        waitForElement(ADMISSION_TEXT);
        return this;
    }
}
