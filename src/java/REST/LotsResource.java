/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.LotDAO;
import Dao.UserDAOInterface;
import Dao.UserDao;
import Dto.Lot;
import Dto.User;
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

            int lotId =  ((Long)obj.get("lot_id")).intValue();
            u.setLot_id(lotId);
            u.setParking_name((String) obj.get("parking_name"));
            if(((Long)obj.get("cc_id")) != null){
            int ccId =  ((Long)obj.get("cc_id")).intValue();
             u.setCc_id(ccId);
            }else{
                u.setCc_id(1);
            }
           

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            u = null;
            return hsb.parseError();
        }
        return u;
    }
    
    
    private Object convertJsonStringToUserID(String jsonString) {
        int userId =-1;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            userId = ((Long) obj.get("user_id")).intValue();

            
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            return hsb.parseError();
        }
        return userId;
    }
    

    private JSONObject convertLotToJson(Lot lot) {
        JSONObject jObj = new JSONObject();
        jObj.put("lot_id", lot.getLot_id());
        jObj.put("parking_name", lot.getParking_name());
        jObj.put("cc_id", lot.getCc_id());
        return jObj;
    }

    @GET
    //@Path("getLots/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getLots() {
        ArrayList<Lot> lots = (ArrayList<Lot>)ldao.selectAllLots();
        if (lots == null || lots.isEmpty()) {
            return hsb.createMessage(-1, "No Lots Found");
        } else {
            JSONArray array = new JSONArray();
            for (Lot lot : lots) {
                array.add(convertLotToJson(lot));
            }
            return array.toString();
        }
    }
    
    //used to obtain a list of lots that contain zones where the logged in user is a vip of
    @POST
    @Path("getLotsByUser/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getLotsByUserId(String content) {
        Object potentialId = convertJsonStringToUserID(content);
        if(potentialId instanceof Integer){
            ArrayList<Lot> lots = (ArrayList<Lot>)ldao.selectLotsByUserId((int)potentialId);
            if (lots == null || lots.isEmpty()) {
                return hsb.createMessage(-1, "No Lots Found");
            } else {
                JSONArray array = new JSONArray();
                for (Lot lot : lots) {
                    array.add(convertLotToJson(lot));
                }
                return array.toString();
            }
        }
        else{
            return (String)potentialId;
        }
    }

    @POST
    //@Path("addLots/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String addParkingLot(String content) {
        Object obj = convertJsonStringToLots(content);
        if (obj instanceof Lot) {
            Lot lot = (Lot) obj;
            return ldao.addLot(lot);
        } else {
            return (String) obj;
        }

    }

    @POST
    @Path("removeLots/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeParkingLot(String content) {
        Object obj = convertJsonStringToLots(content);
        if (obj instanceof Lot) {
            Lot lot = (Lot) obj;
            return ldao.removeLot(lot);
        } else {
            return (String) obj;
        }

    }
}
