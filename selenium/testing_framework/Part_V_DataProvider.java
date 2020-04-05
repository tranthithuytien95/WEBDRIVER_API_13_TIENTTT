package testing_framework;

import org.testng.annotations.Test;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


public class Part_V_DataProvider {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By emailTextbox = By.xpath("//*[@id='email']");
	By passWord = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	By helloworldTextBy = By.xpath("//a[text()= 'Log Out']");
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "user_pass")
	public void TC_01oginToSystem(String username, String password) throws InterruptedException  {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passWord).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'col-1']//p")).getText().contains(username));
		
		Thread.sleep(5);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath("//header[@id = 'header']//span[text()='Account']")));
		driver.findElement(By.xpath("//header[@id = 'header']//span[text()='Account']")).click();
		
		
		
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helloworldTextBy));
		driver.findElement(By.xpath("//a[text()= 'Log Out']")).click();
		
		Thread.sleep(5);
		
		driver.findElement(By.xpath("//header[@id = 'header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()= 'Log In']")).click();
		
  }
  
  @DataProvider(name = "user_pass")
  public Object[][] UserAndPasswordData() {
    return new Object[][] {
       { "selenium_11_01@gmail.com", "111111" },
       { "selenium_11_02@gmail.com", "111111" },
       { "selenium_11_03@gmail.com", "111111" }
    };
  }
  
  @AfterClass
  public void afterClass() {
		driver.quit();
  }
}
