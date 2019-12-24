package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Windows {
	WebDriver driver;
	JavascriptExecutor javascriptexecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascriptexecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Window() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//get ID of tab/window dang dung
		String parentID = driver.getWindowHandle();
		System.out.println("parentID = " + parentID);
		
		driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
		
		//get ID of all tab/window
		//Set<String> allIDs = driver.getWindowHandles();
		//for (String id: allIDs) {
			//System.out.println("ID = " + id);
		//}
		
		switchtowindowbyID(parentID);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
		switchtowindowbyTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[text() = 'TIKI']")).click();
		switchtowindowbyTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		closeAllWindowWithoutParentWindow(parentID);
		driver.switchTo().window(parentID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getWindowHandle(), parentID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_02_Window() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
		List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		if(fancyPopup.size() > 0) {
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.xpath("//a[@title='Close']")).click();
		}
		
		clickToElementByJS("//img[@alt = 'facebook']");
		switchtowindowbyTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		driver.switchTo().window(parentID);
		
		clickToElementByJS("//img[@alt = 'youtube']");
		switchtowindowbyTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		driver.switchTo().window(parentID);
		
		clickToElementByJS("//img[@alt = 'zalo']");
		switchtowindowbyTitle("Kyna.vn");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");
		driver.switchTo().window(parentID);
		
		clickToElementByJS("//img[@alt = 'apple-app-icon']");
		switchtowindowbyTitle("‎KYNA on the App Store");
		Assert.assertEquals(driver.getTitle(), "‎KYNA on the App Store");
		driver.switchTo().window(parentID);
		
		clickToElementByJS("//img[@alt = 'android-app-icon']");
		switchtowindowbyTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
		Assert.assertEquals(driver.getTitle(), "KYNA - Học online cùng chuyên gia - Apps on Google Play");
		driver.switchTo().window(parentID);
		
		clickToElementByJS("//img[@alt = 'kynaforkids.vn']");
		switchtowindowbyTitle("Kynaforkids.vn trường học trực tuyến cho trẻ");
		Assert.assertEquals(driver.getTitle(), "Kynaforkids.vn trường học trực tuyến cho trẻ");
		driver.switchTo().window(parentID);
		
		clickToElementByJS("//img[@alt = 'kynabiz.vn']");
		switchtowindowbyTitle("Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
		Assert.assertEquals(driver.getTitle(), "Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
		driver.switchTo().window(parentID);
		
		switchtowindowbyTitle(parentID); 
	}
	
	@Test
	public void TC_03_Window() {
		driver.get("http://live.guru99.com/index.php/");
		
		driver.findElement(By.xpath("//a[text() = 'Mobile']")).click();
		
		driver.findElement(By.xpath("//a[@title = 'Sony Xperia']//parent::h2//following-sibling::div[@class = 'actions']//a[text() = 'Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'success-msg']//span[text() = 'The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[@title = 'Samsung Galaxy']//parent::h2//following-sibling::div[@class = 'actions']//a[text() = 'Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'success-msg']//span[text() = 'The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		
		driver.findElement(By.xpath("//span[text() = 'Compare']")).click();
		
		switchtowindowbyTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt = 'Sony Xperia']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt = 'Samsung Galaxy']")).isDisplayed());
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		driver.findElement(By.xpath("//span[text() = 'Close Window']")).click();
		
		switchtowindowbyTitle("Mobile");
		driver.findElement(By.xpath("//a[text() = 'Clear All']")).click();

		driver.switchTo().alert().accept();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'success-msg']//span[text() = 'The comparison list was cleared.']")).isDisplayed());
	}
	
	public void switchtowindowbyID (String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	public void switchtowindowbyTitle (String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String  currentWidow = driver.getTitle();
			if (currentWidow.equals(title)) {
				break;
			}
		}
	}
	public boolean closeAllWindowWithoutParentWindow (String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		if(driver.getWindowHandles().size() == 1) {
			return true;
		}
		else{
			return false;
		}
	}
	public void clickToElementByJS(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		javascriptexecutor.executeScript("arguments[0].click();", element);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}