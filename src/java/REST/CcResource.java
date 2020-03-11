/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package REST;

import Dao.CcDAOInterface;
import Dao.CcDAO;
import Dto.Cc;
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
@Path("cc")
public class CcResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CcResource
     */
    public CcResource() {
    }

    /**
     * --------------------------------------------
     * BASIC CRUD METHODS FOR USE BY ADMINISTRATORS
     * --------------------------------------------
     */
    
    /**
     * USER CREATE METHOD
     * @return String "true" || String "false"
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ccCreate(String content) {
        CcDAOInterface uDAO = new CcDAO();
        Cc cc = convertJsonStringToCc(content);
        boolean success = uDAO.insertCc(cc.getCcName());
        if (success == true) {
            return "true";
        } else {
            return "false";
        }        
    }
    
    /**
     * USER READ METHOD
     * @return a JSON array of cc JSON strings || a string "No results found"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ccRead() {
        CcDAOInterface uDAO = new CcDAO();
        ArrayList<Cc> ccs = uDAO.selectAllCcs();
        if (ccs == null || ccs.isEmpty()) {
            return "No results found";
        } else {
            //        // call the method shown on the previous slide to turn the cc objects into json objects
//        JSONObject jObj1 = convertProductToJson(p1);
//        System.out.println("Cc json object = " + jObj1.toString());

            // Create a json array and add the two dummy objects, then stringify and return the array
            JSONArray array = new JSONArray();
            for (Cc cc : ccs) {
                array.add(convertCcToJson(cc));
            }
            return array.toString();
        }
    }
    
    /**
     * USER UPDATE METHOD
     * @param content JSON String of Cc
     * @return boolean
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean ccUpdate(String content) {
        CcDAOInterface uDAO = new CcDAO();
        Cc u = convertJsonStringToCc(content);
        return uDAO.updateCc(u);
    }
    
    /**
     * USER DELETE METHOD
     * @param content JSON String of Cc
     * @return boolean
     */
    @Path("delete")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean ccDelete(String content) {
        CcDAOInterface uDAO = new CcDAO();
        int uid;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(content);
            // create a new Customer and use get method to retrieve values for a key
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            uid = ((Long) obj.get("cc_id")).intValue();
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            uid = -1;
        }
        return uDAO.deleteCc(uid);
    }
        /**
     * --------------------------------------------
     * -----------BASIC CRUD METHODS END-----------
     * --------------------------------------------
     */
    
    private JSONObject convertCcToJson(Cc cc) {
        JSONObject jObj = new JSONObject();
        jObj.put("cc_id", cc.getCcNo());
        jObj.put("cc_name", cc.getCcName());
        return jObj;
    }

    private Cc convertJsonStringToCc(String jsonString) {
        Cc cc = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            cc = new Cc();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            
            cc.setCcName((String) obj.get("cc_name"));
        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            cc = null;
        }
        return cc;
    } 
}
