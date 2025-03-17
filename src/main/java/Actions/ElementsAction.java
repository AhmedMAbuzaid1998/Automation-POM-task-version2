package Actions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;

public class ElementsAction {
    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    public ElementsAction(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
        }

    public WebElement waitForVisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not visible: " + e.getMessage());
            return null;
        }
    }

    public WebElement waitForClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not clickable: " + e.getMessage());
            return null;
        }
    }

    public void doubleClickElement(By locator) {
        try {
            WebElement element = waitForClickable(locator);
            if (element != null) {
                action.doubleClick(element).perform();
            } else {
                System.out.println("Cannot double-click: Element is null.");
            }
        } catch (Exception e) {
            System.out.println("Error double-clicking element: " + e.getMessage());
        }
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
    }
}
