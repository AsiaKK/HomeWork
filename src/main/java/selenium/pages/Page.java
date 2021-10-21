package selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public abstract class Page {

    private static final int TIMEOUT = 30;
    protected WebDriver driver;
    protected FluentWait wait;

    protected Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public abstract By getPageLocator();

    protected void waitUntilInitialized() {
        Assert.assertTrue(isInitialized());
    }

    protected boolean isInitialized() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getPageLocator()));
        return true;
    }

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void fillText(WebElement inputElement, String text) {
        inputElement.click();
        inputElement.clear();
        inputElement.sendKeys(text);
        inputElement.sendKeys(Keys.ENTER);
    }

    protected boolean isValueOfAttributes(WebElement locator, String attributeName) {
        return Boolean.parseBoolean(locator.getAttribute(attributeName));
    }

    protected WebElement waitForElement(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }
}
