package testing_framework;

import org.testng.annotations.Test;


public class Part_III_Priority_Enabled_Disabled {
	
  // neu ko co priority thi se chay theo alphabet
  // neu enabled = true thi chay TC, nguoc lai =  false thi ko run TC
  // description de mo ta xem TC nay test cai gi ?
  @Test(priority = 1, enabled = true, description = "Register function A")
  public void TC_01() {
	  System.out.println("Run TC_01");
  }
  
  @Test(priority = 2, enabled = false)
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