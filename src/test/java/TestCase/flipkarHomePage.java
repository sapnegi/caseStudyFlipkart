package TestCase;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonBrowser.BrowserClass;

public class flipkarHomePage extends BrowserClass{
	
	WebDriverWait wait;

	
	@Test(groups= {"Sanity"}, priority = 1)
	public void openURL() throws InterruptedException {	
		openBrowser("chrome");
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");		
	}
	
	@Test(groups= {"Sanity"}, priority = 2)
	public void closeModalBox() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2AkmmA _29YdH8']"))).click();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	}
	
	@Test(priority = 3,groups= {"Sanity"})
	public void HoverElectronics() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[@class='_1QZ6fC _3Lgyp8' and contains(text(),'Electronics')]"))).perform();
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Mi' and contains(@href,'mobiles/mi')]"))).click();
		//driver.findElement(By.xpath("//a[text()='Mi' and contains(@href,'mobiles/mi')]")).click();

	}
	
	@Test(priority = 4,groups= {"Sanity"})
	public void verifyMILabel() throws InterruptedException {

		wait = new WebDriverWait(driver, 20);
		Boolean labelTest = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='oAWPz2']//p[contains(text(),'Latest')]"), "Latest from MI"));
		Assert.assertTrue(labelTest);	
	}
	
	@Test(priority = 5,groups="Regression")
	public void ChangePriceSlider() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("_3aQU3C"))).click();		
	}
	
	@Test(priority = 6,groups="Regression")
	public void SelectPriceDropDown() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//div[@class='_1YoBfV']//select[@class='fPjUPw']"));
		Select s = new Select(ele);
		s.selectByIndex(2);		
	}
	
	@Test(priority = 7,groups="Regression")
	public void SearchRedmi() throws InterruptedException {
		WebElement searchTextBox = driver.findElement(By.className("LM6RPg")); 
		searchTextBox.sendKeys("redmi go (black, 8 gb)");	
		searchTextBox.sendKeys(Keys.ENTER);
		
	}
		
	@Test(priority = 8,groups="Regression")
	public void productClick() throws InterruptedException {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_3wU53n"))).click();
	}
	
	@Test(priority = 9,groups="Regression")
	public void verifyProductAmount() throws InterruptedException {
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> itr = ids.iterator();
		String parentWindow = itr.next();
		String childWindow = itr.next();
		driver.switchTo().window(childWindow);
		String amount = driver.findElement(By.xpath("//div[contains(@class,'3qQ9m1')]")).getText();
		amount = amount.substring(1).replace(",", "");
			
		if(Integer.parseInt(amount)>0)
		Assert.assertTrue(true);		
	}
	
	@Test(priority = 10,groups="Regression")
	public void playVideo() throws InterruptedException {
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("_3wR-Kp"))).click();

	}
	
	@Test(priority = 11,groups="Regression")
	public void enterPindcode() throws InterruptedException {
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.className("_3X4tVa"))).click().sendKeys("56001").build().perform();		
	}
	
	@Test(priority = 12,groups= {"Regression","Sanity"})
	public void quitBrowser() {
		driver.quit();
	}
}
