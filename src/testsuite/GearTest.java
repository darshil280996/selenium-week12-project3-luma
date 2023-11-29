package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utility.Utility;

import java.time.Duration;

import static browserfactory.BaseTest.driver;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {

        //Mouse Hover on Gear Menu and click
        mouseHoverToElement(By.xpath("//span[normalize-space()='Gear']"));

        //Click on Bags
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));

        //Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));

        //Change Qty 3
        sendTextToElement(By.xpath("//input[@id='qty']"), Keys.DELETE + "3");

        //Click on ‘Add to Cart’ Button.
        clickOnElement(By.xpath("//span[normalize-space()='Add to Cart']"));

        //Verify the text
        Assert.assertEquals("Label does not match", "You added Overnight Duffle to your shopping cart.", getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));

        //Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        //* Verify the Qty is ‘3’
        Assert.assertEquals("Quantity does not match", "3", getTextFromElement(By.xpath("//input[@id='cart-492548-qty']")));

        //Verify the product name ‘Overnight Duffle’
        Assert.assertEquals("Label does not match", "Overnight Duffle", getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']")));

        //Verify the product price ‘$135.00’
        Assert.assertEquals("Price does not match", "$135.00", getTextFromElement(By.xpath("//span[@data-bind='text: getValue()'][normalize-space()='$135.00']")));
        Thread.sleep(2000);

        //* Change Qty to ‘5’
        sendTextToElement(By.cssSelector("td[class='col qty'] input[class*='input-text qty']"), Keys.DELETE + "5");

        // Click On 'Update Shopping Cart' button
        clickOnElement(By.xpath("//span[normalize-space()='Update Shopping Cart']"));

        // Verify the product price '$225.00'
        Assert.assertEquals("$225.00", By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$225.00']"));
    }

    @After
    public void closeBrowser() {

        driver.close();
    }
}

