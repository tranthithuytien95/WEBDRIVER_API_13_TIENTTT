package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_JavacriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	
	String username = "mngr238966";
	String password = "emYmEqe";
	
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

		@BeforeClass
		public void beforeClass() {
			String projectPath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", projectPath + "/libraries/chromedriver");
			driver = new ChromeDriver();
			
			//driver = new FirefoxDriver();
			// Set driver for JE lib
			jsExecutor = (JavascriptExecutor) driver;

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
	
		//@Test
		public void TC_01_JavascriptExecutor() throws InterruptedException {
			navigateToUrlByJS("http://live.demoguru99.com/index.php/");
			
			//get domain page
			String liveDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(liveDomain, "live.demoguru99.com");
			
			//get URL page
			String URL = (String) executeForBrowser("return document.URL");
			Assert.assertEquals(URL, "http://live.demoguru99.com/index.php/");
			
			//click on Mobile tab
			clickToElementByJS("//a[text() = 'Mobile']");
			//Click on Add to cart of Samsung Galaxy
			clickToElementByJS("//a[@title = 'Samsung Galaxy']//parent::h2//following-sibling::div[@class = 'actions']//button");
			
			//verify text is displayed correct
			String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText;");
			Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));
			
			//Click on service customer and verify title
			clickToElementByJS("//a[text() = 'Customer Service']");
			Thread.sleep(3000);
			String customerServiceTitle = (String) executeForBrowser("return document.title");
			Assert.assertEquals(customerServiceTitle, "Customer Service");
			
			//scroll to newsletter text box
			scrollToElement("//input[@id='newsletter']");
			Thread.sleep(3000);
			//verify text is displayed
			String pageInnerText1 = (String) executeForBrowser("return document.documentElement.innerText;");
			Assert.assertTrue(pageInnerText1.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
			
			//navigate to 
			navigateToUrlByJS("http://demo.guru99.com/v4/");
			String demoDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(demoDomain, "demo.guru99.com");
		}
		
		//@Test
		public void TC_02_removeAttribute() throws InterruptedException {
			driver.get("http://demo.guru99.com/v4/");
			driver.findElement(By.name("uid")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.name("btnLogin")).click();
			
			String MessageWelcome = driver.findElement(By.tagName("marquee")).getText();
			Assert.assertEquals(MessageWelcome, "Welcome To Manager's Page of Guru99 Bank");
			Assert.assertTrue(driver.findElement(By.xpath("//td[text() = 'Manger Id : " + username + "']")).isDisplayed());
			
			driver.findElement(By.xpath("//a[text() = 'New Customer']")).click();
			
			//input data
			driver.findElement(nameTextbox).sendKeys(customerName);
			driver.findElement(genderRadio).click();
			
			//Remove attribute type = 'date' (Date of Birth)
			removeAttributeInDOM("//input[@id = 'dob']", "type");
			Thread.sleep(3000);
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
			
		}

		@Test
		public void TC_03_createAccount() {
			navigateToUrlByJS("http://live.guru99.com/");
			clickToElementByJS("//div[@id='header-account']//a[text() = 'My Account']");
			clickToElementByJS("//span[text()='Create an Account']");
			
			sendkeyToElementByJS("//input[@id='firstname']", "Automation");
			sendkeyToElementByJS("//input[@id='lastname']", "FC");
			sendkeyToElementByJS("//input[@id='email_address']", "automationfc"+ randomNumber() + "@gmail.com");
			sendkeyToElementByJS("//input[@id='password']", "123456");
			sendkeyToElementByJS("//input[@id='confirmation']", "123456");
			
			clickToElementByJS("//button[@title = 'Register']");
			
			String InnerText = (String) executeForBrowser("return document.documentElement.innerText;");
			Assert.assertTrue(InnerText.contains("Thank you for registering with Main Website Store."));
			
			clickToElementByJS("//a[text()= 'Log Out']");
			
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
		}
		// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
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