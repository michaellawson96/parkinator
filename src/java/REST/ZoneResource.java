/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.LotDAO;
import Dto.Lot;
import Dto.ParkedCars;
import Dto.User;
import Dto.Zone;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
@Path("zone")
public class ZoneResource {

    HttpStatusBase hsb = new HttpStatusBase();
    LotDAO ldao = new LotDAO();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ParkingZonesResource
     */
    private Object convertJsonStringToLot(String jsonString) {
        Lot l = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            l = new Lot();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            int lot_id = ((Long) obj.get("lot_id")).intValue();
            l.setLot_id(lot_id);

        } catch (ParseException exp) {
            System.out.println(exp);
            l = null;
            return hsb.ParseError();
        }
        return l;
    }

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
            int zoneId = ((Long) obj.get("zone_id")).intValue();
            z.setZone_id(zoneId);
            z.setZone_name((String) obj.get("zone_name"));
            int maxSpace = ((Long) obj.get("max_spaces")).intValue();
            z.setMax_spaces(maxSpace);
            z.setIs_vip((boolean) obj.get("is_vip"));
            int lotId = ((Long) obj.get("lot_id")).intValue();
            z.setLot_id(lotId);
            int maxDisableSpace = ((Long) obj.get("max_disabled_spaces")).intValue();
            z.setMax_disabled_spaces(maxDisableSpace);
            double lng = ((Number) obj.get("lng")).doubleValue();
            z.setLng(lng);
            double lat = ((Number) obj.get("alt")).doubleValue();
            z.setLat(lat);

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            z = null;
            return hsb.ParseError();
        }
        return z;
    }

    private JSONObject convertZoneToJson(Zone zone) {
        JSONObject jObj = new JSONObject();
        jObj.put("zone_id", zone.getZone_id());
        jObj.put("zone_name", zone.getZone_name());
        jObj.put("max_spaces", zone.getMax_spaces());
        jObj.put("is_vip", zone.isIs_vip());
        jObj.put("lot_id", zone.getLot_id());
        jObj.put("max_disabled_spaces", zone.getMax_disabled_spaces());
        jObj.put("lng", zone.getLng());
        jObj.put("alt", zone.getLat());
        return jObj;
    }

    @GET
    //@Path("getLots/")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getLots() {
        ArrayList<Zone> zone = (ArrayList<Zone>) ldao.selectAllZones();
        if (zone == null || zone.isEmpty()) {
            return hsb.CreateMessage(-1, "No Lots Found");
        } else {
            JSONArray array = new JSONArray();
            for (Zone zones : zone) {
                array.add(convertZoneToJson(zones));
            }
            return array.toString();
        }
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Object addZone(String content) {
        Object obj = convertJsonStringToZone(content);
        if (obj instanceof Zone) {
            Zone zone = (Zone) obj;
            return ldao.addzone(zone);
        } else {
            return (String) obj;
        }

    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("getZones/")
    public Object getZoneByLotId(String content) {
        Object obj = convertJsonStringToLot(content);
        Lot l = (Lot) obj;
        ArrayList<Zone> z = (ArrayList<Zone>) ldao.selectAllZoneByLotId(l);
        if (z == null || z.isEmpty()) {
            return hsb.CreateMessage(-1, "No Zone Has Been Added");
        } else {
            JSONArray array = new JSONArray();
            for (Zone zs : z) {
                array.add(convertZoneToJson(zs));
            }
            return array.toString();
        }

    }

    @POST
    @Path("removeZone/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeZone(String content) {
        Object obj = convertJsonStringToZone(content);
        if (obj instanceof Zone) {
            Zone z = (Zone) obj;
            return ldao.removeZone(z);
        } else {
            return (String) obj;
        }

    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Object updateZone(String content) {


        //this will take the action from the json string and determine which action should be taken
       Object obj = convertJsonStringToZone(content);
        if (obj instanceof Zone) {
            Zone z = (Zone) obj;
            return ldao.updateZone(z);
        } else {
            return (String) obj;
        }
    }
}
