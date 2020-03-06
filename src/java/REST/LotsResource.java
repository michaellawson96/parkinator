/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.LotDAO;
import Dto.Lot;
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
@Path("lots")
public class LotsResource {

    HttpStatusBase hsb = new HttpStatusBase();
    LotDAO ldao = new LotDAO();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LotsResource
     */
    public LotsResource() {
    }

    private Object convertJsonStringToLots(String jsonString) {
        Lot u = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            u = new Lot();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.

            u.setLot_id((int) obj.get("lot_id"));
            u.setParking_name((String) obj.get("parking_name"));
            u.setCc_id((int) obj.get("cc_id"));

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            u = null;
            return hsb.ParseError();
        }
        return u;
    }
    
    @POST
    @Path("addLots/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String AddingParkingLot(String content) {
        Object obj = convertJsonStringToLots(content);
        if (obj instanceof Lot) {
            Lot lot = (Lot) obj;
            return ldao.AddLot(lot);
        } else {
            return (String) obj;
        }

    }
    
    @POST
    @Path("removeLots/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String RemoveParkingLot(String content) {
        Object obj = convertJsonStringToLots(content);
        if (obj instanceof Lot) {
            Lot lot = (Lot) obj;
            return ldao.RemoveLot(lot);
        } else {
            return (String) obj;
        }

    }
}
