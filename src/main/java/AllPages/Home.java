package AllPages;
import Actions.ElementsAction;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Home {
        WebDriver driver;
        ElementsAction actions;
         public List<WebElement> freeElements ;
         public List<WebElement> pricedElements ;

        public Home(WebDriver driver) {
            this.driver = driver;
            this.actions = new ElementsAction(driver);
        }

    String getPaidBtn = "Get paid ";
    String price = "Free";
    int minimumPrice = 30, maximumPrice = 60;

    By getPaid = By.xpath(String.format("//a[contains(text(),'%s')]/ancestor::li", getPaidBtn));
    By dropdown = By.xpath(String.format("//span[contains(@class,'price-amount') and contains(text(),'%s')]/ancestor::div[@class='panel-heading left-right-pair']//div[@class='left']", price));
    By getPrice = By.xpath(String.format("//span[contains(@class,'price-amount') and number(translate(text(),'$','')>=%d) and number(translate(text(),'$','')<=%d)]/ancestor::div[@class='panel-heading left-right-pair']//div[@class='left']", minimumPrice, maximumPrice));

    public void openHomePage() {
        driver.get("https://www.levelset.com/");
        actions.waitForVisibility(getPaid);
        Assert.assertTrue(actions.isElementDisplayed(getPaid), "Home page not loaded properly.");
        System.out.println("Home page successfully opened.");
    }

    public void clickGetPaid() {
        actions.doubleClickElement(getPaid);
        actions.waitForVisibility(dropdown);
        Assert.assertTrue(actions.isElementDisplayed(dropdown), "Dropdown not displayed.");
        System.out.println("Get Paid button successfully clicked.");
    }

    public int getFreeDocumentsCount() {
        freeElements = driver.findElements(dropdown);
        return freeElements.size();
    }

    public void printFreeDocuments() {
        freeElements = driver.findElements(dropdown);
        for (WebElement document : freeElements) {
            System.out.println("Free document: " + document.getText());
        }
    }

    public int getPriceDocumentsCount() {
        pricedElements = driver.findElements(getPrice);
        return pricedElements.size();
    }

    public void printPriceDocuments() {
        pricedElements = driver.findElements(getPrice);
        for (WebElement document : pricedElements) {
            System.out.println("Price document between $" + minimumPrice + " and $" + maximumPrice
                    + ":"+ document.getText());
        }
    }
}
