package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.BrowserFactory;
import util.BrowserUtils;

public class EnterTextPractice {
    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement email=driver.findElement(By.name("email"));
        email.sendKeys("abcd@mail.yahoo.com");
        WebElement button=driver.findElement(By.id("form_submit"));
        button.click();
        BrowserUtils.wait(2);
        String expectedUrl="http://practice.cybertekschool.com/email_sent";
        String url =  driver.getCurrentUrl();
        if (expectedUrl.equalsIgnoreCase(url)){
            System.out.println("Test passed");
        }else {
            System.out.println("Test failed");

        }
        driver.close();
    }
}
