/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.UserDAOInterface;
import Dao.UserDao;
import Dto.User;
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

/**
 * REST Web Service
 *
 * @author SeppQ
 */
@Path("UserDetails")
public class UserDetailsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserDetailsResource
     */
    public UserDetailsResource() {
    }

    private String convertJsonStringToEmail(String jsonString) {
        String email = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            email = (String)obj.get("email");

        } catch (Exception exp) {
            System.out.println(exp);

        }
        return email;
    }

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
    /**
     * Retrieves representation of an instance of REST.UserDetailsResource
     * @return an instance of java.lang.String
     */


    /**
     * PUT method for updating or creating an instance of UserDetailsResource
     * @param content representation for the resource
     */
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String postText(String content) {
        UserDAOInterface uDAO = new UserDao();
        String email = convertJsonStringToEmail(content);
        JSONObject jString= new JSONObject();
        jString = convertUserToJson(uDAO.getUserByEmail(email));

        return jString.toString();        
    }
}
