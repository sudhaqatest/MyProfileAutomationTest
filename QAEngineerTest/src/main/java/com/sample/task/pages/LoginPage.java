package com.sample.task.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sample.task.base.TestBase;

public class LoginPage extends TestBase {
	

	
	
	By username=By.xpath("/html/body/div/div/div[1]/div[1]/div/main/div/div/form/div[1]/div[1]/input");
	By Continue =By.xpath("/html/body/div/div/div/div[1]/div/main/div/div/form/div[1]/div[2]/button");
	
	By password=By.id("password");
	By Login=By.xpath("/html/body/div/div/div[1]/div[1]/div/main/div/div/form/div[1]/div[2]/button");
	
	public LoginPage() { 
		
		PageFactory.initElements(driver, this);
	}
	
	
	public  My_Profile_Page loginToWordPress(String uid, String pass) 
	{
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
		return new My_Profile_Page();
	
		
	}
	

}



