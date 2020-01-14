package webdriver_api;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_14_Wait_Part5_Fluent {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Fluent() {
		driver.get("http://demo.guru99.com/");
		// implicit/explicit cứ mỗi 0,5s check lại 1 lần, nếu chưa tìm thấy thì tìm lại cho đến khi hết timeout
		//Fluent có thể set set bao nhiêu s/ms check lại 1 lần
		
		fluentDriver.withTimeout(15, TimeUnit.SECONDS);
		
		//Tan so moi 1s check 1 lan
		fluentDriver.pollingEvery(1,TimeUnit.SECONDS);
		//Neu gap expection ko phai la element se bo qua
		fluentDriver.ignoring(NoSuchElementException.class);
		
		WebElement loginButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>(){

			@Override
			public WebElement apply(WebDriver driver) {
				System.out.println(new Date());
				return driver.findElement(By.xpath("//input[@name='btnLogin']"));
			}
		});
		loginButton.click();
	}

	@Test
	public void TC_01_Fluent_WebElement() {
	driver.get("https://automationfc.github.io/fluent-wait/");
	WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	fluentElement = new FluentWait<WebElement>(countdount);
	
	fluentElement.withTimeout(15, TimeUnit.SECONDS);
	
	//Tan so moi 1s check 1 lan
	fluentElement.pollingEvery(1,TimeUnit.SECONDS);
	//Neu gap expection ko phai la element se bo qua
	fluentElement.ignoring(NoSuchElementException.class);
	
	boolean status = (Boolean) fluentElement.until(new Function<WebElement, Boolean>(){
		@Override
		public Boolean apply(WebElement element) {
			//ktra dieu kien countdount = 00
			boolean flag = element.getText().endsWith("02");
			System.out.println("Time = " + element.getText());
			//return gia tri cho function apply
			return flag;
		}
	});
	System.out.println("Status = " + status);
	Assert.assertTrue(status);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}