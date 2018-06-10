package Vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Controlador.Controller;
import Vista.Player.playerActivity;
import com.mygdx.game.AndroidLauncher;
import com.mygdx.game.R;

import org.json.JSONException;
import org.json.JSONObject;

import Vista.Admin.dashboard_admin;

public class login extends AppCompatActivity {
    private EditText mail1,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail1 = findViewById(R.id.txt_email_login);
        password1 = findViewById(R.id.txt_password_login);
    }

    public void logIn(View v){
        //String mail_aux = mail1.getText().toString();
        //String pass_aux = password1.getText().toString();
        String mail_aux = "admin@mail.com";
        String pass_aux = "a1234";
        if(!mail_aux.equals("") && !pass_aux.equals("")){
            String res = Controller.getInstance().login(mail_aux,pass_aux);
            try {
                JSONObject player = new JSONObject(res);
                String state = player.getJSONObject("data").getString("type_id");
                Toast.makeText(getApplicationContext(),state,Toast.LENGTH_LONG).show();
                //SharedPreferences.Editor sharedPreferences =
                //        getSharedPreferences("myref", Context.MODE_PRIVATE).edit();
                String token = player.getString("token");
                if(state.equals("1")){
                    Intent intent = new Intent(getApplicationContext(),dashboard_admin.class);
                    //    Controller.getInstance().setToken(token);
                    //    sharedPreferences.putString("token",token);
                    startActivity(intent);
                }else if(state.equals("2")){
                    Intent intent = new Intent(getApplicationContext(),playerActivity.class);
                    //    Controller.getInstance().setToken(token);
                    //    sharedPreferences.putString("token",token);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Log in error",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Empty error",Toast.LENGTH_LONG).show();
        }

        /*
        try {
            JSONObject player = new JSONObject(res);
            String state = player.getJSONObject("data").getString("type_id");
            Toast.makeText(getApplicationContext(),state,Toast.LENGTH_LONG).show();
            //SharedPreferences.Editor sharedPreferences =
            //        getSharedPreferences("myref", Context.MODE_PRIVATE).edit();
            String token = player.getString("token");
            if(state.equals("1")){
                Intent intent = new Intent(getApplicationContext(),dashboard_admin.class);
            //    Controller.getInstance().setToken(token);
            //    sharedPreferences.putString("token",token);
                startActivity(intent);
            }else if(state.equals("2")){
                Intent intent = new Intent(getApplicationContext(),playerActivity.class);
            //    Controller.getInstance().setToken(token);
            //    sharedPreferences.putString("token",token);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"Log in error",Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
    }

    public void signIn(View v){
        Intent  intent = new Intent(getApplicationContext(),signIn.class);
        startActivity(intent);
    }

    public void temp(View v){
        //Intent  intent = new Intent(getApplicationContext(),playerActivity.class);
        Intent  intent = new Intent(getApplicationContext(),AndroidLauncher.class);
        startActivity(intent);
    }
}

