package com.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
//import org.testng.AssertJUnit;
import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;

import base.BaseTest;
import utils.AppConstants;

public class TS_02_Verify_Opencart_LoginPageTast extends BaseTest {
	
	@BeforeMethod
	public void setUp() {
		loginPage = homePage.navigateToLoginPage();
		String loginPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE,"The Title of the login page should match the expected title ");
	}

	@Test 
	public void loginPageTitleTest() {
	String actTitle = loginPage.getLoginPageTitle();
	Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test 
	public void navigateToRegisterPageTest() {
	loginPage.navigateToRegisterPage();
	String registerPageTitle = registerPage.getRegisterPageTitle();
	Assert.assertEquals(registerPageTitle, AppConstants.REGISTER_PAGE_TITLE,"The Title of the register page should match the expected title ");
	registerPage.clickHomeIcon();
	}
	
	@Test 
	public void navigateToForgotPasswordPageTest() {
	loginPage.navigateToForgotPasswordPage();
	String forgetpwdPageTitle = forgotPasswordPage.getForgotPasswordPageTitle();
	Assert.assertEquals(forgetpwdPageTitle, AppConstants.FORGOT_PASSWORD_PAGE_TITLE,"The Title of the forgotpassword page should match the expected title ");
	registerPage.clickHomeIcon();
	}
	
	@Test
	public void verifyNewCustomerHeaderExists() {
		boolean isNewCustomerHeaderExists = loginPage.newCustomerHeaderExists();
		Assert.assertTrue(isNewCustomerHeaderExists, "The New Customer header should be visile on the login page");		
	}
	
	@Test
	public void verifyreturningCustomerHeaderExists() {
		boolean isreturningCustomerHeaderExists = loginPage.returningCustomerHeaderExists();
		Assert.assertTrue(isreturningCustomerHeaderExists, "The returning Customer header should be visile on the login page");	
	}
	
	@Test
	public void verifyLoginBreadCrumbExists() {
		boolean loginbreadCrumbExists = loginPage.loginBreadCrumbExist();
		Assert.assertTrue(loginbreadCrumbExists, "The Login breadcrumb should be visile on the login page");	
	}
	
	@Test
	public void loginTest() {
		boolean isLoginSuccess = loginPage.doLogin(testproperties.getProperty("username"), testproperties.getProperty("password"));
		if(isLoginSuccess) {
		    Assert.assertTrue(isLoginSuccess, "The login should success for valid credentials");	
		}else {
			Assert.assertFalse(isLoginSuccess, "The login should fail for invalid credentials");	
		}
	}
	
	

}
