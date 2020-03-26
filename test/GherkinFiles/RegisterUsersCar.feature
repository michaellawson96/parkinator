@RunWith 
Feature: A regular user can register a new car


   Scenario: Registering New Car Passing valid information
      Given on "http://localhost:8080/parkinator/test-resbeans.html" page
      When inputed '{"car_model":"dfgdfg","user_id":22,"car_reg":"dfgd","car_id":6,"car_colour":"dfdf","car_make":"dfgdfg"}' in "blobParam"
      Then returns true in the rawContent
   
    Scenario: Registering New Car Passing invalid information
      Given on "http://localhost:8080/parkinator/test-resbeans.html" page
      When inputed '{"car_model":"dfgdfg","user_id":22,"car_reg":"dfgd","car_id":6,"car_colour":"dfdf","car_make":"dfgdfg"}' in "blobParam"
      Then returns false in the rawContent
   