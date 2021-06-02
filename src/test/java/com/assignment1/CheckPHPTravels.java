package com.assignment1;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utility.CaptureScreenshots;
import com.utility.FakeUserDetails;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckPHPTravels {
	WebDriver driver;
	HashMap<String, String> userdata = new HashMap<String, String>();

	@BeforeClass
	@Parameters({ "browser", "url" })
	public void setup(@Optional("chrome") String browser, @Optional("https://www.phptravels.net/") String url) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

	}

	@Test
	public void signupUser() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue(driver.getTitle().contains("Partner"));
		driver.findElement(By.xpath("//i[@class='bx bx-user']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		Assert.assertTrue(driver.getTitle().contains("Register"));
		userdata = FakeUserDetails.getuserDetails();
		driver.findElement(By.name("firstname")).sendKeys(userdata.get("firstname"));
		driver.findElement(By.name("lastname")).sendKeys(userdata.get("lastname"));
		driver.findElement(By.name("phone")).sendKeys(userdata.get("phone"));
		driver.findElement(By.name("email")).sendKeys(userdata.get("email"));
		driver.findElement(By.name("password")).sendKeys(userdata.get("password"));
		driver.findElement(By.name("confirmpassword")).sendKeys(userdata.get("confirmpassword"));
		CaptureScreenshots.getSignupScreenshot(driver);
	}

	@Test(dependsOnMethods = "signupUser")
	public void loginUser() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//i[@class='bx bx-user']")).click();
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userdata.get("email"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(userdata.get("password"));
		//driver.findElement( By.id("remember-me")).click();
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(5000);
		CaptureScreenshots.getLoginScreenshot(driver);
		Assert.assertTrue(driver.getTitle().contains("Login"));
		System.out.println(userdata);
	}
	
	//@Test(dependsOnMethods = "loginUser")
	public void flightSearch() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(text(),' Flights ')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='select2-drop']/div/child::input")).sendKeys("BLR");
		
		List<WebElement> element = driver.findElements(By.xpath("//ul[@class='select2-results']//child::li//child::div"));
		for(WebElement elt : element) {
			System.out.println("***"+elt.getText());
		}
		
	}

	@AfterClass
	public void quit() {
		driver.close();
	}
}
