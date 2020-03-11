
@RunWith 
Feature: TestingLogin

   Scenario: Loging In Passing valid information
      Given on "http://localhost:8080/parkinator/test-resbeans.html" page
      When inputed '{"email":"testinguser1@gmail.com", "hash":"Testinguser1"}' in 
      Then returns true in the rawContent
   
   Scenario: Loging in Fail using invald information
      Given on "http://localhost:8080/parkinator/test-resbeans.html" page
      When inputed '{"email":"notgoingtowork@gmail.com", "hash":"Testinguser1"}' in 
      Then returns false in the rawContent
