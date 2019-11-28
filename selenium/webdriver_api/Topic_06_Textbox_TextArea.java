package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	
	String username = "mngr233468";
	String password = "tYqAhaq";
	String customerID;
	
	//Locator
	By nameTextbox = By.name("name");
	By genderRadio = By.xpath("//input[@value = 'm']");
	By genderTextbox = By.name("gender");
	By dobTextbox = By.name("dob");
	By addressTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	
	//Data for New Customer
	String customerName = "ThuyTien";
	String DOB = "1995-05-09";
	String gender = "male";
	String address = "123 ABC";
	String city = "HCM";
	String state = "tp";
	String pin = "123456";
	String phone = "01236777888";
	String email = "thuytien"+ randomNumber() + "@gmail.com";
	
	//Data for Edit Customer
	String addressEdit = "123 ABC Edit";
	String cityEdit = "TienGiang";
	String stateEdit = "GoCong";
	String pinEdit = "444555";
	String phoneEdit = "06666777888";
	String emailEdit = "thuytien"+ randomNumber() + "@hotgmail.com";
		

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		String MessageWelcome = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(MessageWelcome, "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertTrue(driver.findElement(By.xpath("//td[text() = 'Manger Id : " + username + "']")).isDisplayed());
		
		
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		
		driver.findElement(By.xpath("//a[text() = 'New Customer']")).click();
		
		//input data
		driver.findElement(nameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		driver.findElement(dobTextbox).sendKeys(DOB);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(email);
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		//verify create new customer successul
		String messageRegisSuccessful = driver.findElement(By.xpath("//p[@class = 'heading3']")).getText();
		Assert.assertEquals(messageRegisSuccessful, "Customer Registered Successfully!!!");
		
		//Get thong tin Customer ID
		customerID = driver.findElement(By.xpath("//td[text() ='Customer ID']/following-sibling::td")).getText();
		System.out.println("CustomerID = "+ customerID);
		
		//verify all value of new customer
		Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[text() ='Customer ID']/following-sibling::td")).getText());
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text() = 'Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text() = 'Gender']/following-sibling::td")).getText());
		Assert.assertEquals(DOB, driver.findElement(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText());
		
	}

	@Test
	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		//verify CustomerName, Gender, DOB is disabled.
		Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());
		
		//verify data edit = data new customer
		Assert.assertEquals(customerName, driver.findElement(nameTextbox).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(genderTextbox).getAttribute("value"));
		Assert.assertEquals(DOB, driver.findElement(dobTextbox).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTextArea).getAttribute("value"));
		Assert.assertEquals(city, driver.findElement(cityTextbox).getAttribute("value"));
		Assert.assertEquals(state, driver.findElement(stateTextbox).getAttribute("value"));
		Assert.assertEquals(pin, driver.findElement(pinTextbox).getAttribute("value"));
		Assert.assertEquals(phone, driver.findElement(phoneTextbox).getAttribute("value"));
		Assert.assertEquals(email, driver.findElement(emailTextbox).getAttribute("value"));
		
		//Edit data
		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(addressEdit);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(cityEdit);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(stateEdit);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(pinEdit);
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(phoneEdit);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(emailEdit);
		
		driver.findElement(By.name("sub")).click();
		
		//verify data after edit.
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		
		Assert.assertEquals(addressEdit, driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText());
		Assert.assertEquals(cityEdit, driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText());
		Assert.assertEquals(stateEdit, driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText());
		Assert.assertEquals(pinEdit, driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phoneEdit, driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(emailEdit, driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText());
		
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