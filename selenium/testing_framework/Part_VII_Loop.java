package testing_framework;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;




public class Part_VII_Loop {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By emailTextbox = By.xpath("//*[@id='email']");
	By passWord = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 3)
	public void TC_01_LoginToSystem() throws InterruptedException  {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(emailTextbox).sendKeys("selenium" + randomNumber() + "@gmail.com");
		driver.findElement(passWord).sendKeys("111111");
		driver.findElement(loginButton).click();
		
  }
  
  @AfterClass
  public void afterClass() {
		driver.quit();
  }
  public int randomNumber()
	{
		Random rand = new Random();
		int n = rand.nextInt(10000);
		return n;
	}
}
