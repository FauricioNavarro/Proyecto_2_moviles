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

import Controlador.Controller;

public class user_detail extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private TextView mail;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);
        mail = findViewById(R.id.txt_email_dt);
        name = findViewById(R.id.txt_nickname_dt);
        toolbar = findViewById(R.id.toolbar_goal);
        toolbar.setTitle(R.string.user_det);
        toolbar.setBackgroundColor(getResources().getColor(R.color.gray));
        toolbar.setTitleTextColor(getResources().getColor(R.color.blanco));
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String mail_aux = extras.getString("mail");
            Log.i("PUT EXTRA->",mail_aux);
            String out = Controller.getInstance().get_single_user(mail_aux);
            Toast.makeText(this,out,Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "EDITAR_", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.eliminar:
                Toast.makeText(getApplicationContext(), "ELIMINAR", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
