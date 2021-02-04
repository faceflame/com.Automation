package tests.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.BrowserFactory;
import util.BrowserUtils;

public class EnterTextPractice2 {
    public static void main(String[] args) {
        WebDriver driver= BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement input=driver.findElement(By.name("email"));
        input.sendKeys("random123@gmail.com", Keys.ENTER);
        WebElement confirmationMessage= driver.findElement(By.name("confirmation_message"));
        String expectedMessage="Your e-mail's been sent!";
        String actualMessage=confirmationMessage.getText();
    if(expectedMessage.equals(actualMessage)){
        System.out.println("test passed");
    }else{
        System.out.println("test failed");

    }
    }
}
