/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.Car_MakeDao;
import Dao.Car_MakeDaoInterface;
import Dto.Car_Make;
import Dto.HttpStatus;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author snake
 */
@Path("car_make")
public class Car_MakeResourse {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarResource
     */
    public Car_MakeResourse() {
    }

     private Car_Make convertJsonStringToCarMake(String jsonString) {
        Car_Make cm = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            cm = new Car_Make();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            cm.setCar_Make_Name((String) obj.get("car_make_name"));
            
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            cm = null;
        }
        return cm;
    }
     
     private JSONObject convertCarToJson(Car_Make cm) {
        JSONObject jObj = new JSONObject();
        jObj.put("car_make_id", cm.getCar_Make_id());
        jObj.put("car_make_name", cm.getCar_Make_Name());

        return jObj;
    }
     
     @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserCars(String content) {
        Car_MakeDaoInterface cmDao = new Car_MakeDao();
        ArrayList<Object> objs = (ArrayList<Object>) cmDao.getAllMakes();
        String objType;
        for (Object obj: objs){
            if(obj instanceof HttpStatus){
                objType = "HttpStatus";
                break;
            }
            else{
                objType = "Car_Make";
                break;
            }
        }
        JSONArray array = new JSONArray();
            for (Object obj : objs) {
                if(obj instanceof HttpStatus)
                    array.add(obj);
                else
                array.add(convertCarToJson((Car_Make)obj));
            }
            return array.toString();
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean postText(String content) {
        Car_MakeDaoInterface cmDao = new Car_MakeDao();
        Car_Make cm = convertJsonStringToCarMake(content);
        return (boolean) cmDao.insertMake(cm);        
    }
}
