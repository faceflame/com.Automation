package tests.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class myfirstselenium {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver= new ChromeDriver();
        driver.get("http://google.com");
        String actualResult=driver.getTitle();
        String expectedResult="Google";
        if(actualResult.equals(expectedResult)) {
            System.out.println("Test Passed");
        }else{
            System.out.println("Test Failed");
        }
driver.close();
    }
}
