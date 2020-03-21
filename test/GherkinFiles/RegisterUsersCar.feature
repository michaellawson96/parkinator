#----------------------------------
# Example of Cucumber .feature file
#----------------------------------
    
@RunWith 
Feature: Fight or flight

   # A very simple scenario
   Scenario: Simple Chuck
      Given on "http://localhost:8080/parkinator/test-resbeans.html" page
      When inputed '{"car_model":"dfgdfg","user_id":22,"car_reg":"dfgd","car_id":6,"car_colour":"dfdf","car_make":"dfgdfg"}' in "blobParam"
      Then returns false in the rawContent
   
