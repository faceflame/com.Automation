package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.BrowserFactory;

public class ReadAttibuteTest {
    public static void main(String[] args) {
        WebDriver driver= BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement input =driver.findElement(By.name("email"));
        System.out.println(input.getAttribute("pattern"));
        input.sendKeys("random123@gmail.com");
        System.out.println(input.getAttribute("value"));
        driver.close();
    }
}
