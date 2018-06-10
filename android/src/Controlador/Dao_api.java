package Controlador;

import android.os.AsyncTask;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fauricio on 09/06/18.
 */

public class Dao_api extends AsyncTask<String,Void,String> {
    private String url = "http://ec2-13-59-238-74.us-east-2.compute.amazonaws.com/api/";
    private String url_auth = "http://ec2-13-59-238-74.us-east-2.compute.amazonaws.com/auth/";

    @Override
    protected String doInBackground(String... strings) {
        String type = strings[0];
        String output = null;
        switch (type){
            case "register":{
                JSONObject player = new JSONObject();
                try {
                    player.put("name", strings[1]);
                    player.put("mail", strings[2]);
                    player.put("password", strings[3]);
                    player.put("type_id",1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = postRequest(url+"users",String.valueOf(player));
            }break;
            case "login":{
                JSONObject player = new JSONObject();
                try {
                    player.put("mail", strings[1]);
                    player.put("password", strings[2]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = postRequest(url_auth+"ingresar",String.valueOf(player));
            }break;
            case "users":{
                output = getRequest(url_auth+"users/");
            }break;
            case "single_user":{
                output = getRequest(url_auth+"users/"+strings[1]);
            }break;

        }
        return output;
    }

    public String postRequest(String path,String content){
        String res = null;
        try {
            HttpResponse<String> response = Unirest.post(path)
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .body(content)
                    .asString();
            res = response.getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getRequest(String path){
        String res = null;
        try {
            HttpResponse<String> response = Unirest.get("http://ec2-13-59-238-74.us-east-2.compute.amazonaws.com/api/users/")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Cache-Control", "no-cache")
                    .asString();
            res = response.getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return res;
    }
}
