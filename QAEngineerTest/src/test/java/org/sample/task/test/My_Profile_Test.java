package org.sample.task.test;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.io.Files;
import com.sample.task.base.TestBase;
import com.sample.task.pages.LoginPage;

public class My_Profile_Test extends TestBase {
	Login_Page_Test loginpagetest;

	public My_Profile_Test() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void setup() throws IOException {
		intialization();

	}

	@Test

	public void Profile_test() throws Exception {
		LoginPage login = new LoginPage();
		login.loginToWordPress(p.getProperty("username"), p.getProperty("password"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/header/h1")));
		String Title = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/header/h1")).getText();
		System.out.println(Title);
		SoftAssert SoftAssert = new SoftAssert();
		SoftAssert.assertEquals(Title, "My Profile",
				"'Verify My Profile Text in Profile Page'-First soft assert failed");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Enter First Name
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/fieldset[1]/input"))
				.sendKeys("");
		;
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/fieldset[1]/input"))
				.sendKeys("Test");
		// Enter Last Name
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/fieldset[2]/input"))
				.sendKeys("Test");
		// Enter Public Display Name
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/fieldset[3]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/fieldset[3]/input"))
				.sendKeys("Test");
		// Enter About Me
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/fieldset[4]/textarea"))
				.sendKeys("This is About me");
		// Click on 'Toggle' button
		driver.findElement(By.id("inspector-toggle-control-0")).click();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,200)");
		// Click on 'Save Profile details' Button
		// driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/p/button")).click();
		// Upload Profile Picture
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/div/div[1]/span/div/div[2]/span"))
				.click();
		Robot robot = new Robot();
		robot.setAutoDelay(4000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		StringSelection stringselection = new StringSelection("c:\\TestProfileImage.PNG");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.focus();");
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.setAutoDelay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/figure/div/div[3]/button[3]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String FileUploadSuccessMessage = driver
				.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/div/span[2]/span")).getText();
		System.out.println(FileUploadSuccessMessage);
		SoftAssert.assertEquals(FileUploadSuccessMessage,
				"You successfully uploaded a new profile photo — looking sharp!",
				"'Verifing File upload Success Message'-Second soft assert failed");
		captureScreen();
		SoftAssert.assertAll();
	}

	private static void captureScreen() throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile, new File("CaptureScreen.jpg"));
		return;

	}

}