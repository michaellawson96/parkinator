/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package REST;

import Dao.CarDAO;
import Dao.CarDAOInterface;
import Dto.Car;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author USER
 */
@Path("car")
public class CarResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarResource
     */
    public CarResource() {
    }

     private Car convertJsonStringToCar(String jsonString) {
        Car c = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            c = new Car();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int carNumber =  ((Long)obj.get("car_id")).intValue();
            c.setCarNo(carNumber);
            c.setCarReg((String) obj.get("car_reg"));
            c.setCarDetails((String) obj.get("car_details"));
            int userNumber =  ((Long)obj.get("user_id")).intValue();
            c.setUserNo(userNumber);
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            c = null;
        }
        return c;
    }
    
    /**
     * POST method for creating an instance of CarResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean postText(String content) {
        CarDAOInterface cDAO = new CarDAO();
        Car c = convertJsonStringToCar(content);
        return cDAO.insertCar(c);
        
        //{"car_reg":"09-MN-6919","car_details":"Red Renault Megane","user_no":2}
        
    }
    
    /**
     * PUT method for updating or deleting an instance of CarResource
     * Sample input for UPDATE: {"car_details":"Red Renault Megane","user_id":14,"car_reg":"09-MN-6919","car_id":5,"action":"update"}
     * Sample input for DELETE: {"car_details":"Red Renault Megane","user_id":14,"car_reg":"09-MN-6919","car_id":5,"action":"delete"}
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean putText(String content) {
        CarDAOInterface cDAO = new CarDAO();
        
        //this will take the action from the json string and determine which action should be taken
        String action = determineAction(content);
        Car c = convertJsonStringToCar(content);
        
        //either case will return a boolean that shows whether or not the method succeeded
        switch(action){
            
            case "delete": return cDAO.deleteCar(c);
                
            case "update": return cDAO.updateCar(c);
                
        }
        
        //if the json string contained neither of those actions (or no action key at all)...
        return false;
    }
    
    
    private String determineAction(String jsonString){
        String action = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            
            action = ((String) obj.get("action"));
            
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            action = null;
        }
        return action;
    }
    
    
}
