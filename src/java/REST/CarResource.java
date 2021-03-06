/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package REST;

import Dao.CarDAO;
import Dao.CarDAOInterface;
import Dto.Car;
import Dto.HttpStatus;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
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
            int carNumber = ((Long) obj.get("car_id")).intValue();
            c.setCarNo(carNumber);
            c.setAlias((String) obj.get("alias"));
            c.setCarReg((String) obj.get("car_reg"));
            c.setCarColour((String) obj.get("car_colour"));
            c.setCarMake((String) obj.get("car_make"));
            c.setCarModel((String) obj.get("car_model"));
            int userNumber = ((Long) obj.get("user_id")).intValue();
            c.setUserNo(userNumber);
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            c = null;
        }
        return c;
    }

    private JSONObject convertCarToJson(Car car) {
        JSONObject jObj = new JSONObject();
        jObj.put("car_id", car.getCarNo());
        jObj.put("car_colour", car.getCarColour());
        jObj.put("car_make", car.getCarMake());
        jObj.put("car_model", car.getCarModel());
        jObj.put("car_reg", car.getCarReg());
        jObj.put("alias", car.getAlias());
        jObj.put("user_id", car.getUserNo());

        return jObj;
    }

    private Car convertJsonStringToCar2(String jsonString) {
        Car c = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            c = new Car();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.

           
            c.setCarReg((String) obj.get("car_reg"));
            c.setCarColour((String) obj.get("car_colour"));
            c.setCarMake((String) obj.get("car_make"));
            c.setCarModel((String) obj.get("car_model"));
            int userNumber = ((Long) obj.get("user_id")).intValue();
            c.setUserNo(userNumber);
             c.setAlias((String) obj.get("alias"));
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            c = null;
        }
        return c;
    }

    /**
     * POST method for creating an instance of CarResource
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean postText(String content) {
        CarDAOInterface cDAO = new CarDAO();
        Car c = convertJsonStringToCar2(content);
        return cDAO.insertCar(c);

        //{"car_reg":"09-MN-6919","car_details":"Red Renault Megane","user_no":2}
    }

    /**
     * PUT method for updating or deleting an instance of CarResource Sample
     * input for UPDATE: {"car_colour":"Red", "car_make":"Renault",
     * "car_model":"Megane", "user_id":14,"car_reg":"09-MN-6919","car_id":5}
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean updateCar(String content) {
        CarDAOInterface cDAO = new CarDAO();

        //this will take the action from the json string and determine which action should be taken
        Car c = convertJsonStringToCar(content);

        return cDAO.updateCar(c);
    }

    /**
     * POST method for updating or deleting an instance of CarResource Sample
     * input for DELETE: {"car_colour":"Red", "car_make":"Renault",
     * "car_model":"Megane", "user_id":14,"car_reg":"09-MN-6919","car_id":5}
     *
     * @param content representation for the resource
     */
    @POST
    @Path("delete/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteCar(String content) {
        CarDAOInterface cDAO = new CarDAO();

        //this will take the action from the json string and determine which action should be taken
        Car c = convertJsonStringToCar(content);

        return cDAO.deleteCar(c);
    }

    /**
     * Get method for creating an instance of CarResource
     *
     * @param content representation for the resource
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserCars(String content) {
        CarDAOInterface cDAO = new CarDAO();
        ArrayList<Object> objs = cDAO.selectAllCars();
        String objType;
        for (Object obj : objs) {
            if (obj instanceof HttpStatus) {
                objType = "HttpStatus";
                break;
            } else {
                objType = "Car";
                break;
            }
        }
        JSONArray array = new JSONArray();
        for (Object obj : objs) {
            if (obj instanceof HttpStatus) {
                array.add(obj);
            } else {
                array.add(convertCarToJson((Car) obj));
            }
        }
        return array.toString();
    }
}
