/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.LotDAO;
import Dto.ParkedCars;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
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

    private Object convertJsonStringToZone(String jsonString) {
        ParkedCars pc = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            pc = new ParkedCars();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.

            pc.setZone_id((int) obj.get("zone_id"));
            pc.setCar_id((int) obj.get("car_id"));
            pc.setBookFrom((String) obj.get("bookFrom"));
            pc.setBookTo((String) obj.get("bookTo"));

        } 
        catch (ParseException exp) {
            System.out.println(exp);
            pc = null;
            return hsb.ParseError();
        }
        return pc;
    }
      
    @POST
    @Path("addBooking/")  
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Object addBooking(String content) {
        Object obj = convertJsonStringToZone(content);
        if (obj instanceof ParkedCars) {
            ParkedCars pc = (ParkedCars) obj;
            return ldao.AddBooking(pc);
        } else {
            return (String) obj;
        }
                
    }
    
    @POST
    @Path("checkOutdatedBookings/") 
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Object checkOutdatedBookings(String content) {
        Object obj = convertJsonStringToZone(content);
        if (obj instanceof ParkedCars) {
            ParkedCars pc = (ParkedCars) obj;
            return ldao.CheckOutDatedParkings(pc);
        } else {
            return (String) obj;
        }
                
    }    
}
