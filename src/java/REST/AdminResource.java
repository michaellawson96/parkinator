/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.UserDAOInterface;
import Dao.UserDao;
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
@Path("Admin")
public class AdminResource {

    @Context
    private UriInfo context;

    private JSONObject convertUserToJson(User user) {
        JSONObject jObj = new JSONObject();
        jObj.put("user_id", user.getUserNo());
        jObj.put("user_fullname", user.getUserFullname());
        jObj.put("email", user.getEmail());
        jObj.put("hash", user.getUserHash());
        jObj.put("user_type", user.getUserType());
        jObj.put("question", user.getQuestion());
        jObj.put("answer_hash", user.getAnswer_hash());
        jObj.put("has_disabled_badge", user.getHasDisabledBadge());

        return jObj;
    }

    private User convertJsonStringToUserForUpdate(String jsonString) {
        User u = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            u = new User();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
//            int id = Math.toIntExact((Long) obj.get("user_id"));
            // u.setUserNo(id);
            u.setUserFullname((String) obj.get("user_fullname"));
            u.setEmail((String) obj.get("email"));
            u.setUserHash((String) obj.get("hash"));
            u.setUserType((String) obj.get("user_type"));
            u.setQuestion((String) obj.get("question"));
            u.setAnswer_hash((String) obj.get("answer_hash"));
//            u.setHasDisabledBadge((boolean) obj.get("has_disabled_badge"));
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            u = null;
        }
        return u;
    }

    private User convertJsonStringToUserForDelete(String jsonString) {
        User u = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            u = new User();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int id = Math.toIntExact((Long) obj.get("user_id"));
            u.setUserNo(id);
            u.setUserFullname((String) obj.get("user_fullname"));
            u.setEmail((String) obj.get("email"));
            u.setUserHash((String) obj.get("hash"));
            u.setUserType((String) obj.get("user_type"));
            u.setQuestion((String) obj.get("question"));
            u.setAnswer_hash((String) obj.get("answer_hash"));
//            u.setHasDisabledBadge((boolean) obj.get("has_disabled_badge"));
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            u = null;
        }
        return u;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getText() {
        UserDAOInterface uDAO = new UserDao();
        ArrayList<User> users = uDAO.selectAllUsers();
        if (users == null || users.isEmpty()) {
            return "No results found";
        } else {
            //        // call the method shown on the previous slide to turn the user objects into json objects
//        JSONObject jObj1 = convertProductToJson(p1);
//        System.out.println("User json object = " + jObj1.toString());

            // Create a json array and add the two dummy objects, then stringify and return the array
            JSONArray array = new JSONArray();
            for (User user : users) {
                array.add(convertUserToJson(user));
            }
            return array.toString();
        }
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean UpdatesUserType(String content) {
        UserDao udao = new UserDao();
        User update = convertJsonStringToUserForUpdate(content);
        return udao.AdminUpdatesUserTypes(update);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean DeletesUser(String content) {
        UserDao udao = new UserDao();
        User delete = convertJsonStringToUserForDelete(content);
        return udao.AdminDeletesYser(delete);
    }
}
