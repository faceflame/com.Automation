package tests.day3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class differencebetweenquitandclose {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver= new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/open_new_tab");
       Thread.sleep(4000);
       //driver.close();
        driver.quit();

    }
}
