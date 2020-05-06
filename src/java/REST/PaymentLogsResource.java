/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import Dao.HttpStatusBase;
import Dao.PaymentLogDAO;
import Dto.PaymentLogs;
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
@Path("PaymentLogs")
public class PaymentLogsResource {
    HttpStatusBase hsb = new HttpStatusBase();
    PaymentLogDAO pldao = new PaymentLogDAO();
    @Context
    private UriInfo context;
    private Object convertJsonStringToPaymentLog(String jsonString) {
        PaymentLogs pl = null;
        try {
            // create a parser to convert a string to a json object
            JSONParser parser = new JSONParser();
            // parser returns an object. You should know or check what to convert to (an JSONObject or JSONArray)
            JSONObject obj = (JSONObject) parser.parse(jsonString);

            // create a new Customer and use get method to retrieve values for a key
            pl = new PaymentLogs();
            // note that JSONObject has all numbers as longs, and needs to be converted to an int if required.
            pl.setId((String) obj.get("id"));
            pl.setCreate_time((String) obj.get("create_time"));
            pl.setIntent((String) obj.get("intent"));
            pl.setStatus((String) obj.get("status"));

        } // more detailed reporting can be done by catching specific exceptions, such as ParseException
        catch (ParseException exp) {
            System.out.println(exp);
            pl = null;
            return hsb.parseError();
        }
        return pl;
    }
    /**
     * Creates a new instance of PaymentLogsResource
     */
    public PaymentLogsResource() {
    }

    private JSONObject convertPaymentLogsToJson(PaymentLogs pl) {
        JSONObject jObj = new JSONObject();
        jObj.put("id", pl.getId());
        jObj.put("create_time", pl.getCreate_time());
        jObj.put("intent", pl.getIntent());
        jObj.put("status", pl.getStatus());     
        return jObj;
    }    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String getBookings() {
        ArrayList<PaymentLogs> pl = (ArrayList<PaymentLogs>)pldao.selectPaymentLogs();
        if (pl == null || pl.isEmpty()) {
            return hsb.createMessage(-1, "No Payement Logs Found");
        } else {
            JSONArray array = new JSONArray();
            for (PaymentLogs pcs : pl) {
                array.add(convertPaymentLogsToJson(pcs));
            }
            return  hsb.createMessage(1, array.toString());
        }
    }

    /**
     * PUT method for updating or creating an instance of PaymentLogsResource
     * @param content representation for the resource
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Object InsertLog(String content) {
        Object obj = convertJsonStringToPaymentLog(content);
        if (obj instanceof PaymentLogs) {
            PaymentLogs pl = (PaymentLogs) obj;
            return pldao.insertPaymentLogs(pl);
        } else {
            return (String) obj;
        }
    }
}
