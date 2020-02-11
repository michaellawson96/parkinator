/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package REST;

import Dao.CarDAO;
import Dao.CarDAOInterface;
import Dto.Car;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
@Path("userCar")
public class UserCarResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserCarResource
     */
    public UserCarResource() {
    }
    
    private int convertJsonStringToUserNo(String jsonString) {
        int userNo = -1;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            
            userNo = ((Long) obj.get("user_no")).intValue();
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            userNo = -1;
        }
        return userNo;
    }
    
    private JSONObject convertCarToJson(Car car) {
        JSONObject jObj = new JSONObject();
        jObj.put("car_id", car.getCarNo());
        jObj.put("car_details", car.getCarDetails());
        jObj.put("car_reg", car.getCarReg());
        jObj.put("user_id", car.getUserNo());
        

        return jObj;
    }

    /**
     * Put method for updating or creating an instance of UserCarResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserCars(String content) {
        CarDAOInterface cDAO = new CarDAO();
        int userNo = convertJsonStringToUserNo(content);
        ArrayList<Car> cars = cDAO.getAllUserCars(userNo);
        JSONArray array = new JSONArray();
            for (Car car : cars) {
                array.add(convertCarToJson(car));
            }
            return array.toString();
    }
}
