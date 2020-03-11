/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.HttpStatus;
import org.json.simple.JSONObject;

/**
 *
 * @author Lukas
 */
public class HttpStatusBase {
    private JSONObject convertStatusToJson(HttpStatus hs) {
        JSONObject jObj = new JSONObject();
        jObj.put("status_code", hs.getStatusCode());
        jObj.put("message", hs.getMessage());
        return jObj;
    } 
    
    public String SQlError(){
        HttpStatus hs = new HttpStatus(2003,"Problem connecting to server");
        return convertStatusToJson(hs).toJSONString();
    }
    public String CreateMessage(int status_code,String message){
        HttpStatus hs = new HttpStatus(status_code,message);
        return convertStatusToJson(hs).toJSONString();
    }
    public String ExceptionError(){
        HttpStatus hs = new HttpStatus(408,"Problem With the Server Side");
        return convertStatusToJson(hs).toJSONString();
    }    
    public String ParseError(){
        HttpStatus hs = new HttpStatus(523,"Error has been reached unexpectedly while parsing.");
        return convertStatusToJson(hs).toJSONString();
    }      
}
