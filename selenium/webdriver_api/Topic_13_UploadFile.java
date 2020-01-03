package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_UploadFile {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String image0 = projectPath + "/uploadFiles/image0.JPG";
	String image1 = projectPath + "/uploadFiles/image1.JPG";
	String image2 = projectPath + "/uploadFiles/image2.JPG";
	String image3 = projectPath + "/uploadFiles/image3.JPG";

	@BeforeClass
	public void beforeClass() {
		//Firefox lastest
		System.setProperty("webdriver.gecko.driver", projectPath + "/libraries/geckodriver");
		driver = new FirefoxDriver();
		
		//Chrome
		//System.setProperty("webdriver.gecko.driver", projectPath + "/libraries/chromedriver");
		//driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Sendkey() throws InterruptedException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = find("//input[@name='files[]']");
		//upload multiple only active when Firefox >=55 and selenium moi nhat
		uploadFile.sendKeys(image0 + "\n" + image1 + "\n" + image2 + "\n" + image3);
		
		List <WebElement> startButton = finds("//table//button[@class = 'btn btn-primary start']");
		Thread.sleep(3000);
		for(WebElement start: startButton ) {
			start.click();
			Thread.sleep(3000);
		}
		Assert.assertTrue(find("//p[@class = 'name']//a[@title = 'image0.JPG']").isDisplayed());
		Assert.assertTrue(find("//p[@class = 'name']//a[@title = 'image1.JPG']").isDisplayed());
		Assert.assertTrue(find("//p[@class = 'name']//a[@title = 'image2.JPG']").isDisplayed());
		Assert.assertTrue(find("//p[@class = 'name']//a[@title = 'image3.JPG']").isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public WebElement find(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	public List <WebElement> finds(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

}