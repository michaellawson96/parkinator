/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package REST;

import Dao.HttpStatusBase;
import Dao.UserDAOInterface;
import Dao.UserDao;
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
    UserDAOInterface uDAO = new UserDao();
    
    /**
     * Retrieves representation of an instance of REST.VipResource
     * @return an instance of java.lang.String
     */
    @POST
    //default post is get by zone
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String vipReadZoneSpecified(String content) {
        //the json string inputted into the post method is converted from json to an integer (this may produce a hsb string)
        Object obj = convertJsonStringToZoneID(content);
        //if the object is a zoneID (not a hsb string, continue down that stream)
        if (obj instanceof Integer) {
            //the zone id will be casted to an int and placed in a new variable
            int zoneId = (int)obj;
            //that zone id will be used to select all vip records relevant to that zone
            ArrayList<Object> objs = vDAO.selectAllZoneVips(zoneId);
            //
            JSONArray users = new JSONArray();
            
            for(Object eachobj : objs){
                if(eachobj instanceof Vip){
                    Object uobj = uDAO.selectUserById(((Vip) eachobj).getUserId());
                    if(uobj instanceof User){
                        users.add(convertUserToJson((User)uobj));
                    }
                    else{
                        return (String)uobj;
                    }
                }
                else 
                    return (String)obj.toString();
            }
            return users.toJSONString();
        }
        return hsb.createMessage(72, "Invalid Zone");
    }
    
    @POST
    @Path("getByUser/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String vipReadUserSpecified(String content) {
        //the json string inputted into the post method is converted from json to an integer (this may produce a hsb string)
        Object obj = convertJsonStringToZoneID(content);
        //if the object is a zoneID (not a hsb string, continue down that stream)
        if (obj instanceof Integer) {
            //the zone id will be casted to an int and placed in a new variable
            int zoneId = (int)obj;
            //that zone id will be used to select all vip records relevant to that zone
            ArrayList<Object> objs = vDAO.selectAllZoneVips(zoneId);
            //
            JSONArray users = new JSONArray();
            
            for(Object eachobj : objs){
                if(eachobj instanceof Vip){
                    Object uobj = uDAO.selectUserById(((Vip) eachobj).getUserId());
                    if(uobj instanceof User){
                        users.add(convertUserToJson((User)uobj));
                    }
                    else{
                        return (String)uobj;
                    }
                }
                else 
                    return (String)obj.toString();
            }
            return users.toJSONString();
        }
        return hsb.createMessage(72, "Invalid Zone");
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
            String obj = vDAO.addVip(vip);
            

                return obj;
                }
          else {
            return (String) objV;
        }
    }
    
    @POST
    @Path("deleteVip/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String deleteVip(String content) {
        //we first need to figure out which zone's vips we are displaying. this is in the content String
        //we'll only need the id
        Object objV = convertJsonStringToVip(content);
        if (objV instanceof Vip) {
            Vip vip = (Vip)objV;
            String obj = vDAO.removeVip(vip);
            

                return obj;
                }
          else {
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
            v.setUserId(userId);
            

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            v = null;
            return hsb.parseError();
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
            return hsb.parseError();
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
    
    private JSONObject convertVipToJson(Vip vip) {
        JSONObject jObj = new JSONObject();
        jObj.put("user_id", vip.getUserId());
        jObj.put("zone_id", vip.getZoneId());


        return jObj;
    }
}
