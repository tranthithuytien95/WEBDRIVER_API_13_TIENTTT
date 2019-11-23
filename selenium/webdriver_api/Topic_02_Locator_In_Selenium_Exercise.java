package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_02_Locator_In_Selenium_Exercise {
	WebDriver driver;
	String fristname = "Automation";
	String lastname = "Testing";
	String validEmail = "automation" + randomNumber() + "@gmail.com";
	String ValidPassword = "123123";


	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void BeforMethod() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class = 'footer']//*[@title='My Account']")).click();
	}
	
	@Test
	public void TC_01_LoginwithEmailandPasswordEmpty() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String EmailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(EmailErrorMsg, "This is a required field.");
		
		String PassErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(PassErrorMsg, "This is a required field.");
		
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@132.342");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String EmailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(EmailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithPasswordLess6Characters() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String EmailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(EmailErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String EmailErrorMsg = driver.findElement(By.xpath("//li[@class = 'error-msg']//span")).getText();
		Assert.assertEquals(EmailErrorMsg, "Invalid login or password.");
	}

	@Test
	public void TC_05_CreateNewAccount() {
		
		driver.findElement(By.xpath("//span[text() = 'Create an Account']")).click();
		
		System.out.println("Random email = " + validEmail);

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(fristname);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(ValidPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(ValidPassword);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'success-msg']//span[text() = 'Thank you for registering with Main Website Store.']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text() = 'Hello, " + fristname + " "+ lastname + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'box-content']/p[contains(text(),'" + fristname + " "+ lastname + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'box-content']/p[contains(.,'" + validEmail + "')]")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class = 'account-cart-wrapper']//span[text() = 'Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}
	
	@Test
	public void TC_06_LoginWithEmailAndPasswordValid() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(ValidPassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//CACH 1: 
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");

		
		//CACH 2:
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text() = 'Hello, " + fristname + " "+ lastname + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'box-content']/p[contains(text(),'" + fristname + " "+ lastname + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'box-content']/p[contains(.,'" + validEmail + "')]")).isDisplayed());
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