package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_VerifyURL() {
		//Step 1: Open URL
		driver.get("http://live.demoguru99.com");
		
		//Step 2: Click My account
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title = 'My Account']")).click();
		
		//Step 3: Verufy URL HomePage
		String HomePageURL = driver.getCurrentUrl();
		Assert.assertEquals(HomePageURL, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Step 4: Click Create an Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		
		//Step 3: Verufy URL RegisPage
		String RegisPageURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisPageURL, "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_VerifyTitle() {
		//Step 1: Open URL
		driver.get("http://live.demoguru99.com");
				
		//Step 2: Click My account
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title = 'My Account']")).click();
		
		//Step 3: Verify Title Login Page
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		//Step 5: Click Create an Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		
		//Step 3: Verify Title Regis Page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");	
	}

	@Test
	public void TC_03_Navigate() {
		//Step 1: Open URL
		driver.get("http://live.demoguru99.com");
				
		//Step 2: Click My account
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title = 'My Account']")).click();
		
		//Step 3: Click Create an Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		
		//Step 4: Verufy URL RegisPage
		String RegisPageURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisPageURL, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		//Step 5: Back to Login Page
		driver.navigate().back();
		
		//Step 6: Verufy URL HomePage
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Step 7: Forward to Regis Page
		driver.navigate().forward();
		
		//Step 8: Verify Title Regis Page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		
	}
	
	@Test
	public void TC_04_GetPageSource() {
		//Step 1: Open URL
		driver.get("http://live.demoguru99.com");
				
		//Step 2: Click My account
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title = 'My Account']")).click();
		
		//Step 3: Verify MyAccount Page Source
		String MyAccountPageSource = driver.getPageSource();
		Assert.assertTrue(MyAccountPageSource.contains("Login or Create an Account"));
		
		//Step 4: Click Create an Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		
		//Step 3: Verify CreateAccount Page Source
		String CreateAccountPageSource = driver.getPageSource();
		Assert.assertTrue(CreateAccountPageSource.contains("Create an Account"));	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}