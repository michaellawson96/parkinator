
@RunWith 
Feature: Fight or flight

   Scenario: Loging In
      Given the user put on the login page
      When the user inputs their email as "testinguser1@gmail.com"
       And the user inputs their password as "Testinguser1"
      Then the user is logged in
        And the user is brought to the home page
   
    Scenario: Regster
        Given the user is put on the register page 
        When the user inputs their Fullname as "Testing User1"
            And the user inputs their Email as "testinguser1@gamil.com"
            And the user inputs their Password as "Testinguser1"
            And the user choose their Question as "What is your childhood nickname?"
            And the user inputs their Answer as "Testie"
            And the user clicks on the Disabled Badge
        Then the user is registered
            And the user is logged in 
            And the user is put on the home page

