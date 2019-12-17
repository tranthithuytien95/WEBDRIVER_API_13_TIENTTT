package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Popup_Iframe {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Popup() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		boolean fancypopup = driver.findElement(By.xpath("//img[@class = 'fancybox-image']")).isDisplayed();
		System.out.println("Fancy Popup is displayed: "+ fancypopup);
		Assert.assertTrue(fancypopup);
		
		driver.findElement(By.xpath("//a[@class = 'fancybox-item fancybox-close']")).click();
		
		//index
		//driver.switchTo().frame(1);
		
		//id or name
		//driver.switchTo().frame("");
		
		//webelement
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class = 'face-content']//iframe")));
		
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		System.out.println("FacebookIframe is displayed:" + facebookIframe);
		Assert.assertTrue(facebookIframe);
		
		String Like = driver.findElement(By.xpath("//a[text() = 'Kyna.vn']//parent::div//following-sibling::div")).getText();
		Assert.assertEquals(Like, "170K likes");
		
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//header[@id = 'header']//a[@class = 'button-login']")).click();
		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
		
		String userLogin = driver.findElement(By.xpath("//span[@class ='user' and text() = 'Automation FC']")).getText();
		Assert.assertEquals(userLogin, "Automation FC");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}