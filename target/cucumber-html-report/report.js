$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("snapdeal.feature");
formatter.feature({
  "line": 2,
  "name": "Search and Verify Product Details",
  "description": "",
  "id": "search-and-verify-product-details",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@snapdeal"
    }
  ]
});
formatter.background({
  "comments": [
    {
      "line": 4,
      "value": "#--------------------------------  Scenario Flow 1 --------------------------------------------------#"
    }
  ],
  "line": 5,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 6,
  "name": "Launching the browser",
  "keyword": "Given "
});
formatter.match({
  "location": "CommonSteps.launching_the_browser()"
});
formatter.result({
  "duration": 7202564200,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "Verify that user should able to search product , apply filter and verify the details of the selected product - Using_Parameter",
  "description": "",
  "id": "search-and-verify-product-details;verify-that-user-should-able-to-search-product-,-apply-filter-and-verify-the-details-of-the-selected-product---using-parameter",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 8,
      "name": "@Using_Parameter"
    }
  ]
});
formatter.step({
  "line": 10,
  "name": "user should able to enter \"Noise Cancellation headphone\" in search textbox",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "user click on search button",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "user should verify the search landing page",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "user should able select \"10 - 20\" facet",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "user should able select \"Sony\" facet",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "user should able select \"JBL\" facet",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "user should able select \"Marshall\" facet",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "user should able to list down the product name, INR Price, Offer of the available brand \"Sony\",\"JBL\",\"Marshall\"",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "user should able to verify the brand name price and offer of the selected product \"Sony\" or \"JBL\" or \"Marshall\"",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "close browser",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "Noise Cancellation headphone",
      "offset": 27
    }
  ],
  "location": "SearchProduct.user_should_able_to_enter_in_search_textbox(String)"
});
formatter.result({
  "duration": 4091957700,
  "status": "passed"
});
formatter.match({
  "location": "SearchProduct.user_click_on_search_button()"
});
formatter.result({
  "duration": 3877396300,
  "status": "passed"
});
formatter.match({
  "location": "SearchProduct.user_should_verify_the_search_landing_page()"
});
formatter.result({
  "duration": 1035430400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "10 - 20",
      "offset": 25
    }
  ],
  "location": "SearchProduct.user_should_able_select_facet(String)"
});
formatter.result({
  "duration": 5720349500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Sony",
      "offset": 25
    }
  ],
  "location": "SearchProduct.user_should_able_select_facet(String)"
});
formatter.result({
  "duration": 5611617500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "JBL",
      "offset": 25
    }
  ],
  "location": "SearchProduct.user_should_able_select_facet(String)"
});
formatter.result({
  "duration": 5585759000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Marshall",
      "offset": 25
    }
  ],
  "location": "SearchProduct.user_should_able_select_facet(String)"
});
formatter.result({
  "duration": 5524948100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Sony",
      "offset": 89
    },
    {
      "val": "JBL",
      "offset": 96
    },
    {
      "val": "Marshall",
      "offset": 102
    }
  ],
  "location": "SearchProduct.user_should_able_to_list_down_the_product_name_INR_Price_Offer_of_the_available_brand(String,String,String)"
});
formatter.result({
  "duration": 15597910900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Sony",
      "offset": 83
    },
    {
      "val": "JBL",
      "offset": 93
    },
    {
      "val": "Marshall",
      "offset": 102
    }
  ],
  "location": "SearchProduct.user_should_able_to_verify_the_brand_name_price_and_offer_of_the_selected_product_or_or(String,String,String)"
});
formatter.result({
  "duration": 11644599000,
  "status": "passed"
});
formatter.match({
  "location": "CommonSteps.close_browser()"
});
formatter.result({
  "duration": 14154006600,
  "status": "passed"
});
formatter.after({
  "duration": 297400,
  "status": "passed"
});
});