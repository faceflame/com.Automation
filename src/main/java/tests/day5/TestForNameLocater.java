package tests.day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.BrowserFactory;
import util.BrowserUtils;

public class TestForNameLocater {
    public static void main(String[] args) {
        WebDriver driver= BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/sign_up");
driver.findElement(By.name("full_name"));
        driver.findElement(By.name("full_name")).sendKeys("TestUser");
        driver.findElement(By.name("email")).sendKeys("email@gmail.com");
        driver.findElement(By.name("wooden_spoon")).click();
        BrowserUtils.wait(3);
        driver.quit();
    }
}
