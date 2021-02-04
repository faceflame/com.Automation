package tests.day7;

import org.testng.Assert;
import org.testng.annotations.*;



public class AnnotationsTest {
    @BeforeMethod
    public void setup(){
        System.out.println("Before method!");
    }
    @AfterMethod
    public void teardown(){
        System.out.println("After method");
    }
    @Test
    public void test1(){
        System.out.println("test1!");
       Assert.assertTrue(4==4);
    }
    @Test
    public void test2(){
        System.out.println("test2!");
    }
    @Test
    public void test(){
        System.out.println("test3!");
    }


}
