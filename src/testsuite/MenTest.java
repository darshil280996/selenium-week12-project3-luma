package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utility.Utility;

import java.time.Duration;

public class MenTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() {

        //Mouse Hover on Men Menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Men']"));

        //Mouse Hover on Bottoms
        mouseHoverToElement(By.xpath("(//span[contains(text(),'Bottoms')])[2]"));

        //Click on Pants
        clickOnElement(By.xpath("//a[@id='ui-id-23']"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // * * Mouse Hover on product name
        clickOnElement(By.xpath(" //a[normalize-space()='Cronus Yoga Pant']"));

        // * ‘Cronus Yoga Pant’ and click on size 32.
        clickOnElement(By.xpath("//div[@id='option-label-size-143-item-175']"));

        //Cronus Yoga Pant and click on colour Black.
        clickOnElement(By.xpath("//div[@id='option-label-color-93-item-49']"));

        clickOnElement(By.xpath("//li[1]//div[1]//div[1]//div[3]//div[1]//div[1]//form[1]//button[1]//span[1]"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Assert.assertEquals("Not redirected to login page", "You added Cronus Yoga Pant to your shopping cart.", getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        // Click on ‘shopping cart’ Link into message

        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ///** Verify the text ‘Shopping Cart.’*/
        Assert.assertEquals("Label does not match", "Shopping Cart", getTextFromElement(By.xpath("//span[@class='base']")));

        //**Verify the product name ‘Cronus Yoga Pant’*/
        Assert.assertEquals("name doesn't match", "Cronus Yoga Pant", getTextFromElement(By.xpath("//td[@class='col item']//a[normalize-space()='Cronus Yoga Pant']")));

        //**Verify the product colour ‘Black’*/
        Assert.assertEquals("Colour is not matched", "Black", getTextFromElement(By.xpath("//dd[contains(text(),'Black ')]")));

        //**Verify the product size ‘32’*/
        Assert.assertEquals("Size is not correct", "32", getTextFromElement(By.xpath("//dd[contains(text(),'32 ')]")));


    }

    @After
    public void closeBrowser() {

        driver.close();
    }
}