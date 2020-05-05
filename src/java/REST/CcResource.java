/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package REST;

import Dao.CcDAOInterface;
import Dao.CcDAO;
import Dao.HttpStatusBase;
import Dao.LotDAO;
import Dto.BookingDetailsCC;
import Dto.Car;
import Dto.Cc;
import Dto.ParkedCars;
import Dto.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("cc")
public class CcResource {
HttpStatusBase hsb = new HttpStatusBase();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CcResource
     */
    public CcResource() {
    }

    /**
     * --------------------------------------------
     * BASIC CRUD METHODS FOR USE BY ADMINISTRATORS
     * --------------------------------------------
     */
    
    /**
     * USER CREATE METHOD
     * @param content
     * @return String "true" || String "false"
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ccCreate(String content) {
        CcDAOInterface uDAO = new CcDAO();
        Cc cc = convertJsonStringToCc(content);
        boolean success = uDAO.insertCc(cc.getCcName());
        if (success == true) {
            return "true";
        } else {
            return "false";
        }        
    }
    
    /**
     * USER READ METHOD
     * @return a JSON array of cc JSON strings || a string "No results found"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ccRead() {
        CcDAOInterface uDAO = new CcDAO();
        ArrayList<Cc> ccs = uDAO.selectAllCcs();
        if (ccs == null || ccs.isEmpty()) {
            return "No results found";
        } else {
            //        // call the method shown on the previous slide to turn the cc objects into json objects
//        JSONObject jObj1 = convertProductToJson(p1);
//        System.out.println("Cc json object = " + jObj1.toString());

            // Create a json array and add the two dummy objects, then stringify and return the array
            JSONArray array = new JSONArray();
            for (Cc cc : ccs) {
                array.add(convertCcToJson(cc));
            }
            return array.toString();
        }
    }
    
    /**
     * USER UPDATE METHOD
     * @param content JSON String of Cc
     * @return boolean
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean ccUpdate(String content) {
        CcDAOInterface ccDAO = new CcDAO();
        Cc cc = convertJsonStringToCc(content);
        return ccDAO.updateCc(cc);
    }
    
    /**
     * USER DELETE METHOD
     * @param content JSON String of Cc
     * @return boolean
     */
    @Path("delete")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean ccDelete(String content) {
        CcDAOInterface uDAO = new CcDAO();
        int uid;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(content);
            // create a new Customer and use get method to retrieve values for a key
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            uid = ((Long) obj.get("cc_id")).intValue();
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            uid = -1;
        }
        return uDAO.deleteCc(uid);
    }
        /**
     * --------------------------------------------
     * -----------BASIC CRUD METHODS END-----------
     * --------------------------------------------
     */
    
    private JSONObject convertCcToJson(Cc cc) {
        JSONObject jObj = new JSONObject();
        jObj.put("cc_id", cc.getCcNo());
        jObj.put("cc_name", cc.getCcName());
        return jObj;
    }

    private Cc convertJsonStringToCc(String jsonString) {
        Cc cc = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            cc = new Cc();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int ccNumber =  ((Long)obj.get("cc_id")).intValue();
            cc.setCcNo(ccNumber);
            cc.setCcName((String) obj.get("cc_name"));
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            cc = null;
        }
        return cc;
    } 
     private Object convertJsonStringToParkedCar(String jsonString) {
         
        ParkedCars pc = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            pc = new ParkedCars();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int zoneId = ((Long) obj.get("zone_id")).intValue();
            pc.setZone_id(zoneId);
        } catch (ParseException exp) {
            System.out.println(exp);
            pc = null;
            return hsb.parseError();
        } 
        return pc;
    }    
     private JSONObject convertCarToJson(Car car) {
        JSONObject jObj = new JSONObject();
        jObj.put("car_id", car.getCarNo());
        jObj.put("car_colour", car.getCarColour());
        jObj.put("car_make", car.getCarMake());
        jObj.put("car_model", car.getCarModel());
        jObj.put("car_reg", car.getCarReg());
        jObj.put("user_id", car.getUserNo());

        return jObj;
    }     
     //testing need to be done
    @Path("getCarReg")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Object getCarRegUnderAZone(String content) {
        Object obj = convertJsonStringToParkedCar(content);
                CcDAOInterface uDAO = new CcDAO();

        ParkedCars pc = (ParkedCars) obj;
        ArrayList<Car> car = (ArrayList<Car>)uDAO.getAllregPlatesUnderAZone(pc);
        if (car == null || car.isEmpty()) {
            return hsb.createMessage(-1, "No Cars In This Zone");
        } else {
            JSONArray array = new JSONArray();
            for (Car c : car) {
                array.add(convertCarToJson(c));
            }
            return hsb.createMessage(1, array.toString());
        }
    }    
     private JSONObject convertBookingDetailsCCToJson(BookingDetailsCC bd) {
        JSONObject jObj = new JSONObject();
        jObj.put("car_reg", bd.getCar_reg());
        jObj.put("zone_name", bd.getZone_name());
        jObj.put("parking_name", bd.getParking_name());
        jObj.put("fullname", bd.getFullname());

        return jObj;
    }         
     //testing need to be done
     
    @Path("getAllCarRegFromAllZones")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Object getCarRegUnderAllZone( ) {
                CcDAOInterface uDAO = new CcDAO();
        ArrayList<BookingDetailsCC> bd = (ArrayList<BookingDetailsCC>)uDAO.getAllregPlatesUnderAllZone();
        if (bd == null || bd.isEmpty()) {
            return hsb.createMessage(-1, "No Cars Found");
        } else {
            JSONArray array = new JSONArray();
            for (BookingDetailsCC c : bd) {
                array.add(convertBookingDetailsCCToJson(c));
            }
            return hsb.createMessage(1, array.toString());
        }
    }      
}
