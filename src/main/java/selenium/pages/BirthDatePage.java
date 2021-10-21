package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BirthDatePage extends Page {

    private static final String BORN_DATE_PAGE_LOCATOR = "//font[text()='When were you born?']//ancestor::form";

    @Override
    public By getPageLocator() {
        return By.xpath(BORN_DATE_PAGE_LOCATOR);
    }

    private BirthDatePage(WebDriver driver) {
        super(driver);
    }

    public static BirthDatePage initialize(WebDriver driver) {
        BirthDatePage page = new BirthDatePage(driver);
        page.waitUntilInitialized();
        return page;
    }
}
