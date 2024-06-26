package com.testcases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import base.BaseTest;
import utils.AppConstants;

public class TS_03_Verify_Opencart_RegisterPageTest extends BaseTest {
	
	@BeforeMethod
	public void setUp() {
		registerPage = homePage.navigateToRegisterPage();
		String registerPageTitle = registerPage.getRegisterPageTitle();
		Assert.assertEquals(registerPageTitle, AppConstants.REGISTER_PAGE_TITLE,"The Title of the register page should match the expected title ");
	}
	@Test
	public void registerNewAccountTest() {
		//create object for Faker class
		Faker faker = new Faker();
		//get fields from register page and generate the dynamic data at runtime
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email = faker.internet().emailAddress();
		String telephone = faker.phoneNumber().phoneNumber();
		String password = faker.internet().password();
		
		//call our methods to fill(set) our personal details
		registerPage.setPersonalDetails(firstName, lastName, email, telephone);
		registerPage.setPasswordDetails(password, password);
		registerPage.selectSubScribe("Yes");
		registerPage.checkAgreeCheckBox();
		registerPage.clickContinueBtn();
		//Assert.assertTrue(registerPage.isAccountCreatedSucMsgExists(),"Account creation sccess msg should be displayed");
		//Assert.assertEquals(registerPage.isAccountCreatedSucMsgExists(),AppConstants.ACCOUNT_CREATION_SUCCESS_MESSAGE,"Account creation sccess msg should be displayed");
		//Assert.assertTrue(registerPage.isAccountCreatedBreadCrumbExists(),"Account creation Bread Crumb should be displayed");
		registerPage.clickOnaccountCreatedContinueBtn();


	}


		
	

}
