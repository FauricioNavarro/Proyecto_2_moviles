package Controlador;

import android.content.Context;
import android.content.SharedPreferences;
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
    private String token;

    private Controller() {
        try {
            users = new JSONArray(get_users());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
}
