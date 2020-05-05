/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.SupportDao;
import Dto.Support;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * @author SeppQ
 */
@Path("Support")
public class SupportResource {

    HttpStatusBase hsb = new HttpStatusBase();
    SupportDao sdao = new SupportDao();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SupportResource
     */
    public SupportResource() {
    }

    private Object convertJsonStringToSupport(String jsonString) {
        Support s = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            s = new Support();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            int messageId = ((Long) obj.get("message_id")).intValue();
            s.setMessage_id(messageId);
            s.setTitle((String) obj.get("title"));
            s.setMessage((String) obj.get("message"));
            Date date = simpleDateFormat.parse(obj.get("date").toString());
            s.setDate(date);

            int user_id = ((Long) obj.get("user_id")).intValue();
            s.setUser_id(user_id);
            s.setStatus((String) obj.get("status"));
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            s = null;
            return hsb.parseError();
        }catch (java.text.ParseException jtp){
            return jtp.getMessage();
        }
        return s;
    }

    private Object convertJsonStringToSupportRemove(String jsonString) {
        Support s = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            s = new Support();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            int messageId = ((Long) obj.get("message_id")).intValue();
            s.setMessage_id(messageId);
            s.setTitle((String) obj.get("title"));
            s.setMessage((String) obj.get("message"));

            int user_id = ((Long) obj.get("user_id")).intValue();
            s.setUser_id(user_id);
            s.setStatus((String) obj.get("status"));

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            s = null;
            return hsb.parseError();
        }
        return s;
    }

    private JSONObject convertSupportToJson(Support sup) {
        JSONObject jObj = new JSONObject();
        jObj.put("message_id", sup.getMessage_id());
        jObj.put("title", sup.getTitle());
        jObj.put("message", sup.getMessage());
        jObj.put("date", sup.getDate().toString());
        jObj.put("user_id", sup.getUser_id());
        jObj.put("status", sup.getStatus());
        return jObj;
    }

    /**
     * Retrieves representation of an instance of REST.SupportResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getMessages() {
        ArrayList<Support> sup = (ArrayList<Support>) sdao.selectAllMessage();
        if (sup == null || sup.isEmpty()) {
            return hsb.createMessage(-1, "No Messages Found");
        } else {
            JSONArray array = new JSONArray();
            for (Support s : sup) {
                array.add(convertSupportToJson(s));
            }
            return array.toString();
        }
    }

    @POST
    @Path("getMessagesById/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getMessagesById(String content) {
        Object obj = convertJsonStringToSupport(content);
        if (obj instanceof Support) {
            Support support = (Support) obj;
            ArrayList<Support> sup = (ArrayList<Support>) sdao.selectAllMessageByUserId(support);
            if (sup == null || sup.isEmpty()) {
                return hsb.createMessage(-1, "No Messages Found");
            } else {
                JSONArray array = new JSONArray();
                for (Support s : sup) {
                    array.add(convertSupportToJson(s));
                }
                return hsb.createMessage(1, array.toString());
            }
        }else{
            return (String) obj;
        }
       
    }

    /**
     * PUT method for updating or creating an instance of SupportResource
     *
     * @param content representation for the resource
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Object InsertMessage(String content) {
        Object obj = convertJsonStringToSupport(content);
        if (obj instanceof Support) {
            Support sup = (Support) obj;
            return sdao.insertMessage(sup);
        } else {
            return (String) obj;
        }
    }
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Object updateStatus(String content) {
        Object obj = convertJsonStringToSupport(content);
        if (obj instanceof Support) {
            Support sup = (Support) obj;
            return sdao.statusUpdate(sup);
        } else {
            return (String) obj;
        }
    }
    @POST
    @Path("removeSupport/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeMessage(String content) {
        Object obj = convertJsonStringToSupportRemove(content);
        if (obj instanceof Support) {
            Support sup = (Support) obj;
            return sdao.removeMessage(sup);
        } else {
            return (String) obj;
        }

    }
}
