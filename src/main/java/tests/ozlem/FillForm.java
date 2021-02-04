package tests.ozlem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import util.BrowserFactory;


public class FillForm {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver= BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/registration_form");
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("OZLEM");
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("SAHIN");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("turkmen-sahin@hotmail.com");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("akrepler");
        Select select=new Select(driver.findElement(By.cssSelector("select[name='job_title']")));
                select.selectByVisibleText("MSR");
        driver.findElement(By.id("wooden_spoon")).click();

        Thread.sleep(5000);
        driver.close();
;    }

}
