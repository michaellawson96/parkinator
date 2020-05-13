/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.Car_ModelDao;
import Dao.Car_ModelDaoInterface;
import Dto.Car_Make;
import Dto.Car_Model;
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
@Path("car_model")
public class Car_ModelResourse {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarResource
     */
    public Car_ModelResourse() {
    }

    private int convertJsonStringToCarMakeID(String jsonString) {
        int cmID;

        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            cmID = ((Long) obj.get("car_make_id")).intValue();

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            cmID = 0;
        }
        return cmID;
    }

    private Car_Model convertJsonStringToCarModel(String jsonString) {
        Car_Model cm = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            cm = new Car_Model();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            cm.setCar_Model_Name((String) obj.get("car_model_name"));
            int cm_id = ((Long) obj.get("car_make_id")).intValue();

            cm.setCar_Make_ID(cm_id);
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            cm = null;
        }
        return cm;
    }

    private JSONObject convertCarModelToJson(Car_Model cm) {
        JSONObject jObj = new JSONObject();
        jObj.put("car_model_id", cm.getCar_Model_ID());
        jObj.put("car_model_name", cm.getCar_Model_Name());
        jObj.put("car_make_id", cm.getCar_Make_ID());

        return jObj;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCar(String content) {
        Car_ModelDaoInterface cmDao = new Car_ModelDao();
        ArrayList<Object> objs = (ArrayList<Object>) cmDao.getAllModels();
        String objType;
        for (Object obj : objs) {
            if (obj instanceof HttpStatus) {
                objType = "HttpStatus";
                break;
            } else {
                objType = "Car_Model";
                break;
            }
        }
        JSONArray array = new JSONArray();
        for (Object obj : objs) {
            if (obj instanceof HttpStatus) {
                array.add(obj);
            } else {
                array.add(convertCarModelToJson((Car_Model) obj));
            }
        }
        return array.toString();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean postText(String content) {
        Car_ModelDaoInterface cmDao = new Car_ModelDao();
        Car_Model cm = convertJsonStringToCarModel(content);
        return (boolean) cmDao.insertModel(cm);
    }
    
    @Path("GetById")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getCarModelsByCarMakeID(String content) {
        Car_ModelDaoInterface cmDao = new Car_ModelDao();
        int cMakeID = convertJsonStringToCarMakeID(content);
        ArrayList<Object> objs = (ArrayList<Object>) cmDao.getAllModelsByCarMakeId(cMakeID);
        String objType;
        for (Object obj : objs) {
            if (obj instanceof HttpStatus) {
                objType = "HttpStatus";
                break;
            } else {
                objType = "Car_Model";
                break;
            }
        }
        JSONArray array = new JSONArray();
        for (Object obj : objs) {
            if (obj instanceof HttpStatus) {
                array.add(obj);
            } else {
                array.add(convertCarModelToJson((Car_Model) obj));
            }
        }
        return array.toString();
    }
}
