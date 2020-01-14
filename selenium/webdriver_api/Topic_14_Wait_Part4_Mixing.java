package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_Wait_Part4_Mixing {
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 7);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Found_Element() {
		driver.get("http://live.demoguru99.com/");
		
		//Implicit
		System.out.println("-------STEP 01 - Start TC_01_Found_Element: " + new Date() + "--------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='newsletter']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (Exception ex){
			System.out.println("Switch to catch exception!");
		}
		System.out.println("-------STEP 01 - End TC_01_Found_Element: " + new Date() + "--------");
		
		//Explicit
		System.out.println("-------STEP 02 - Start TC_01_Found_Element: " + new Date() + "--------");
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button search-button']")));
		} catch (Exception ex){
			System.out.println("Switch to catch exception!");
		}
		System.out.println("-------STEP 02 - End TC_01_Found_Element: " + new Date() + "--------");
		
	}

	@Test
	public void TC_02_Not_Found_Element() {
		driver.get("http://live.demoguru99.com/");
		
		//Implicit (Not found Elemenet): nhan timeout = implicit
		System.out.println("-------STEP 01 - Start TC_02_Not_Found_Element: " + new Date() + "--------");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='newsletter00']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
		} catch (NoSuchElementException ex){
			System.out.println("*****Nhay vao catch*****");
			System.out.println(ex.getMessage());
		}
		System.out.println("-------STEP 01 - End TC_02_Not_Found_Element: " + new Date() + "--------");
		
		
		//Explicit (WebElement): nhan timeout = implicit
		System.out.println("-------STEP 02 - Start TC_02_Not_Found_Element(WebELement): " + new Date() + "--------");
		try {
			waitExplicit.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='button search-button00']"))));
		} catch (Exception ex){
			System.out.println("*****Nhay vao catch*****");
			System.out.println(ex.getMessage());
		}
		System.out.println("-------STEP 02 - End TC_02_Not_Found_Element(WebELement): " + new Date() + "--------");
		
		
		//Explicit (By): nhan timeout = explicit
		System.out.println("-------STEP 03 - Start TC_02_Not_Found_Element(By): " + new Date() + "--------");
		try {
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button search-button00']")));
		} catch (Exception ex){
			System.out.println("*****Nhay vao catch*****");
			System.out.println(ex.getMessage());
		}
		System.out.println("-------STEP 03 - End TC_02_Not_Found_Element(By): " + new Date() + "--------");
		
		
		// nếu ko set timeout of implicit/explicit thì sẽ set = 0~1s
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}