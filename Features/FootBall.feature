Feature: Ladbrokes functionality

Scenario: Journey 1
  Given open the Ladbrokes application
  When User Hover mouse over on Football tab
  Then the Football menu appears
  And click on Premier League
  Then Verify that Premier League page has been opened
  
Scenario: Journey 2
  Given open the Ladbrokes application
  When User Click on random article in the Sport News section
  Then Verify that the correct article has been opened
    
Scenario: Journey 3
  Given open the Ladbrokes application
  When User provides "Serie A" search phrase
  And User press Enter on the keyboard
  Then Verify that more than one page of results have been returned
  
