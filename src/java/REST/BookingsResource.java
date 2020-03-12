/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.LotDAO;
import Dto.Lot;
import Dto.ParkedCars;
import Dto.User;
import Dto.Zone;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
 * @author SeppQ
 */
@Path("bookings")
public class BookingsResource {

    HttpStatusBase hsb = new HttpStatusBase();
    LotDAO ldao = new LotDAO();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookingsResource
     */
    public BookingsResource() {
    }

    private Object convertJsonStringToZone(String jsonString)  {
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
            int carId = ((Long) obj.get("car_id")).intValue();
            pc.setCar_id(carId);
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date from =  simpleDateFormat.parse(obj.get("book_from").toString());
            pc.setBookFrom(from);
            Date to = simpleDateFormat.parse(obj.get("book_to").toString());
            pc.setBookTo(to);
            int userId = ((Long) obj.get("user_id")).intValue();
            pc.setUser_id(userId);
        } catch (ParseException exp) {
            System.out.println(exp);
            pc = null;
            return exp.getMessage();//hsb.ParseError();
        } catch (java.text.ParseException jtp){
            return jtp.getMessage();
        }
        return pc;
    }
   private Object convertJsonStringToUser(String jsonString)  {
        User u = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            u = new User();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int user_id = ((Long) obj.get("user_id")).intValue();
            u.setUserNo(user_id);

        } catch (ParseException exp) {
            System.out.println(exp);
            u = null;
            return hsb.ParseError();
        }
        return u;
    }    
    private JSONObject convertBookingToJson(ParkedCars pc) {
        JSONObject jObj = new JSONObject();
        jObj.put("zone_id", pc.getZone_id());
        jObj.put("car_id", pc.getCar_id());
        jObj.put("book_from", pc.getBookFrom().toString());
        jObj.put("book_to", pc.getBookTo().toString());     
        jObj.put("user_id", pc.getUser_id()); 
        return jObj;
    }    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getBookings() {
        ArrayList<ParkedCars> pc = (ArrayList<ParkedCars>)ldao.selectAllBookigns();
        if (pc == null || pc.isEmpty()) {
            return hsb.CreateMessage(-1, "No bookings found Found");
        } else {
            JSONArray array = new JSONArray();
            for (ParkedCars pcs : pc) {
                array.add(convertBookingToJson(pcs));
            }
            return array.toString();
        }
    }
    @POST
    //@Path("addBooking/")  
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String addBooking(String content) {
        Object obj = convertJsonStringToZone(content);
        if (obj instanceof ParkedCars) {
            ParkedCars pc = (ParkedCars) obj;
            return ldao.AddBooking(pc);
        } else {
            return (String) obj;
        }

    }
    @POST
    @Path("displayBookings/")  
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String displayBookings(String content) {
        Object obj = convertJsonStringToUser(content);
        User u = (User) obj;
        ArrayList<ParkedCars> pc = (ArrayList<ParkedCars>)ldao.selectAllBookignsByUserId(u);
        if (pc == null || pc.isEmpty()) {
            return hsb.CreateMessage(-1, "No bookings found Found");
        } else {
            JSONArray array = new JSONArray();
            for (ParkedCars pcs : pc) {
                array.add(convertBookingToJson(pcs));
            }
            return array.toString();
        }

    }
}
