
@RunWith 
Feature: A registered user can view their account details

   # A very simple scenario
   Scenario: GetUserByEmail
      Given on "http://localhost:8080/parkinator/test-resbeans.html" page
      When inputed '{"email":"testinguser1@gmail.com"}' in "blobParam"
      Then returns "{\"user_type\":\"admin\",\"question\":\"What is your oldest sibling's middle name?\",\"user_id\":14,\"answer_hash\":\"$2a$12$CAi4ItWkf9y5TDmtvDybyeBwRuOrF3.ZD7ih5OZxVjTCB7mFewXNq\",\"user_fullname\":\"Michael Lawson\",\"email\":\"michael.c.k.lawson@gmail.com\",\"hash\":\"$2a$12$JEV2Q3QjkQUeBt3ppnKdt.tEw3w3CbHXhgwPK5eh.wDYctKp6knCy\",\"has_disabled_badge\":false}"