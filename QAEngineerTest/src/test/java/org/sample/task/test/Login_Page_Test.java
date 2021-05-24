package org.sample.task.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.sample.task.base.TestBase;
import com.sample.task.pages.LoginPage;


public class Login_Page_Test extends TestBase {

	LoginPage loginpage;

	public Login_Page_Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void setup() throws IOException {
		intialization();
		loginpage = new LoginPage();
	}

	@Test
	public void Login_Test() throws IOException {
		loginpage.loginToWordPress(p.getProperty("username"), p.getProperty("password"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.quit();
	}

}
