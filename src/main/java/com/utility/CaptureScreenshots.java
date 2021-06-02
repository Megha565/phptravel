package com.utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CaptureScreenshots {

	public static void getSignupScreenshot(WebDriver driver) {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/screenshot/signup" + Math.random() + ".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void getLoginScreenshot(WebDriver driver) {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/screenshot/login" + Math.random() + ".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	

}
