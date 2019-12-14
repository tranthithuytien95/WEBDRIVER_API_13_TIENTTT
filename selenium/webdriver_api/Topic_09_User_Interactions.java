package webdriver_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_09_User_Interactions {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		
		// Run by Chrome
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/libraries/chromedriver");
		driver = new ChromeDriver();
		
		//close notification of firefox
		//FirefoxProfile profile = new FirefoxProfile();
		//profile.setPreference("dom.webnotifications.enabled", false);
		
		//driver = new FirefoxDriver(profile);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_moveMouse() {
		driver.get("http://www.myntra.com/");
		
		//hover on Discover
		action.moveToElement(driver.findElement(By.xpath("//a[@class = 'desktop-main' and text() = 'Discover']"))).perform();

		//Click on American Eagle
		driver.findElement(By.xpath("//a[@class = 'desktop-categoryLink' and text() = 'American Eagle']")).click();
		
		//Verify page is displayed
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "American Eagle");
		
		String title = driver.findElement(By.xpath("//h1[@class='title-title']")).getText();
		System.out.println(title);
		String count = driver.findElement(By.xpath("//h1[@class='title-title']//following-sibling::span[@class = 'title-count']")).getText();
		System.out.println(count);
		String text = ""+ title +" "+ count +"";
		System.out.println(text);
		Assert.assertEquals(text, "American Eagle "+ count +"");
	}

	//@Test
	public void TC_02_ClickandHold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//Click and hold tu 1->4
		List<WebElement> number = driver.findElements(By.xpath("//ol[@id ='selectable']//li"));
		int numberSize = number.size();
		System.out.println("NumberSize before click and hold = " + numberSize); //12
		
		action.clickAndHold(number.get(0)).moveToElement(number.get(3)).release().perform(); //release: tha chuot ra
		
		//verify 4 items is select
		List<WebElement> numberselect = driver.findElements(By.xpath("//ol[@id ='selectable']//li[@class = 'ui-state-default ui-selectee ui-selected']"));
		int numberselectSize = numberselect.size();
		System.out.println("NumberSize after click and hold = " + numberselectSize); //4
		
		for (WebElement i:numberselect) {
			System.out.println("text = " + i.getText());
		}
		Assert.assertEquals(numberselectSize, 4);
	}

	//@Test
	public void TC_03_ClickandSelectItemRandom() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> number = driver.findElements(By.xpath("//ol[@id ='selectable']//li"));
		
		action.keyDown(number.get(0), Keys.CONTROL).perform();
		
		action.click(number.get(0))
		.click(number.get(2))
		.click(number.get(5))
		.click(number.get(10))
		.perform();
		Thread.sleep(3000);
		
		action.keyUp(Keys.CONTROL).perform();
		
		//verify 5 items is select
		List<WebElement> numberselect = driver.findElements(By.xpath("//ol[@id ='selectable']//li[@class = 'ui-state-default ui-selectee ui-selected']"));
		int numberselectSize = numberselect.size();
		System.out.println("NumberSize after click and hold = " + numberselectSize); //5
		
		for (WebElement i:numberselect) {
			System.out.println("text = " + i.getText());
		}
		Assert.assertEquals(numberselectSize, 3);
	}
	
	//@Test
	public void TC_04_DoubleClick() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text() = 'Double click me']"))).perform();;
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}
	//@Test
	public void TC_05_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//click right
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
		
		//Hover on quit
		action.clickAndHold(driver.findElement(By.xpath("//span[text() = 'Quit']"))).perform();
		
		//verify Quit is hovered
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-hover') and contains(@class, 'context-menu-visible')]//span[text() = 'Quit']")).isDisplayed());
		
		//Click chon Quit
		action.click(driver.findElement(By.xpath("//span[text() = 'Quit']"))).perform();
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
	}

	@Test
	public void TC_06_DrapandDrop() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		action.dragAndDrop(source, target).perform();
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text() = 'You did great!']")).isDisplayed());
	}

	//@Test
	public void TC_06_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
	
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}