package pages;

import static base.PlaywrightFactory.takeScreenshot;
import static utils.ExtentReporter.extentLog;
import static utils.ExtentReporter.extentLogWithScreenshot;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage {
	private Page page;
	private ExtentTest extentTest;
	private Logger log = LogManager.getLogger(LoginPage.class);

	public ProductPage(Page page, ExtentTest extentTest) {
		this.page = page;
		this.extentTest = extentTest;
	}

	private String productItem = "div.product-thumb div.caption";
	private String priceList = "p.price";
	private String priceExTaxList = "span.price-tax";
	private String sortInputDropDown = "#input-sort";
	private String myAccountMenu = "span[contains(.,'My Account')]";
	private String errorMsg = "//div[contains@class,'alert alert-danger']";

	/**
	 * this method fetches product name and price from product page
	 * 
	 * @return
	 */

	public Map<String, Double> getProductsAndPrice() {// step 1
		Map<String, Double> mapOfProductsAndPrice = new HashMap<>();
		Locator productItems = page.locator(productItem);

		for (int i = 0; i < productItems.count(); i++) {
			String productName = productItems.nth(i).locator("a").textContent();
			// get the price for each product
			Locator priceLocator = productItems.nth(i).locator(priceList);
			String priceString = priceLocator.textContent().replaceAll("[^0-9.]", "");// ^(negation) it is saying if not
																						// numbers(0-9)then replace it
																						// with ""
			Double price = Double.parseDouble(priceString);
			mapOfProductsAndPrice.put(productName, price);

		}
		return mapOfProductsAndPrice;

	}

	public boolean sortProductByPrice() { // step3
		Map<String, Double> MapSortedFromCode = sortMapByValue(getProductsAndPrice());
		// select price low to high in drop down
		page.selectOption(sortInputDropDown, page.locator("twxt = 'price (Low > High)'").elementHandle());
		// fetch the products and price from ui
		Map<String, Double> MapOfProductsPricesSortedFromUI = getProductsAndPrice();
		// compare
		if (MapOfProductsPricesSortedFromUI.equals(MapSortedFromCode)) {
			extentLog(extentTest, Status.PASS, "products are sorted by price in UI Successfull");
			return true;
		} else {
			extentLogWithScreenshot(extentTest, Status.FAIL, "The products are sorted by price in UI",
					takeScreenshot(page));
			return false;
		}
	}

	/**
	 * This method is usde to sort the map based on value
	 * 
	 * @param mapOfProductsAndPrice
	 * @return
	 */

	/*
	 * interview quetion can you sort the map based on the value -> below code is
	 * the fixed
	 */
	private Map<String, Double> sortMapByValue(Map<String, Double> mapOfProductsAndPrice) { // step 2
		// Converting the map to setCollection then stream it and call the srted() and
		// comparing by value(key and value)
		// and collect each key and value and based on oldvalue and newvalue compare the
		// oldvalue
		return mapOfProductsAndPrice.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors
				.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

}
