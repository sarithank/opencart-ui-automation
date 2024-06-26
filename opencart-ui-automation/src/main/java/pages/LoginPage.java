package pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

import static base.PlaywrightFactory.takeScreenshot;
import static utils.ExtentReporter.extentLog;
import static utils.ExtentReporter.extentLogWithScreenshot;
import org.apache.logging.log4j.Logger;

import java.util.Base64;

import org.apache.logging.log4j.LogManager;

public class LoginPage {

	private Page page;
	private ExtentTest extentTest;
	private Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(Page page, ExtentTest extentTest) {
		this.page = page;
		this.extentTest = extentTest;
	}

	private String emailEditBox = "input#input-email";
	private String passwordEditBox = "input#input-password";
	private String loginBtn = "//input[@value='Login']";
	private String newCustomerHeader = "//h2[normalize-space()='New Customer']";
	private String logOutLink = "//a[normalize-space()='Logout']";
	private String newCustomerContinueBtn = "//a[@class='btn btn-primary']";
	private String returningCustomerHeader = "//h2[normalize-space()='Returning Customer']";
	private String forgotPasswordLink = "a:text('Forgotten Password')";
	private String myAccountMenu = "//span[contains(.,'My Account')]";
	private String errorMsg = "//div[contains@class,'alert alert-danger']";
	//private String loginBreadCrumb = "//ul[@class='breadcrumb']//a:text('Login')";////*[@id="account-login"]/ul/li[3]/a
	private String loginBreadCrumb = "//*[@id=\"account-login\"]/ul/li[3]/a";
	private String loginHomeIcon = "//*[@class='fa fa-home']";

	public void clickLoginHomeIcon() {
		log.info("click on login home icon in breadcrumb");
		page.locator(loginHomeIcon).click();

	}

	/**
	 * this method fetches the current page title
	 */
	public String getLoginPageTitle() {
		page.waitForLoadState();
		return page.title();
	}

	public void navigateToRegisterPage() {
		log.info("click on continue button in ligin page under newcustomer section");
		page.locator(newCustomerContinueBtn).click();
	}

	public void navigateToForgotPasswordPage() {
		log.info("click on forget Passwrd button in ligin page ");
		page.locator(forgotPasswordLink).first().click();
	}

	private boolean isLogoutLinkVisible() {
		page.click(myAccountMenu);
		return page.locator(logOutLink).first().isVisible();
	}

	public boolean doLogin(String username, String password) {
		extentLog(extentTest, Status.INFO,
				"Login to the application using username" + username + "and password" + password);
		page.fill(emailEditBox, username);
		page.fill(passwordEditBox, new String(Base64.getDecoder().decode(password)));
		page.click(loginBtn);
		if (isLogoutLinkVisible()) {
			extentLog(extentTest, Status.PASS, "Login to the application is Successfull");
			return true;
		}
		boolean isErrorMsgDisplayed = page.textContent(errorMsg)
				.contains(" Warning: No match for E-Mail Address and/or Password.");
		extentLogWithScreenshot(extentTest, Status.FAIL, "User login to the application", takeScreenshot(page));
		return !isErrorMsgDisplayed;
	}

	public boolean newCustomerHeaderExists() {
		return page.locator(newCustomerHeader).isVisible();
	}

	public boolean returningCustomerHeaderExists() {
		return page.locator(returningCustomerHeader).isVisible();
	}

	public boolean loginBreadCrumbExist() {
		return page.locator(loginBreadCrumb).isVisible();
	}

}
