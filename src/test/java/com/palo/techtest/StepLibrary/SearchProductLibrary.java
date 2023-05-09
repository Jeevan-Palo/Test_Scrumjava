package com.palo.techtest.StepLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.palo.techtest.Constant.SearchProductConst;

public class SearchProductLibrary extends CommonLibrary {
	
	
	public static void searchValue(String arg1) throws Exception {
		CommonLibrary.clearAndEnterText(SearchProductConst.SEARCH_TXTBOX, arg1);

	}
	
	public static void clickSearchBtn() throws Exception {
		CommonLibrary.isElementPresentVerifyClick(SearchProductConst.SEARCH_BTN);
	}
	
	public static void verify_Search_LandingPage() {
		isElementPresentVerification(SearchProductConst.SEARCH_RESULT_TXT);

	}
	
	public static void selectFacet(String facetValue) throws Exception {
		try {
			Thread.sleep(5000);
			List<WebElement> facetName;
			List<WebElement> selectedFacet;
			facetName = CommonLibrary.webDriver.findElements(By.className("filters-list"));
			selectedFacet = facetName.stream().filter(e -> e.getText().contains(facetValue))
					.collect(Collectors.toList());
			if (selectedFacet != null) {
				selectedFacet.get(0).click();
				System.out.println(facetValue + " value is available");
			}

		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println(facetValue + " value is not available");

		} catch (Exception exception) {
			System.out.println(facetValue + " value is not available");

		}
	}
	
	public static void listDowndetails(String arg1, String arg2, String arg3) throws Exception {
		Thread.sleep(5000);
		List<WebElement> productList;
		List<WebElement> productDesc;

		String[] facetArray = { arg1, arg2, arg3 };
		for (int i = 0; i <= facetArray.length - 1; ++i) {

			try {
				String facetValue = facetArray[i];

				productList = CommonLibrary.webDriver.findElements(By.className("product-title"));
				productDesc = productList.stream().filter(e -> e.getText().contains(facetValue))
						.collect(Collectors.toList());
				List<WebElement> productList1 = CommonLibrary.webDriver
						.findElements(By.className("product-tuple-description"));
				List<WebElement> productList2 = productList1.stream().filter(e -> e.getText().contains(facetValue))
						.collect(Collectors.toList());
				List<String> productName = productList2.stream()
						.map(e -> e.findElement(By.className("product-title")).getText()).collect(Collectors.toList());
				List<String> productPrice = productList2.stream()
						.map(e -> e.findElement(By.className("product-price")).getText()).collect(Collectors.toList());
				List<String> productOffer = productList2.stream()
						.map(e -> e.findElement(By.className("product-discount")).getText())
						.collect(Collectors.toList());

				if (productDesc.isEmpty()) {
					System.out.println(facetValue + " value is not available");
				} else {

					for (int j = 0; j < productDesc.size(); j++) {
						Thread.sleep(5000);
						// List<String> productName = productDesc.stream().map(e ->
						// e.getText()).collect(Collectors.toList());
						System.out.println("Product Name : " + productName + " " + "| Product Price : " + productPrice
								+ "" + "| Product Offer : " + productOffer);

					}
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println(" value is not available");

			} catch (Exception exception) {
				System.out.println(" value is not available");

			}

		}

	}
	
	public static void verifyProductDetails(String arg1, String arg2, String arg3) throws Exception {
		Thread.sleep(5000);
		List<WebElement> productList;
		List<WebElement> productDesc;

		String[] facetArray = { arg1, arg2, arg3 };
		for (int i = 0; i <= facetArray.length; i++) {

			try {
				String facetValue = facetArray[i];

				productList = CommonLibrary.webDriver.findElements(By.className("product-title"));
				productDesc = productList.stream().filter(e -> e.getText().contains(facetValue))
						.collect(Collectors.toList());

				if (productDesc.isEmpty()) {
					System.out.println(facetValue + " value is not available");
				} else {
					productDesc.get(0).click();
					System.out.println(facetValue + " value is available");
					ArrayList<String> tabs2 = new ArrayList<String>(webDriver.getWindowHandles());
					webDriver.switchTo().window(tabs2.get(1));
					Thread.sleep(5000);
					String productName = getElementText(SearchProductConst.PRODUCT_NAME);
					String productActualPrice = getElementText(SearchProductConst.PRODUCT_ACT_PRICE);
					String productFinalPrice = getElementText(SearchProductConst.PRODUCT_FINAL_PRICE);
					String productDiscount = getElementText(SearchProductConst.PRODUCT_DISCOUNT);
					System.out.println("Product Name : " + productName + "Actual Price : " + productActualPrice
							+ "|productFinalPrice :" + productFinalPrice + "|productDiscount :" + productDiscount);
					webDriver.close();
					webDriver.switchTo().window(tabs2.get(0));
					break;

				}

			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println(" value is not available");

			} catch (Exception exception) {
				System.out.println(" value is not available");

			}
		}
		
	}
	
	public static void selectMultipleFacet(String arg1, String arg2, String arg3, String arg4) throws Exception {

		Thread.sleep(5000);
		List<WebElement> facetName;
		List<WebElement> selectedFacet;

		String[] facetArray = { arg1, arg2, arg3, arg4 };
		for (int i = 0; i <= facetArray.length; i++) {

			try {
				String facetValue = facetArray[i];

				facetName = CommonLibrary.webDriver.findElements(By.className("filters-list"));
				selectedFacet = facetName.stream().filter(e -> e.getText().contains(facetValue))
						.collect(Collectors.toList());

				if (selectedFacet.isEmpty()) {
					System.out.println(facetValue + " value is not available");
				} else {
					selectedFacet.get(0).click();
					System.out.println(facetValue + " value is available");
					Thread.sleep(5000);

				}

			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println(" value is not available");

			} catch (Exception exception) {
				System.out.println(" value is not available");

			}
		}
	}
//Data Input
	public static void searchText(Map<String, List<String>> dataMap) throws Exception {
		String lastName = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "SearchKeyword");

		try {
			clearAndEnterText(SearchProductConst.SEARCH_TXTBOX, lastName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void select_MultipleFacet(Map<String, List<String>> dataMap) throws Exception {
		String facet1 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "Offer");
		String facet2 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "Brand1");
		String facet3 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "Brand2");
		String facet4 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "Brand3");

