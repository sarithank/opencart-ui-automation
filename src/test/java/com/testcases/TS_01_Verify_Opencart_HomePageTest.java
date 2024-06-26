package com.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;

import base.BaseTest;
import utils.AppConstants;

public class TS_01_Verify_Opencart_HomePageTest extends BaseTest{
	
	@Test 
	public void homePageTitleTest() {
	String actTitle = homePage.getHomePageTitle();
	Assert.assertEquals(actTitle, AppConstants.HOME_PAGE_TITLE);
	
	}
	@Test 
	public void homePageURLTest() {
	String actUrl = homePage.getHomePageURL();
	Assert.assertEquals(actUrl, testproperties.getProperty("url"));
	
	}
	@Test 
	public void verifyOpenCartLogoTest() {
	boolean isLogoExist = homePage.isOpenCartLogoExist();
	Assert.assertTrue(isLogoExist);
	
	}
	@Test 
	public void FeaturedSectionCardaCountTest() {
	int actCardCount = homePage.getFeaturedSectionCardaCount();
	Assert.assertEquals(actCardCount, AppConstants.FEATURED_SECTION_CARD_COUNT);
	
	}
	@Test 
	public void navigateToLoginPageTest() {
	loginPage = homePage.navigateToLoginPage();
	String loginPageTitle = loginPage.getLoginPageTitle();
	Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE,"The Title of the login page should match the expected title ");
	loginPage.clickLoginHomeIcon();
	}
	
	@Test 
	public void navigateToRegisterPageTest() {
	registerPage = homePage.navigateToRegisterPage();
	String registerPageTitle = registerPage.getRegisterPageTitle();
	Assert.assertEquals(registerPageTitle, AppConstants.REGISTER_PAGE_TITLE,"The Title of the register page should match the expected title ");
	registerPage.clickHomeIcon();
	}
	
	@DataProvider
	public Object[][]productData(){
	Object[][] data = new Object[3][1];
	//first row
	data[0][0] = "Macbook";
	//second Row
	data[1][0] = "IMac";
	//third row
	data[2][0] = "Samsung";
	return data;
	}
			
	@Test (dataProvider = "productData",dependsOnMethods = "navigateToRegisterPageTest")
	public void searchTest(String productName) {
	boolean actSearchHeader = homePage.searchProduct(productName);
	Assert.assertTrue(actSearchHeader, "The product search should be successful foe : "+ productName);
	
	}

}
