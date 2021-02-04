package tests.day8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.BrowserFactory;
import util.BrowserUtils;

import java.util.List;

public class radioButtons {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver= BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
    }
    @Test
    public void test1(){
        driver.findElement(By.linkText("Radio Buttons")).click();
        WebElement blueButton=driver.findElement(By.id("blue"));
        //blueButton.isSelected();
        boolean isSelected=blueButton.isSelected();
        Assert.assertTrue(blueButton.isSelected());
    }
    @Test(description="Verify that red button is clickable")
    public void test2(){
        WebElement redButton=driver.findElement(By.id("red"));
        Assert.assertTrue(redButton.isSelected());
    }
    @Test(description="Verify that green button is clickable")
    public void test3() {
        WebElement greenButton = driver.findElement(By.id("green"));
        Assert.assertFalse(greenButton.isEnabled());
    }
        @Test(description="Click on all radio buttons")
    public void test4() {
            List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
            for (WebElement button : radioButtons) {
                if (button.isEnabled() && !button.isSelected()) {
                    button.click();
                    System.out.println("Button clicked:=" + button.getAttribute("id"));
                } else {
                    System.out.println("Button was not clicked:" + button.getAttribute("id"));
                }
                BrowserUtils.wait(1);
            }
        }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
