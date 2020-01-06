package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part1_ElementStatus {
	WebDriver driver;
	WebDriverWait waitExplicit;
	boolean status;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver,5);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_DisplayOrVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//DK1: Emlement co hien thi tren UI + co trong DOM: Passed
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']"))); //chờ cho Element displayed/visible
		status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed();
		System.out.println("Emlement co hien thi tren UI + co trong DOM:" + status);
		
		//DK2: Emlement khong hien thi tren UI + co trong DOM: 
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='create_account_error']"))); 
		status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		System.out.println("Emlement co hien thi tren UI + co trong DOM:" + status);
		
		//DK3: Emlement khong hien thi tren UI + khong co trong DOM: Failed
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
		status = driver.findElement(By.xpath("//input[@id='id_order']")).isDisplayed();
		System.out.println("Emlement khong hien thi tren UI + khong co trong DOM:" + status);
	}
	
	//@Test
	public void TC_02_UndisplayOrInvisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//DK1: Emlement co hien thi tren UI + co trong DOM: Failed
		//waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']"))); //chờ cho Element undisplayed/unvisible
		
		//DK2: Emlement khong hien thi tren UI + co trong DOM: Passed
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']"))); 
		
		//DK3: Emlement khong hien thi tren UI + khong co trong DOM: Passed
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
	}

	//@Test
	public void TC_03_Present() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		//DK1: Emlement co hien thi tren UI + co trong DOM: Passed
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']"))); 
				
		//DK2: Emlement khong hien thi tren UI + co trong DOM: Passed
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']"))); 
				
		//DK3: Emlement khong hien thi tren UI + khong co trong DOM: Failed
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='id_order']")));		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}