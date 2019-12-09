package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor javascript;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Button() {
		driver.get("http://live.guru99.com/");
		
		//click on MY ACCOUNT by JS
		clickByJS("//div[@class='footer']//a[@title = 'My Account']");
		
		//Verify URL correct
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Click on Create an account by JS
		clickByJS("//span[text() = 'Create an Account']");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}


	//@Test
	public void TC_02_CheckboxbyJS() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		//check ON Dual-zone air conditioning and verify is choose
		clickByJS("//input[@id='eq5']");
		Assert.assertTrue(isSelected("//input[@id='eq5']"));
		
		//check OFF Dual-zone air conditioning and verify is unchoose
		clickByJS("//input[@id='eq5']");
		Assert.assertFalse(isSelected("//input[@id='eq5']"));
	}

	//@Test
	public void TC_03_radioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//Click on [2.0 Petrol, 147kW]radio and verify is choose
		clickByJS("//input[@id = 'engine3']");
		Assert.assertTrue(isSelected("//input[@id = 'engine3']"));
		
	}
	
	//@Test
	public void TC_04_handleAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String messageResult = "//p[@id='result']";
		String Name = "Automation Testing";
		
		
		
		driver.findElement(By.xpath("//button[text() = 'Click for JS Alert']")).click();
		alert = driver.switchTo().alert();

		//Accept Alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(messageResult)).getText(), "You clicked an alert successfully");
		
		//Cancel Alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(messageResult)).getText(), "You clicked: Cancel");
		
		//Prompt Alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text() = 'Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(Name);
		Thread.sleep(5000);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(messageResult)).getText(), "You entered: " + Name);
	}
	
	
	@Test
	//truong hop ko biet truoc url
	public void TC_06_authenticationAlert() {
		driver.get("http://the-internet.herokuapp.com");
		
		String username = "admin";
		String password = "admin";
		
		WebElement bassicAuthenLink = driver.findElement(By.xpath("//a[text() = 'Basic Auth']"));
		String url = bassicAuthenLink.getAttribute("href");
		
		driver.get(byPassAuthenticationAlert(url, username, password));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]")).isDisplayed());
	}
	
	public String byPassAuthenticationAlert(String url, String username, String password) {
		System.out.println("Old URL = " +url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + username + ":" + password +"@"+ splitUrl[1];
		System.out.println("New URL = " + url);
		return url;
		
	}
	
	public void checkToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click();", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}