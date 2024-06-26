package base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import static utils.ExtentReporter.extentLogWithScreenshot;
import static base.PlaywrightFactory.takeScreenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentReporter;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.Tracing.StopOptions;

import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.RegisterPage;
import utils.TestProperties;
import utils.ExtentReporter;

public class BaseTest {

	protected Page page;
	protected SoftAssert softAssert = new SoftAssert();
	protected ExtentTest extentTest ,testNode;
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected RegisterPage registerPage ;
	protected ForgotPasswordPage forgotPasswordPage;
	protected ProductPage productPage;
	protected static ExtentReports extreporter;
	protected static TestProperties testproperties;
	private static Logger log;
	
	/**
	 * 
	 * Before method is used to do cleanup of test-results and initialization of 
	 * logger, TestProperties and extentrepots
	 * @throws Exception
	 */
	@BeforeSuite
	public void setUpBeforeTestSuite() throws Exception {
		File f = new File("test-results");
		if(f.exists()&&!deleteDirectory(f)) {
			throw new Exception("Exception accured while deleting the test-results directory");
		}
		log =LogManager.getLogger();
		testproperties = new TestProperties();
		testproperties.updateTestProperties();
		extreporter = ExtentReporter.getExtentReporter(testproperties);
	}
	/**
	 * AfterSuite method is used to assert all the softassertions and flush the ex
	 */
	
	@AfterSuite
	public void tearDownAfterSuite() {
		try {	
			softAssert.assertAll();
			extreporter.flush();
		}catch(Exception ex) {
			log.error("Error in AfterSuite method",ex);
		}
	}
	
	/**
	 * BeforeMethod to start the palywright server, create a page and navogate to the url
	 */
	@BeforeMethod
	public void lanchPlaywrightAndUrl() {
		PlaywrightFactory pf = new PlaywrightFactory(testproperties);
		page = pf.createPage();
		page.navigate(testproperties.getProperty("url"));
		testNode = extreporter.createTest("Test Node");// initialize testNode here or in ear
		extentTest = extreporter.createTest("Test"); //initialize extentTest here
		//initialize  HomePage 
		homePage = new HomePage(page, extentTest);
		loginPage = new LoginPage(page, extentTest);
	    registerPage = new RegisterPage(page, extentTest);
	    forgotPasswordPage= new ForgotPasswordPage(page, extentTest);
		
	}
	
	@AfterMethod
	public void closePageAndPwServer(ITestResult result) {
		if(extentTest !=null) {
		String testName = testNode.getModel().getName();
		if(Boolean.parseBoolean(testproperties.getProperty("enableTracing"))){
			String fileName = testproperties.getProperty("tracingDirectory")+"Trace_"+testName+".zip";
			page.context().tracing().stop(new Tracing.StopOptions().setPath(Paths.get(fileName)));
		}
		if(!result.isSuccess()) {
			extentLogWithScreenshot(testNode, Status.WARNING,"The test is not passed refer the prevoius step", takeScreenshot(page));
		}
		}else {
			log.warn("extentTest is null. Skipping trace and loggingsteps");
		}
		
		page.context().browser().close();
		extreporter.flush();
	}
	/**
	 * this method is used to clear the directories
	 */
	private boolean deleteDirectory(File directoryToBeDelete) {
		File[] allFolders = directoryToBeDelete.listFiles();
		if(allFolders!=null) {
			for(File folder:allFolders) {
				deleteDirectory(folder);//this predefined method will delete the dir
			}
		}
		return directoryToBeDelete.delete();
	}
	
	
	
}
