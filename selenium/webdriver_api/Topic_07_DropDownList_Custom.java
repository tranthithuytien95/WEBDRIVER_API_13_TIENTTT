package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_DropDownList_Custom {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;
	Actions action;
	By numberAllItems = By.xpath("//ul[@id='number-menu']//li");
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver,5);
		javascript = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Jquery() throws InterruptedException {
		//span[@id='number-button']
		//ul[@id='number-menu']//li
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//click vao item 19
		SelectItemOnCustomDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//li", "19");
		Thread.sleep(5000);
		
		//Ktra item 19 da duoc chon
		Assert.assertTrue(isDisplayed("//span[@id='number-button']/span[@class = 'ui-selectmenu-text' and text()='19']"));	
	}
	
	//@Test
	public void TC_02_Angular() throws InterruptedException {

		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		//click vao item Footbal
		SelectItemOnCustomDropdownList("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Football");
		Thread.sleep(5000);
		
		//Ktra item Footbal da duoc chon
		String expectedValue = gettextByJS("#games_hidden > option");
		Assert.assertEquals(expectedValue, "Football");
		
		
	}
		
	//@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		//Select Christian
		SelectItemOnCustomDropdownList("//div[@class = 'ui fluid selection dropdown']","//div[@class = 'visible menu transition']//div[@role = 'option']","Christian");
		//Ktra item Christian da duoc chon
		Assert.assertTrue(isDisplayed("//div[@class = 'text' and text() = 'Christian']"));	
	}
	
	@Test
	public void TC_04_Editabled() throws InterruptedException {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		InputvalueItemOnCustomDropdownList("//div[@class = 'ui fluid search selection dropdown']","//input[@class = 'search']","ame");
		Thread.sleep(5000);
		Assert.assertTrue(isDisplayed("//div[@class = 'text' and text() = 'American Samoa']"));	
		
	}
	
	
	public void InputvalueItemOnCustomDropdownList(String parentXpath, String inputXpath, String expected) {
		driver.findElement(By.xpath(parentXpath)).click();
		driver.findElement(By.xpath(inputXpath)).sendKeys(expected);
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();	
	}
	
	public void SelectItemOnCustomDropdownList(String parentXpath, String allTimeXpath, String expected) {
		//click vao item chua dropdown list
		driver.findElement(By.xpath(parentXpath)).click();
		
		//khai bao mang chua all item
		List <WebElement> allItems = driver.findElements(By.xpath(allTimeXpath));
		
		//wait cho all items xuat hien
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allTimeXpath)));
		
		//duyet qua tat ca phan tu den khi tim dc text can chon
		for (WebElement item : allItems) {
			System.out.println("Text = " + item.getText());
			
			//ktra neu co phan tu duoc chon thi click
			if (item.getText().equals(expected)){
				item.click();
				break;
			}
		}
	}
	
	@Test
	public boolean isDisplayed(String xpathlocator) {
		WebElement element = driver.findElement(By.xpath(xpathlocator));
		if (element.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}

	public String gettextByJS(String locator) {
		return (String) javascript.executeScript("return document.querySelector('" + locator + "').text");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}