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
       String expectedTitle=driver.getTitle();
        WebElement button = driver.findElement(By.id("form_submit"));
        button.click();
        String actualTitle=driver.getTitle();
        if (actualTitle.equals(expectedTitle)){
            System.out.println("test passed");
        }else{
            System.out.println("test failed");
            System.out.println("expected title:"+expectedTitle);
            System.out.println("actual title:"+actualTitle);
        }
        BrowserUtils.wait(2);
    }
}
