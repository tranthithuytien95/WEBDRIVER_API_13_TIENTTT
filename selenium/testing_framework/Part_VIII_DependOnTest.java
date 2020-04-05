package testing_framework;


import org.junit.Assert;
import org.testng.annotations.Test;

public class Part_VIII_DependOnTest {
	
 
  @Test()
  public void TC_01() {
	  System.out.println("Run TC_01");
	  Assert.assertTrue(false);
  }
  
  @Test(dependsOnMethods = "TC_01")
  public void TC_02() {
	  System.out.println("Run TC_02");
  }
  
  @Test(dependsOnMethods = "TC_02")
  public void TC_03() {
	  System.out.println("Run TC_03");
  }
  
}