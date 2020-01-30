/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.UserDAOInterface;
import Dao.UserDao;
import Dto.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author snake
 */
@Path("Login")
public class LoginResourse {

    @Context
    private UriInfo context;

    private User convertJsonStringToUserForPost(String jsonString) {
        User u = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            u = new User();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            u.setEmail((String) obj.get("email"));
            u.setUserPassword((String) obj.get("password"));
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            u = null;
        }
        return u;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String Login(String content) {
        UserDAOInterface uDAO = new UserDao();
        User u = convertJsonStringToUserForPost(content);
        boolean existenceCheck = uDAO.Login(u.getEmail(), u.getUserPassword());
        if (existenceCheck == true) {//this means the customer has been successfully obtained
            return "true";
        } else {
            return "false";
        }
    }

}
