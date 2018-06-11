package Vista.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mygdx.game.R;

import org.json.JSONException;
import org.json.JSONObject;

import Controlador.Controller;

public class user_detail extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private TextView mail,kills,challengs,points;
    private EditText name;
    private String mail_temp,name_temp,pass_temp,run_temp
            ,type_temp,challenges_completed,points_temp,zombies_killed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mail = findViewById(R.id.txt_email_dt);
        kills = findViewById(R.id.txt_kill_dt);
        challengs = findViewById(R.id.txt_challeng_dt);
        points = findViewById(R.id.txt_points_dt);
        name = findViewById(R.id.txt_nickname_dt);
        toolbar = findViewById(R.id.toolbar_user);
        toolbar.setTitle(R.string.user_det);
        toolbar.setBackgroundColor(getResources().getColor(R.color.gray));
        toolbar.setTitleTextColor(getResources().getColor(R.color.blanco));
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String mail_aux = extras.getString("mail");
            Log.i("PUT EXTRA->",mail_aux);
            String out = Controller.getInstance().get_single_user(mail_aux);
            try {
                JSONObject jsonObject = new JSONObject(out);
                mail_temp = jsonObject.getString("mail");
                name_temp = jsonObject.getString("name");
                pass_temp = jsonObject.getString("password");
                run_temp = jsonObject.getString("run_aways");
                type_temp = jsonObject.getString("type_id");
                challenges_completed = jsonObject.getString("challenges_completed");
                points_temp = jsonObject.getString("points");
                zombies_killed = jsonObject.getString("zombies_killed");
                mail.setText("Mail:" + mail_temp);
                name.setText(name_temp);
                kills.setText("zombies killed: "+zombies_killed);
                challengs.setText("challenges completed:"+challenges_completed);
                points.setText("points: "+points_temp);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.editar:
                String out = Controller.getInstance().put_user(mail_temp,name_temp,pass_temp,run_temp,type_temp,
                        challenges_completed,points_temp,zombies_killed);
                Log.i("Edit->",out);
                Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
                Controller.getInstance().load_user();
                return true;
            case R.id.eliminar:
                String out1 = Controller.getInstance().delete_users(mail_temp);
                Log.i("Delete->",out1);
                Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                Controller.getInstance().load_user();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
