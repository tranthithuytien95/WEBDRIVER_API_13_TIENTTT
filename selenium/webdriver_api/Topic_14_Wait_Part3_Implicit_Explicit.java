package webdriver_api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait_Part3_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By startButtonBy = By.xpath("//button[text() = 'Start']");
	By loadingImageBy = By.xpath("//div[@id='loading']//img");
	By helloworldTextBy = By.xpath("//h4[contains(text(),'Hello World!')]");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//rõ ràng: chờ element nào có trạng thái cụ thể
		waitExplicit = new WebDriverWait(driver, 30);
		
		//ngầm định: ko chờ element nào có trạng thái cụ thể -> đi tìm element 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Implicit() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//1- open page: http://the-internet.herokuapp.com/dynamic_loading/2
		//2- wait cho button Start is displayed
		//3- Click Start Button
		//4- Hien thi Loading bar: Invisiable(Undisplay)
		// Loading bien mat (toi 5s) du nhanh hay cham
		//5- Helloworld text duoc hien thi visiable(display)
		
		//cho element duoc hien thu (Visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		//Click Start Button
		System.out.println("Time Strat: " + getCurrentTime());
		startButton.click();
		System.out.println("Time End: " + getCurrentTime());
		
		//Loading icon hien thi va bien mat tới 5s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Check helloword duoc hien thi
		System.out.println("Time Strat: " + getCurrentTime());
		WebElement helloWord = driver.findElement(helloworldTextBy);
		Assert.assertTrue(helloWord.isDisplayed());
		System.out.println("Time End: " + getCurrentTime());
		
	}
	
	//@Test
	public void TC_02_Implicit_Override() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//cho element duoc hien thu (Visible)
				WebElement startButton = driver.findElement(startButtonBy);
				Assert.assertTrue(startButton.isDisplayed());
				
				//Click Start Button
				System.out.println("Time Strat: " + getCurrentTime());
				startButton.click();
				System.out.println("Time End: " + getCurrentTime());
				
				//Loading icon hien thi va bien mat tới 5s
				
				//Check helloword duoc hien thi
				System.out.println("Time Strat: " + getCurrentTime());
				WebElement helloWord = driver.findElement(helloworldTextBy);
				Assert.assertTrue(helloWord.isDisplayed());
				System.out.println("Time End: " + getCurrentTime());
	}

	//@Test
	public void TC_03_Explicit_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Click Strat button
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy)); //nhan timeout of Explicit
		driver.findElement(startButtonBy).click();
		
		//Loading icon bien mat sau 5s
		
		//Wait cho helloworld duoc hien thi
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helloworldTextBy));
		
		System.out.println("Time Strat: " + getCurrentTime());
		WebElement helloWord = driver.findElement(helloworldTextBy);
		Assert.assertTrue(helloWord.isDisplayed());
		System.out.println("Time End: " + getCurrentTime());
	}
	
	//@Test
	public void TC_04_Explicit_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Click Strat button
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy)); //nhan timeout of Explicit
		driver.findElement(startButtonBy).click();
		
		//Loading icon bien mat sau 5s
		System.out.println("Time Strat Invisiable: " + getCurrentTime());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImageBy));
		System.out.println("Time End Invisiable: " + getCurrentTime());
		
		//Wait cho helloworld duoc hien thi
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(helloworldTextBy));
		
		System.out.println("Time Strat Visible: " + getCurrentTime());
		WebElement helloWord = driver.findElement(helloworldTextBy);
		Assert.assertTrue(helloWord.isDisplayed());
		System.out.println("Time End Visible: " + getCurrentTime());		
		
	}
	
	//@Test
	public void TC_05_Date_Implicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//In ra date is selected
		WebElement dateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		System.out.println("dateSelected: " + dateSelected.getText());
		
		//select date = 7
		driver.findElement(By.xpath("//a[text() = '7']")).click();
		
		//Check date = 7 is selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text() = '7']")).isDisplayed());
		System.out.println("dateSelected: " + dateSelected.getText());
		Assert.assertEquals(dateSelected.getText(), "Tuesday, January 07, 2020");

	}
	
	@Test
	public void TC_05_Date_Explicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//In ra date is selected
		WebElement dateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		System.out.println("dateSelected: " + dateSelected.getText());
		
		//select date = 7
		driver.findElement(By.xpath("//a[text() = '7']")).click();
		
		//Wait cho loading icon load xong
		By loadingImage = By.xpath("//div[@class = 'raDiv']");
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));
		
		
		//Check date = 7 is selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text() = '7']")).isDisplayed());
		dateSelected = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		System.out.println("dateSelected: " + dateSelected.getText());
		Assert.assertEquals(dateSelected.getText(), "Tuesday, January 07, 2020");

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