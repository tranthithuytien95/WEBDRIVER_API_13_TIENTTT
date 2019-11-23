package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element {
	WebDriver driver;
	
	//Enabled
	By emailTextboxBy = By.xpath("//input[@id='mail']");
	By age18RadioBy = By.xpath("//input[@id='under_18']");
	By educationTextAreaBy = By.xpath("//textarea[@id='edu']");
	By jobRole01DropdownBy = By.xpath("//select[@id='job1']");
	By developmentCheckboxBy = By.xpath("//input[@id ='development']");
	By slider01By = By.xpath("//input[@id='slider-1']");
	
	//Disabled
	By passwordTextboxBy = By.xpath("//input[@id='password']");	
	By ageDisableRadioBy = By.xpath("//input[@id='radio-disabled']");	
	By biographyTextAreaBy = By.xpath("//textarea[@id='bio']");	
	By jobRole2DropdownBy = By.xpath("//select[@id='job2']");	
	By checkboxDisableBy = By.xpath("//input[@id='check-disbaled']");	
	By slider02By = By.xpath("//input[@id='slider-2']");	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Displayed() {
		//Step 1: Open URL
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2 & 3: Verify Email, Age, Education isDisplayed, neu co truyen value
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		if (emailTextbox.isDisplayed())
		{
			emailTextbox.sendKeys("Automation Testing");
		}
		
		WebElement age18Radio = driver.findElement(age18RadioBy);
		if (age18Radio.isDisplayed())
		{
			age18Radio.click();
		}
		
		WebElement educationTextArea = driver.findElement(educationTextAreaBy);
		if (educationTextArea.isDisplayed())
		{
			educationTextArea.sendKeys("Automation Testing");
		}
		
	}

	@Test
	public void TC_02_EnabledDisable() {
		//Step 1: Open URL
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2: Verify Enabled
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		if (emailTextbox.isEnabled())
		{
			System.out.println("Element [" + emailTextboxBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + emailTextboxBy + "] is disabled");
		}
		
		WebElement age18Radio = driver.findElement(age18RadioBy);
		if (age18Radio.isEnabled())
		{
			System.out.println("Element [" + age18RadioBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + age18RadioBy + "] is disabled");
		}
		
		WebElement educationTextArea = driver.findElement(educationTextAreaBy);
		if (educationTextArea.isEnabled())
		{
			System.out.println("Element [" + educationTextAreaBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + educationTextAreaBy + "] is disabled");
		}
		
		WebElement jobRole01Dropdown = driver.findElement(jobRole01DropdownBy);
		if (jobRole01Dropdown.isEnabled())
		{
			System.out.println("Element [" + jobRole01DropdownBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + jobRole01DropdownBy + "] is disabled");
		}
		
		WebElement developmentCheckbox = driver.findElement(developmentCheckboxBy);
		if (developmentCheckbox.isEnabled())
		{
			System.out.println("Element [" + developmentCheckboxBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + developmentCheckboxBy + "] is disabled");
		}
		
		WebElement slider01 = driver.findElement(slider01By);
		if (slider01.isEnabled())
		{
			System.out.println("Element [" + slider01By + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + slider01By + "] is disabled");
		}
		
		//Step 3: Verify Disabled
		WebElement passwordTextbox = driver.findElement(passwordTextboxBy);
		if (passwordTextbox.isEnabled())
		{
			System.out.println("Element [" + passwordTextboxBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + passwordTextboxBy + "] is disabled");
		}
		
		WebElement ageDisableRadio = driver.findElement(ageDisableRadioBy);
		if (ageDisableRadio.isEnabled())
		{
			System.out.println("Element [" + ageDisableRadioBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + ageDisableRadioBy + "] is disabled");
		}
		
		WebElement biographyTextArea = driver.findElement(biographyTextAreaBy);
		if (biographyTextArea.isEnabled())
		{
			System.out.println("Element [" + biographyTextAreaBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + biographyTextAreaBy + "] is disabled");
		}
		
		
		WebElement jobRole2Dropdown = driver.findElement(jobRole2DropdownBy);
		if (jobRole2Dropdown.isEnabled())
		{
			System.out.println("Element [" + jobRole2DropdownBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + jobRole2DropdownBy + "] is disabled");
		}
		
		WebElement checkboxDisable = driver.findElement(checkboxDisableBy);
		if (checkboxDisable.isEnabled())
		{
			System.out.println("Element [" + checkboxDisableBy + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + checkboxDisableBy + "] is disabled");
		}
		
		WebElement slider02 = driver.findElement(slider02By);
		if (slider02.isEnabled())
		{
			System.out.println("Element [" + slider02By + "] is enabled");
		}
		else
		{
			System.out.println("Element [" + slider02By + "] is disabled");
		}
		
	}

	@Test
	public void TC_03_Selected() {
		//Step 1: Open URL
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Step 2: Select AgeUnder18, Interest (Development)
		WebElement age18Radio = driver.findElement(age18RadioBy);
		WebElement developmentCheckbox = driver.findElement(developmentCheckboxBy);
		
		//when want select, verify it unselect
		Assert.assertFalse(age18Radio.isSelected());
		Assert.assertFalse(developmentCheckbox.isSelected());
		
		age18Radio.click();
		developmentCheckbox.click();
		
		//Step 3: verify AgeUnder18, Interest (Development) are selected
		Assert.assertTrue(age18Radio.isSelected());
		Assert.assertTrue(developmentCheckbox.isSelected());
		
		//Step 4: unselect Interest (Development)
		developmentCheckbox.click();
		
		//Step 5: verify Interest (Development) is unselect
		Assert.assertTrue(age18Radio.isSelected());
		Assert.assertFalse(developmentCheckbox.isSelected());
		}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}