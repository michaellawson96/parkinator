/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.CountyDAO;
import Dao.HttpStatusBase;
import Dto.County;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("Counties")
public class CountiesResource {

    @Context
    private UriInfo context;
    HttpStatusBase hsb = new HttpStatusBase();
    CountyDAO cdao = new CountyDAO();
    /**
     * Creates a new instance of CountiesResource
     */
    public CountiesResource() {
    }

     private Object convertJsonStringToCounty(String jsonString) {
        County c = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            c = new County();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int countyID =  ((Long)obj.get("countyID")).intValue();
            c.setCountyID(countyID);
            c.setCountyName((String) obj.get("countyName"));

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            c = null;
            return exp.getMessage();
        }
        return c;
    }
    
     private JSONObject convertCountyToJson(County c) {
        JSONObject jObj = new JSONObject();
        jObj.put("countyID", c.getCountyID());
        jObj.put("countyName", c.getCountyName());


        return jObj;
    }    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCounties() {
        ArrayList<County> c = (ArrayList<County>)cdao.selectAllCounties();
        if (c == null || c.isEmpty()) {
            return hsb.createMessage(-1, "No counties  Found");
        } else {
            JSONArray array = new JSONArray();
            for (County counies : c) {
                array.add(convertCountyToJson(counies));
            }
            return   array.toString();
        }
    }

    /**
     * PUT method for updating or creating an instance of CountiesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
