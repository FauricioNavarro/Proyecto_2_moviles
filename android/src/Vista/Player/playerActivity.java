package Vista.Player;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.mygdx.game.R;

import Controlador.Controller;
import Vista.Admin.achievementFragment;
import Vista.Admin.challengeFragment;
import Vista.Admin.user;

public class playerActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.ply_navigation_challenges:
                    transaction.replace(R.id.ply_fr_contenedor,new player_challenge_Fragment()).commit();
                    return true;
                case R.id.ply_navigation_achievements:
                    transaction.replace(R.id.ply_fr_contenedor,new player_achiev_Fragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        SharedPreferences sharedPreferences = getSharedPreferences("myref", Context.MODE_PRIVATE);
        String mail = sharedPreferences.getString("mail","");
        Log.i("User mail->",mail);
        Controller.getInstance().load_user_challenges(mail);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.ply_fr_contenedor,new player_challenge_Fragment()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.ply_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
