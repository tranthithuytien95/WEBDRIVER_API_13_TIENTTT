package testing_framework;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Part_II_Group {
	WebDriver driver;
	
  @BeforeClass
  public void beforeClass() {
	  System.out.println("beforeClass");

	  //driver = new FirefoxDriver();
	  
	  //Assert.assertTrue(false);
  }
	
  @Test(groups = "user")
  public void TC_01() {
	  System.out.println("Run TC_01");
  }
  
  @Test(groups = "user")
  public void TC_02() {
	  System.out.println("Run TC_02");
  }
  
  @Test(groups = "pay")
  public void TC_03() {
	  System.out.println("Run TC_03");
  }
  
  @Test(groups = "pay")
  public void TC_04() {
	  System.out.println("Run TC_04");
  }
  
  @Test(groups = "shop")
  public void TC_05() {
	  System.out.println("Run TC_05");
  }
  
  @Test(groups = "shop")
  public void TC_06() {
	  System.out.println("Run TC_06");
  }
  
  //@AfterClass(alwaysRun = true) -> close browser when TC fail
  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  System.out.println("afterClass");
	  //driver.quit();
  }
}