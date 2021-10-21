package selenium.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ForWholeApp {

    protected FluentWait wait;
    protected WebDriver driver;
    private static final int TIMEOUT = 10;
    private static final String SUBMIT_BUTTON = "//button[@type='submit']";
    private static final String RETURN_BUTTON = "//button[@data-testid='wizardBackButton']";

    public ForWholeApp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchFieldException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public void clickOnFurtherButton() {
        WebElement furtherButton = waitForElementToBeClickable(By.xpath(SUBMIT_BUTTON));
        furtherButton.click();
    }

    public void clickOnReturnButton() {
        WebElement returnButton = waitForElementToBeClickable(By.xpath(RETURN_BUTTON));
        returnButton.click();
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebElement button = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        return button;
    }
}
