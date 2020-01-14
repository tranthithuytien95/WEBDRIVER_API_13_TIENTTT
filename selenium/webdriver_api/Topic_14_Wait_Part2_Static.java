package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part2_Static {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//cho element hien thi ->tuong tac vao ->findelement/findelements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//apply de cho page duoc load xong
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Static() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("TimeStrat: " + getCurrentTime());
		//1000ms = 1s
		//1- nếu page được load xong trong 3s thì lãng phí 2s
		//2- nếu page được load xong trong 10s thì bị thiếu time -> fail testcase
		// ko flexible
		// 1 testcase = 30 steps x 1s = 30s
		// 300 testcases x 30 steps x 1s = 9000s = 2.5
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='search_query_top']")).sendKeys("automation");
		System.out.println("TimeEnd: " + getCurrentTime());
		
	}

	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}