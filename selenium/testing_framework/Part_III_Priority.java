package testing_framework;

import org.testng.annotations.Test;


public class Part_III_Priority {
	
	
  @Test(priority = 1)
  public void TC_01() {
	  System.out.println("Run TC_01");
  }
  
  @Test(priority = 2)
  public void TC_02() {
	  System.out.println("Run TC_02");
  }
  
  @Test(priority = 3)
  public void TC_03() {
	  System.out.println("Run TC_03");
  }
  
  @Test(priority = 4)
  public void TC_04() {
	  System.out.println("Run TC_04");
  }
  
  @Test(priority = 5)
  public void TC_05() {
	  System.out.println("Run TC_05");
  }
  
  @Test(priority = 6)
  public void TC_06() {
	  System.out.println("Run TC_06");
  }
  
}