@snapdeal
Feature: Search and Verify Product Details

  #--------------------------------  Scenario Flow 1 --------------------------------------------------#
  Background: 
    Given Launching the browser

  @Using_Parameter
  Scenario: Verify that user should able to search product , apply filter and verify the details of the selected product - Using_Parameter
    When user should able to enter "Noise Cancellation headphone" in search textbox
    And user click on search button
    Then user should verify the search landing page
    When user should able select "10 - 20" facet
    And user should able select "Sony" facet
    And user should able select "JBL" facet
    And user should able select "Marshall" facet
    And user should able to list down the product name, INR Price, Offer of the available brand "Sony","JBL","Marshall"
    Then user should able to verify the brand name price and offer of the selected product "Sony" or "JBL" or "Marshall"
    And close browser

  @Using_ScenarioOutline
  Scenario Outline: Verify that user should able to search product , apply filter and verify the details of the selected product - Using_Scenario Outline
    When user should able to enter "Noise Cancellation headphone" in search textbox
    And user click on search button
    Then user should verify the search landing page
    When user should able select "<Facet1>", "<Facet2>","<Facet3>","<Facet4>"
    And user should able to list down the product name, INR Price, Offer of the available brand "<Facet2>","<Facet3>","<Facet4>"
    Then user should able to verify the brand name price and offer of the selected product "<Facet2>" or "<Facet3>" or "<Facet4>"
    And close browser
		
    Examples: 
      | Facet1  | Facet2 | Facet3 | Facet4   |
      | 20 - 30 | Sony   | JBL    | Marshall |

  @Using_DataSheet
  Scenario: Verify that user should able to search product , apply filter and verify the details of the selected product - Using_DataSheet
    When user should able to enter search keyword in search textbox
      | InputFileName | SheetName | RowId   |
      | Search_Input  | Search    | Search1 |
    And user click on search button
    Then user should verify the search landing page
    When user should able to select multiple facet
      | InputFileName | SheetName | RowId   |
      | Search_Input  | Search    | Search1 |
    Then user should able to verify the details of the selected brand
      | InputFileName | SheetName | RowId   |
      | Search_Input  | Search    | Search1 |
    And close browser

   