		Thread.sleep(5000);
		List<WebElement> facetName;
		List<WebElement> selectedFacet;

		String[] facetArray = { facet1, facet2, facet3, facet4 };
		for (int i = 0; i <= facetArray.length; i++) {

			try {
				String facetValue = facetArray[i];

				facetName = CommonLibrary.webDriver.findElements(By.className("filters-list"));
				selectedFacet = facetName.stream().filter(e -> e.getText().contains(facetValue))
						.collect(Collectors.toList());

				if (selectedFacet.isEmpty()) {
					System.out.println(facetValue + " value is not available");
				} else {
					selectedFacet.get(0).click();
					System.out.println(facetValue + " value is available");
					Thread.sleep(5000);

				}

			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println(" value is not available");

			} catch (Exception exception) {
				System.out.println(" value is not available");

			}
		}

	}
	
	public static void selected_ProductDetail(Map<String, List<String>> dataMap) throws Exception {
		Thread.sleep(5000);
		String brand1 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "Brand1");
		String brand2 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "Brand2");
		String brand3 = getXLSTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0),
				dataMap.get("RowId").get(0), "Brand3");

		List<WebElement> productList;
		List<WebElement> productDesc;

		String[] facetArray = { brand1, brand2, brand3 };
		for (int i = 0; i <= facetArray.length; i++) {

			try {
				String facetValue = facetArray[i];

				productList = CommonLibrary.webDriver.findElements(By.className("product-title"));
				productDesc = productList.stream().filter(e -> e.getText().contains(facetValue))
						.collect(Collectors.toList());

				if (productDesc.isEmpty()) {
					System.out.println(facetValue + " value is not available");
				} else {
					productDesc.get(0).click();
					System.out.println(facetValue + " value is available");
					ArrayList<String> tabs2 = new ArrayList<String>(webDriver.getWindowHandles());
					webDriver.switchTo().window(tabs2.get(1));
					Thread.sleep(5000);
					String productName = getElementText(SearchProductConst.PRODUCT_NAME);
					String productActualPrice = getElementText(SearchProductConst.PRODUCT_ACT_PRICE);
					String productFinalPrice = getElementText(SearchProductConst.PRODUCT_FINAL_PRICE);
					String productDiscount = getElementText(SearchProductConst.PRODUCT_DISCOUNT);
					System.out.println("Product Name : " + productName + "Actual Price : " + productActualPrice
							+ "|productFinalPrice :" + productFinalPrice + "|productDiscount :" + productDiscount);
					webDriver.close();
					webDriver.switchTo().window(tabs2.get(0));
					break;

				}

			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println(" value is not available");

			} catch (Exception exception) {
				System.out.println(" value is not available");

			}
		}

	}
}
