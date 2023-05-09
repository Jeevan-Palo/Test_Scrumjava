package com.palo.techtest.StepDefinition;

import java.util.List;
import java.util.Map;

import com.palo.techtest.StepLibrary.CommonLibrary;
import com.palo.techtest.StepLibrary.SearchProductLibrary;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchProduct {

	// Parameter

	@Given("^user should able to enter \"([^\"]*)\" in search textbox$")
	public void user_should_able_to_enter_in_search_textbox(String arg1) throws Throwable {
		SearchProductLibrary.searchValue(arg1);

	}

	@When("^user click on search button$")
	public void user_click_on_search_button() throws Throwable {
		SearchProductLibrary.clickSearchBtn();
	}

	@Then("^user should verify the search landing page$")
	public void user_should_verify_the_search_landing_page() throws Throwable {
		SearchProductLibrary.verify_Search_LandingPage();
	}

	@When("^user should able select \"([^\"]*)\" facet$")
	public void user_should_able_select_facet(String facetValue) throws Throwable {
		SearchProductLibrary.selectFacet(facetValue);
	}

	@Then("^user should able to list down the product name, INR Price, Offer of the available brand \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void user_should_able_to_list_down_the_product_name_INR_Price_Offer_of_the_available_brand(String arg1,
			String arg2, String arg3) throws Throwable {
		SearchProductLibrary.listDowndetails(arg1, arg2, arg3);
	}

	@Then("^user should able to verify the brand name price and offer of the selected product \"([^\"]*)\" or \"([^\"]*)\" or \"([^\"]*)\"$")
	public void user_should_able_to_verify_the_brand_name_price_and_offer_of_the_selected_product_or_or(String arg1,
			String arg2, String arg3) throws Throwable {
		SearchProductLibrary.verifyProductDetails(arg1, arg2, arg3);
	}

	@When("^user should able select \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void user_should_able_select(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		SearchProductLibrary.selectMultipleFacet(arg1, arg2, arg3, arg4);
	}

	// Data Input
	@When("^user should able to enter search keyword in search textbox$")
	public void user_should_able_to_enter_search_keyword_in_search_textbox(DataTable arg1) throws Throwable {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg1);
		SearchProductLibrary.searchText(dataMap);
	}

	@When("^user should able to select multiple facet$")
	public void user_should_able_to_select_multiple_facet(DataTable arg1) throws Throwable {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg1);
		SearchProductLibrary.select_MultipleFacet(dataMap);
	}

	@Then("^user should able to verify the details of the selected brand$")
	public void user_should_able_to_verify_the_details_of_the_selected_brand(DataTable arg1) throws Throwable {
		Map<String, List<String>> dataMap = null;
		dataMap = CommonLibrary.getHorizontalData(arg1);
		SearchProductLibrary.selected_ProductDetail(dataMap);
	}

}
