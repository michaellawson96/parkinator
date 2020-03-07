/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.LotDAO;
import Dto.Zone;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author SeppQ
 */
@Path("zone")
public class ZoneResource {
 HttpStatusBase hsb = new HttpStatusBase();
    LotDAO ldao = new LotDAO();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ParkingZonesResource
     */

    private Object convertJsonStringToZone(String jsonString) {
        Zone z = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            z = new Zone();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.

            z.setLot_id((int) obj.get("lot_id"));
            z.setZone_name((String) obj.get("zone_name"));
            z.setMax_spaces((int) obj.get("max_spaces"));
            z.setIs_vip((boolean) obj.get("is_vip"));
            z.setLot_id ((int) obj.get("lot_id"));
            z.setMax_disabled_spaces((int) obj.get("max_disabled_spaces"));
            
         

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            z = null;
            return hsb.ParseError();
        }
        return z;
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Object addZone(String content) {
        Object obj = convertJsonStringToZone(content);
        if (obj instanceof Zone) {
            Zone zone = (Zone) obj;
            return ldao.Addzone(zone);
        } else {
            return (String) obj;
        }
        
    }
    
}
