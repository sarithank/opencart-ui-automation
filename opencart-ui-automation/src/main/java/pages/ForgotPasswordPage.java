package pages;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

public class ForgotPasswordPage {
	
	private Page page;
	private ExtentTest extentTest;
	

	public ForgotPasswordPage(Page page, ExtentTest extentTest) {
		this.page = page;
		this.extentTest = extentTest;
	}
	
	public String getForgotPasswordPageTitle() {
		return page.title();
		
	}

}
