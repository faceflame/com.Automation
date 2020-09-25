package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.BrowserFactory;
import util.BrowserUtils;

public class FindElementsTest {
    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com./forgot_password");
        WebElement button = driver.findElement(By.id("form_submit"));
        button.click();
        BrowserUtils.wait(2);driver.close();
    }
}
