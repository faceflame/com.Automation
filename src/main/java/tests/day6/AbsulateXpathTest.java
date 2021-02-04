package tests.day6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.BrowserFactory;
import util.BrowserUtils;

public class AbsulateXpathTest {
    public static void main(String[] args) {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://login1.nextbasecrm.com/?login=yes");
        driver.findElement(By.className("login-btn")).click();
        BrowserUtils.wait(2);
        WebElement warningMessage=driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div[2]"));
        System.out.println(warningMessage.getText());


    }
}
