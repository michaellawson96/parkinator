
@RunWith 
Feature: TestingLogin_Pass

   Scenario: Loging In Passing valid information
      Given on "http://localhost:8080/parkinator/test-resbeans.html" page
      When inputed '{"email":"testinguser1@gmail.com", "hash":"Testinguser1"}' in 
      Then returns true
   
