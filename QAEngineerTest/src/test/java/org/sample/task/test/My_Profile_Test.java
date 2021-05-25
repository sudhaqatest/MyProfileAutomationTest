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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("H1")));
		String Title = driver.findElement(By.tagName("H1")).getText();
		System.out.println(Title);
		SoftAssert SoftAssert = new SoftAssert();
		SoftAssert.assertEquals(Title, "My Profile",
				"'Verify My Profile Text in Profile Page'-First soft assert failed");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Enter First Name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name,'first_name')]")));
		driver.findElement(By.xpath("//input[contains(@name,'first_name')]")).clear();
		driver.findElement(By.xpath("//input[contains(@name,'first_name')]")).sendKeys("Testfirstname");
		// Enter Last Name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'last_name')]")));
		driver.findElement(By.xpath("//input[contains(@id,'last_name')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'last_name')]")).sendKeys("Testlastname");
		// Enter Public Display Name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'display_name')]")));
		driver.findElement(By.xpath("//input[contains(@id,'display_name')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'display_name')]")).sendKeys("Testdisplayname");
		// Enter About Me
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@id,'description')]")));
		driver.findElement(By.xpath("//textarea[contains(@id,'description')]")).clear();
		driver.findElement(By.xpath("//textarea[contains(@id,'description')]")).sendKeys("Testdisplayname");
		// Click on 'Toggle' button
		driver.findElement(By.xpath("//input[contains(@id,'inspector-toggle-control-0')]")).click();
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,200)");
		// Click on 'Save Profile details' Button
		// driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/main/div[2]/form/p/button")).click();
		// Upload Profile Picture
		driver.findElement(
				By.xpath("//div[@class='edit-gravatar__label-container']//span[@class='edit-gravatar__label']"))
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
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div/figure/div/div[1]/div/div[5]")));
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/figure/div/div[3]/button[3]")).click();
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

