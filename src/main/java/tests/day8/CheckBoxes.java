package tests.day8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.BrowserFactory;

import java.util.List;

public class CheckBoxes {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com");
        driver.findElement(By.linkText("checkboxes")).click();
    }
    @Test
    public void test1(){
        List<WebElement> checkboxes=driver.findElements(By.cssSelector("[type='checkbox']"));
        int index=1;
        for (WebElement checkbox:checkboxes){
            if(checkbox.isEnabled()&&!checkbox.isSelected()){
                checkbox.click();
                System.out.println("checkbox #"+index+"was clicked");
            }else{
                System.out.println("checkbox #"+index+"was not clicked");
            }
            index++;
        }
    }

}
