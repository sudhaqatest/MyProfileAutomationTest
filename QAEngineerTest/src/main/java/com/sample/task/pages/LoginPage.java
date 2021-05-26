package com.sample.task.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import com.sample.task.base.TestBase;

public class LoginPage extends TestBase {

	By username = By.xpath("//input[contains(@name,'usernameOrEmail')]");
	By Continue = By.xpath("//div[@class='login']//button[@type='submit']");
	By password = By.xpath("//input[contains(@id,'password')]");
	By Login = By.xpath("//div[@class='login']//button[@type='submit']");

	public LoginPage() {

		PageFactory.initElements(driver, this);
	}

	public void loginToWordPress(String uid, String pass) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.findElement(username).sendKeys(p.getProperty("username"));
		driver.findElement(username).sendKeys(uid);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(Continue).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(password).sendKeys(pass);
//		driver.findElement(password).sendKeys(p.getProperty("password"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(Login).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		

	}

}
