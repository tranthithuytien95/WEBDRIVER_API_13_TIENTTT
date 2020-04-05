package testing_framework;

import org.testng.annotations.Test;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



public class Part_VI_Parameter_MutiBrowser {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By emailTextbox = By.xpath("//*[@id='email']");
	By passWord = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	By helloworldTextBy = By.xpath("//a[text()= 'Log Out']");
	
	
	@Parameters("browser")
	@BeforeClass
	//@Optional("firefox") để nếu quên setting bên file run.xml thi nó sẽ chạy với default là firefox
	public void beforeClass(@Optional("firefox")String browserName) {
		
		if(browserName.equals("firefox")){
		driver = new FirefoxDriver();
		} else if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "./libraries/chromedriver");
			driver = new ChromeDriver();
			
		} else if(browserName.equals("chrome_headless")){
			System.setProperty("webdriver.chrome.driver", "./libraries/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		}
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
	@Parameters("url")
	@Test
	public void TC_01oginToSystem(String URL) throws InterruptedException  {
		driver.get(URL);
		
		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passWord).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		
		Thread.sleep(6);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(By.xpath("//header[@id = 'header']//span[text()='Account']")));
		driver.findElement(By.xpath("//header[@id = 'header']//span[text()='Account']")).click();
	
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helloworldTextBy));
		driver.findElement(By.xpath("//a[text()= 'Log Out']")).click();
		
		Thread.sleep(5);
		
		
  }
  
  @AfterClass
  public void afterClass() {
		driver.quit();
  }
}
