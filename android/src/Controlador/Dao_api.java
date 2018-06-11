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
                output = postRequest(url+"users",String.valueOf(player),null);
            }break;
            case "login":{
                JSONObject player = new JSONObject();
                try {
                    player.put("mail", strings[1]);
                    player.put("password", strings[2]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = postRequest(url_auth+"ingresar",String.valueOf(player),null);
            }break;
            case "users":{
                output = getRequest(url+"users/",null);
            }break;
            case "single_user":{
                output = getRequest(url+"users/"+strings[1],Controller.getInstance().getToken());
            }break;
            case "put_user":{
                JSONObject put_player = new JSONObject();
                try {
                    put_player.put("mail", strings[1]);
                    put_player.put("password", strings[2]);
                    put_player.put("name", strings[3]);
                    put_player.put("challenges_completed", strings[4]);
                    put_player.put("points", strings[5]);
                    put_player.put("zombies_killed", strings[6]);
                    put_player.put("run_aways", strings[7]);
                    put_player.put("type_id", strings[8]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = putRequest(url+"users/"+strings[1],Controller.getInstance().getToken(),String.valueOf(put_player));
            }break;
            case "delete_user":{
                output = deleteRequest(url+"users/"+strings[1],Controller.getInstance().getToken());
            }break;
            case "add_challenge":{
                JSONObject challenge = new JSONObject();
                try {
                    challenge.put("name", strings[1]);
                    challenge.put("description", strings[2]);
                    challenge.put("latitud_inicial", strings[3]);
                    challenge.put("longitud_inicial", strings[4]);
                    challenge.put("latitud_final", strings[5]);
                    challenge.put("longitud_final", strings[6]);
                    challenge.put("zombies_probability", strings[7]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = postRequest(url+"challenges/",String.valueOf(challenge),Controller.getInstance().getToken());
            }break;
            case "challenges":{
                output = getRequest(url+"challenges/",Controller.getInstance().getToken());
            }break;
            case "single_challenge":{
                output = getRequest(url+"challenges/"+strings[1],Controller.getInstance().getToken());
            }break;
            case "achiev":{
                output = getRequest(url+"achievements/",Controller.getInstance().getToken());
            }break;
            case "add_achiev":{
                JSONObject achiev = new JSONObject();
                try {
                    achiev.put("name", strings[1]);
                    achiev.put("description", strings[2]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = postRequest(url+"achievs/",String.valueOf(achiev),Controller.getInstance().getToken());
            }break;
            case "put_achiev":{
                JSONObject achiev = new JSONObject();
                try {
                    achiev.put("name", strings[2]);
                    achiev.put("description", strings[3]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = putRequest(url+"achievs/"+strings[1],Controller.getInstance().getToken(),String.valueOf(achiev));
            }break;
            case "delete_achiev":{
                output = deleteRequest(url+"achievs/"+strings[1],Controller.getInstance().getToken());
            }break;
            case "challengesGoalsUsers":{
                output = getRequest(url+"challengesGoalsUsers/",Controller.getInstance().getToken());
            }break;
            case "user_challengesGoalsUsers":{
                output = getRequest(url+"challengesGoalsUsers/"+strings[1],Controller.getInstance().getToken());
            }break;
            case "goals_x_challenge":{
                output = getRequest(url+"goals_x_challenge/"+strings[1],Controller.getInstance().getToken());
            }break;
            case "single_goal":{
                output = getRequest(url+"goals/"+strings[1],Controller.getInstance().getToken());
            }break;
            case "add_goal":{
                JSONObject goal = new JSONObject();
                try {
                    goal.put("name", strings[1]);
                    goal.put("latitud", strings[2]);
                    goal.put("longitud", strings[3]);
                    goal.put("points", strings[4]);
                    goal.put("type_id", strings[5]);
                    goal.put("challenge_id", strings[6]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                output = postRequest(url+"goals/",String.valueOf(goal),Controller.getInstance().getToken());
            }break;
        }
        return output;
    }

    public String postRequest(String path,String content,String auth){
        String res = null;
        try {
            if(auth==null){
                HttpResponse<String> response = Unirest.post(path)
                        .header("content-type", "application/json")
                        .header("cache-control", "no-cache")
                        .body(content)
                        .asString();
                res = response.getBody().toString();
            }else{
                HttpResponse<String> response = Unirest.post(path)
                        .header("content-type", "application/json")
                        .header("cache-control", "no-cache")
                        .header("Authorization", auth)
                        .body(content)
                        .asString();
                res = response.getBody().toString();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getRequest(String path,String auth){
        String res = null;
        try {
            if(auth==null){
                HttpResponse<String> response = Unirest.get(path)
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Cache-Control", "no-cache")
                        .asString();
                res = response.getBody().toString();
            }else{
                HttpResponse<String> response = Unirest.get(path)
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Cache-Control", "no-cache")
                        .header("Authorization", auth)
                        .asString();
                res = response.getBody().toString();
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String putRequest(String url,String auth,String content){
        String res = null;
        try {
            HttpResponse<String> response = Unirest.put(url)
                    .header("content-type", "application/json")
                    .header("Cache-Control", "no-cache")
                    .header("Authorization", auth)
                    .body(content)
                    .asString();
            res = response.getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String deleteRequest(String url,String auth){
        String res = null;
        try {
            HttpResponse<String> response = Unirest.delete(url)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Cache-Control", "no-cache")
                    .header("Authorization", auth)
                    .asString();
            res = response.getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return res;
    }


}
