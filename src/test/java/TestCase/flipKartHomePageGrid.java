package TestCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class flipKartHomePageGrid {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Parameters("System")
	@Test(priority = 1)
	public void openURL(String device) throws InterruptedException, MalformedURLException {
		
		if (device.equalsIgnoreCase("pc1")) {	
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL("http://192.168.0.106:5757/wd/hub"), cap);

		} else if (device.equalsIgnoreCase("pc2")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			driver = new RemoteWebDriver(new URL("http://192.168.0.106:5656/wd/hub"), cap);
		}
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");		
	}
	
	@Test(priority = 2)
	public void closeModalBox() {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2AkmmA _29YdH8']"))).click();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	}
	
	@Test(priority = 3)
	public void HoverElectronics() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[@class='_1QZ6fC _3Lgyp8' and contains(text(),'Electronics')]"))).perform();
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Mi' and contains(@href,'mobiles/mi')]"))).click();
		//driver.findElement(By.xpath("//a[text()='Mi' and contains(@href,'mobiles/mi')]")).click();

	}
	
	@Test(priority = 4)
	public void verifyMILabel() throws InterruptedException {

		wait = new WebDriverWait(driver, 20);
		Boolean labelTest = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='oAWPz2']//p[contains(text(),'Latest')]"), "Latest from MI"));
		Assert.assertTrue(labelTest);
		driver.close();
	}

}
