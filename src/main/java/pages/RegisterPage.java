package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

public class RegisterPage {
	private Page page;
	private ExtentTest extentTest;
	private Logger log = LogManager.getLogger(RegisterPage.class);

	public RegisterPage(Page page, ExtentTest extentTest) {
		this.page = page;
		this.extentTest = extentTest;
	}

	private String registerAccountHeader = "#count h1";
	private String loginPageLink = "a:text('login page')";
	private String firstNameEditBox = "id=input-firstname";
	private String lastNameEditBox = "id=input-lastname";
	private String emailEditBox = "#input-email";
	private String telephoneEditBox = "#input-telephone";
	private String passwordEditBox = "#input-password";
	private String confirmPasswordEditBox = "#input-confirm";
	private String accountCreatedHeader = "div#content h1";
	private String accountCreatedSucMsg = "div#content p";
	//private String accountCreatedSucMsg = "//*[@id=\"content\"]/p[1]";
	private String accountCreatedContinueBtn = "a.btn.btn-primary";
	private String accountCreatedBreadCrumbSuccess = "#common-success > ul > li:nth-child(3)";
	private String accountCreatedcontactUsLink = "a.text('contact us')";
	private String subScribeYesRadioBtn = "//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input";
	private String subScribeNoRadioBtn  = "//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input";
	private String privacyheckBox = "input[name='agree']";
	private String continueBtn = "input[value='Continue']";
	//private String continueBtn = "//*[@id='content']/form/div/div/input[2]";
	private String registerHomeIcon = "//*[@class = 'fa fa-home']";
	
	public void clickHomeIcon() {
		page.locator(registerHomeIcon).click();
	}
	
	public String getRegisterPageTitle() {
		page.waitForLoadState();
		return page.title();
	}

	public String getAccountCreatedHeader() {
		return page.locator(accountCreatedHeader).textContent().trim();
	}

	public String getaccountCreatedSucMsg() {
		return page.locator(accountCreatedSucMsg).first().textContent().trim();
		//return page.locator(accountCreatedSucMsg).textContent().trim();
	}
	public boolean isAccountCreatedSucMsgExists() {
		return page.locator(accountCreatedSucMsg).first().isVisible();
	}
	public boolean isAccountCreatedBreadCrumbExists() {
		return page.locator(accountCreatedBreadCrumbSuccess).isVisible();
	}
	public void clickOnContactUsLink() {
		 page.locator(accountCreatedcontactUsLink).click();;
	}
	public void clickOnaccountCreatedContinueBtn() {
		 page.locator(accountCreatedContinueBtn).click();;
	}

	public String getTelephoneEditBox() {
		return telephoneEditBox;
	}

	public void setTelephoneEditBox(String telephone) {
		page.locator(telephoneEditBox).fill(telephone);
	}

	public String getLoginPageLink() {
		return loginPageLink;
	}

	public void setLoginPageLink(String loginPageLink) {
		this.loginPageLink = loginPageLink;
	}

	public String getFirstNameEditBox() {
		return firstNameEditBox;
	}

	public void setFirstNameEditBox(String firstName) {
		page.locator(firstNameEditBox).fill(firstName);
	}

	public String getLastNameEditBox() {
		return lastNameEditBox;
	}

	public void setLastNameEditBox(String lastName) {
		page.locator(lastNameEditBox).fill(lastName);
	}

	public String getEmailEditBox() {
		return emailEditBox;
	}

	public void setEmailEditBox(String email) {
		page.locator(emailEditBox).fill(email);
	}

	public String getPasswordEditBox() {
		return passwordEditBox;
	}

	public void setPasswordEditBox(String password) {
	    page.locator(passwordEditBox).fill(password);
	}

	public String getConfirmPasswordEditBox() {
		return confirmPasswordEditBox;
	}

	public void setConfirmPasswordEditBox(String password) {
		page.locator(confirmPasswordEditBox).fill(password);
	}

	public void setPersonalDetails(String fname, String lname, String email, String telephone) {
		setFirstNameEditBox(fname);
		setLastNameEditBox(lname);
		setEmailEditBox(email);
		setTelephoneEditBox(telephone);
	}

	public void setPasswordDetails(String pwd, String confirmPwd) {
		setPasswordEditBox(pwd);
		setConfirmPasswordEditBox(confirmPwd);

	}

	public void selectSubScribe(String subscribe) {
		log.info("select newsletter subscription value");
		if (subscribe.equalsIgnoreCase("Yes")) {
			log.info("select newsletter subscription value - Yes ");
			page.locator(subScribeYesRadioBtn).click();
		} else {
			log.info("select newsletter subscription value - No ");
			page.click(subScribeNoRadioBtn);
		}
	}
	
	public void checkAgreeCheckBox() {
		log.info("check the check box");	
		page.locator(privacyheckBox).check();
		
	}
	
	public void clickContinueBtn() {
		log.info("click on continue button");	
		//page.locator(continueBtn).check();
		page.locator(continueBtn).click();;
	}
	
	

}
