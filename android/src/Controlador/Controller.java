package Controlador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by fauricio on 09/06/18.
 */

public class Controller {
    private static final Controller ourInstance = new Controller();
    public static Controller getInstance() {
        return ourInstance;
    }
    private String ref = "Myref";
    private JSONArray users;
    private JSONArray achievements;
    private JSONArray challenges;
    private String token;

    private Controller() {
        try {
            users = new JSONArray(get_users());
            //achievements = new JSONArray(get_achievs());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void load_achievements(){
        try {
            achievements =  new JSONArray(get_achievs());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void load_challenges(){
        try {
            challenges =  new JSONArray(get_challenges());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void load_user_challenges(String mail){
        try {
            challenges =  new JSONArray(get_user_challengesGoalsUsers(mail));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void load_user(){
        try {
            users = new JSONArray(get_users());
            //achievements = new JSONArray(get_achievs());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getAchievements() {
        return achievements;
    }

    public void setAchievements(JSONArray achievements) {
        this.achievements = achievements;
    }

    public JSONArray getChallenges() {
        return challenges;
    }

    public void setChallenges(JSONArray challenges) {
        this.challenges = challenges;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONArray getUsers() {
        return users;
    }

    public void setUsers(JSONArray users) {
        this.users = users;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String signin(String name,String mail,String password){
        String result = null;
        try {
            result = new Dao_api().execute("register",name,mail,password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String login(String mail,String password){
        String result = null;
        try {
            result = new Dao_api().execute("login",mail,password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    //-------------- Sesión de usuarios --------------

    public String get_users(){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("users").get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String get_single_user(String mail){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("single_user",mail).get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONObject("data").toString();
                Log.i("REQUEST->",output);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String put_user(String mail_temp,String name_temp,String pass_temp,String run,
                String type,String challenges_completed,String points_temp,String zombies_killed){
        String out = null;
        try {
            Log.i("Edit->",mail_temp+"-"+pass_temp+"-"+name_temp+"-"+challenges_completed+"-"+points_temp+"-"+zombies_killed+"-"+run+"-"+type);
            out = new Dao_api().execute("put_user",mail_temp,pass_temp,name_temp,challenges_completed,points_temp,zombies_killed,run,type).get();
            /*
            String output_temp = new Dao_api().execute("put_user",mail_temp,pass_temp,name_temp,challenges_completed,points_temp,zombies_killed,run,type).get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                out = jsonObject.getJSONObject("data").toString();
                Log.i("REQUEST->",out);
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String delete_users(String mail){
        String output = null;
        try {
            output = new Dao_api().execute("delete_user",mail).get();
            /*
            String output_temp = new Dao_api().execute("delete_user").get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return output;
    }
    //-------------- Achievements --------------

    public String get_achievs(){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("achiev").get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String add_achiev(String name,String description){
        String output = null;
        try {
            output = new Dao_api().execute("add_achiev",name,description).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String put_achiev(String id,String name,String description){
        String out = null;
        try {
            out = new Dao_api().execute("put_achiev",id,name,description).get();
            /*
            String output_temp = new Dao_api().execute("put_user",mail_temp,pass_temp,name_temp,challenges_completed,points_temp,zombies_killed,run,type).get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                out = jsonObject.getJSONObject("data").toString();
                Log.i("REQUEST->",out);
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String delete_achiev(String id) {
        String output = null;
        try {
            output = new Dao_api().execute("delete_achiev", id).get();
            /*
            String output_temp = new Dao_api().execute("delete_user").get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return output;
    }

    //-------------- Challenges --------------

    public String get_challenges(){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("challenges").get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String add_challenge(String name,String description,String latitud_inicial,String longitud_inicial,
                                String latitud_final,String longitud_final,String zombies_probability){
        String output = null;
        try {
            output = new Dao_api().execute("add_challenge",name,description,latitud_inicial,longitud_inicial,
                    latitud_final,longitud_final,zombies_probability).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String get_single_challenge(String mail){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("single_challenge",mail).get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONObject("data").toString();
                Log.i("REQUEST->",output);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String get_challengesGoalsUsers(){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("challengesGoalsUsers").get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String get_user_challengesGoalsUsers(String mail){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("user_challengesGoalsUsers",mail).get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String goals_challenge(String id){
        String output = null;
        try {
            //output = new Dao_api().execute("goals_x_challenge",id).get();
            String output_temp = new Dao_api().execute("goals_x_challenge",id).get();

            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONArray("data").toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String get_single_goal(String id){
        String output = null;
        try {
            String output_temp = new Dao_api().execute("single_goal",id).get();
            JSONObject jsonObject = new JSONObject(output_temp);
            String state = jsonObject.getString("status");
            if(state.equals("success")){
                output = jsonObject.getJSONObject("data").toString();
                Log.i("REQUEST->",output);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String add_goal(String name,String latitud,String longitud,
                String points,String type,String challenge){
        String output = null;
        try {
            output = new Dao_api().execute("add_challenge",name,latitud,longitud,
                    points,type,challenge).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return output;
    }
}
