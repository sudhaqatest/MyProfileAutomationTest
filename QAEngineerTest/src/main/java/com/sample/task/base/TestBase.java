package com.sample.task.base;

//This is base class

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	public static WebDriver driver;
	public static Properties p;

	public TestBase() {
		try {

			p = new Properties();
			FileInputStream ip = new FileInputStream("config.properties");
			p.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void intialization() throws MalformedURLException {
		String BrowserName = p.getProperty("browser");
		String url = p.getProperty("url");
		if (BrowserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("Webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C://geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (BrowserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "C://EdgeDriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
	}
}
