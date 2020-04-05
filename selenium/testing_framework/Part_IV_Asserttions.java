package testing_framework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Part_IV_Asserttions {
  @Test
  public void TC_01() {
	  
	  String  testNGFramework = "TestNG";
	  String  junitFramework = "JUnit";
	  
	  boolean status = testNGFramework.equals(junitFramework);
	  System.out.println("Status = " + status);
	  //false
	  
	  boolean assertion = testNGFramework.equals(testNGFramework);
	  System.out.println("Status = " + assertion);
	  //false
	  
	  Assert.assertFalse(status);
	  Assert.assertTrue(assertion);
	  
	  Assert.assertEquals(testNGFramework, "TestNG");
	  Assert.assertNotEquals(testNGFramework, junitFramework);
	  
	  Object object = null;
	  
	  Assert.assertNull(object);
	  
	  object = "Automation";
	  object = 11;
	  object = 11.11;
	  
	  Assert.assertNotNull(object);
	  
	  
  }
}
