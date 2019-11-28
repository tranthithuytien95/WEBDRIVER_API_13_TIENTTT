package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_DropDownList {
	WebDriver driver;
	Select select;
	String email = "thuytien"+ randomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_DropdownList() {
		//Step 1: open URL
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2: kiem tra dropdown ko ho tro thuoc tinh multiple select
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());
		
		//Step 3: select value = ByVisibleText
		select.selectByVisibleText("Mobile Testing");
		
		//Step 4: verify value is selected
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Mobile Testing");
		
		//Step 5: select value = ByValue
		select.selectByValue("manual");
		
		//Step 6: verify value is selected
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Manual Testing");
		
		//Step 7: select value = ByIndex
		select.selectByIndex(9);
		
		//Step 8: verify value is selected
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Functional UI Testing");
		
		//Step 9: kiem tra co bao nhieu gia tri trong dropdown list
		Assert.assertEquals(select.getOptions().size(),10);
		
		//Step 10: kiem tra dropdown list JobRole02 cos= ho tro multiple select ?
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());
		
		//Step 11: 
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		
		//Step 12
		List <WebElement> optionSelect = select.getAllSelectedOptions();
		List <String> arraySelect = new ArrayList<String>();
		for (WebElement select : optionSelect)
		{
			arraySelect.add(select.getText());
		}
		
		Assert.assertTrue(arraySelect.contains("Automation"));
		Assert.assertTrue(arraySelect.contains("Mobile"));
		Assert.assertTrue(arraySelect.contains("Desktop"));
		
		//Step 13:
		select.deselectAll();
		
		//Step 14:
		List <WebElement> unoptionSelect = select.getAllSelectedOptions();
		Assert.assertEquals(unoptionSelect.size(),0);
		
	}

	@Test
	public void TC_02_DropdownList() {
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.xpath("//a[@class = 'ico-register']")).click();
		
		driver.findElement(By.xpath("//span[@class = 'female']//label[@class ='forcheckbox']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Thuy");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Tien");
		
		select = new Select(driver.findElement(By.xpath("//select[@name = 'DateOfBirthDay']")));
		select.selectByVisibleText("5");
		Assert.assertEquals(select.getOptions().size(),32);
		
		select = new Select(driver.findElement(By.xpath("//select[@name = 'DateOfBirthMonth']")));
		select.selectByVisibleText("September");
		Assert.assertEquals(select.getOptions().size(),13);

		
		select = new Select(driver.findElement(By.xpath("//select[@name = 'DateOfBirthYear']")));
		select.selectByVisibleText("1995");
		Assert.assertEquals(select.getOptions().size(),112);

		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("AuTomation");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class = 'result']")).getText(), "Your registration completed");
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class = 'ico-account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class = 'ico-logout']")).isDisplayed());
		
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