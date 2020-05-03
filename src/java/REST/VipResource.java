/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package REST;

import Dao.HttpStatusBase;
import Dao.VipDAO;
import Dao.VipDAOInterface;
import Dto.HttpStatus;
import Dto.User;
import Dto.Vip;
import Dto.Zone;
import java.util.ArrayList;
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
@Path("vip")
public class VipResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VipResource
     */
    public VipResource() {
    }

    HttpStatusBase hsb = new HttpStatusBase();
    VipDAOInterface vDAO = new VipDAO();
    
    /**
     * Retrieves representation of an instance of REST.VipResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String vipReadSpecified(String content) {
        //we first need to figure out which zone's vips we are displaying. this is in the content String
        //we'll only need the id
        Object obj = convertJsonStringToZoneID(content);
        if (obj instanceof Integer) {
            int zoneId = (int)obj;
            ArrayList<Object> objs = vDAO.selectAllZoneVips(zoneId);
            
            JSONArray array = new JSONArray();
            for (Object eachobj : objs) {
                if(eachobj instanceof HttpStatus)
                    array.add(eachobj);
                else
                array.add(convertUserToJson((User)eachobj));
            }
            return array.toString();
            
        } else {
            return (String) obj;
        }
    }

    @POST
    @Path("addVip/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String addVip(String content) {
        //we first need to figure out which zone's vips we are displaying. this is in the content String
        //we'll only need the id
        Object objV = convertJsonStringToVip(content);
        if (objV instanceof Vip) {
            Vip vip = (Vip)objV;
            Object obj = vDAO.addVip(vip);
            
            JSONString string = new JSONString();

                if(obj instanceof Boolean){
                    return "\"success\":true";
                }
                else{
                return "\"success\":false";
                }
        } else {
            return (String) objV;
        }
    }
    
    private Object convertJsonStringToVip(String jsonString) {
        Vip v = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            v = new Vip();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int zoneId = ((Long) obj.get("zone_id")).intValue();
            v.setZoneId(zoneId);
            int userId = ((Long) obj.get("user_id")).intValue();
            v.setUserId(zoneId);
            

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            v = null;
            return hsb.ParseError();
        }
        return v;
    }
    
    private JSONObject convertBooleanToJson(boolean b) {
        JSONObject jObj = new JSONObject();
        jObj.put("success", b);
        return jObj;
    }
    
    
    private Object convertJsonStringToZoneID(String jsonString) {
        int zoneId =-1;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            zoneId = ((Long) obj.get("zone_id")).intValue();

            
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            return hsb.ParseError();
        }
        return zoneId;
    }
    
    private JSONObject convertUserToJson(User user) {
        JSONObject jObj = new JSONObject();
        jObj.put("user_id", user.getUserNo());
        jObj.put("user_fullname", user.getUserFullname());
        jObj.put("email", user.getEmail());
        jObj.put("user_type", user.getUserType());
        jObj.put("has_disabled_badge", user.getHasDisabledBadge());

        return jObj;
    }
}
