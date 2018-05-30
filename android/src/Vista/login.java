package Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mygdx.game.R;

import Vista.Admin.dashboard_admin;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logIn(View v){
        Intent intent = new Intent(getApplicationContext(),dashboard_admin.class);
        startActivity(intent);
    }

    public void signIn(View v){
        Intent  intent = new Intent(getApplicationContext(),signIn.class);
        startActivity(intent);
    }
}
