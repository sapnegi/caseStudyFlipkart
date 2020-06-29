package commonBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserClass 
{
	public static WebDriver driver;
	public static void openBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "E:\\Software\\Selenium\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "E:\\Software\\Selenium\\Drivers\\geckodriver.exe");
			driver = new ChromeDriver();
		}
	}
}
