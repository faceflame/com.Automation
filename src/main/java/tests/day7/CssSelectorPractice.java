package tests.day7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.BrowserFactory;
import util.BrowserUtils;

import java.util.List;

public class CssSelectorPractice {
    public static void main(String[] args) {
        WebDriver driver= BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        List<WebElement> buttons=driver.findElements(By.cssSelector(".btn.btn-primary"));
        for(WebElement button:buttons){
            button.click();
            BrowserUtils.wait(1);
            WebElement message=driver.findElement(By.cssSelector("#result"));
            System.out.println(message.getText());
        }
driver.quit();
    }
}
