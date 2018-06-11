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
import android.widget.Toast;

import com.mygdx.game.R;

public class goal_detail extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private EditText name,lat,lon,point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);
        toolbar = findViewById(R.id.toolbar_goal);
        name = findViewById(R.id.txt_name_goal_dt);
        lat = findViewById(R.id.txt_latitud_goal_dt);
        lon = findViewById(R.id.txt_longitude_goal_dt);
        point = findViewById(R.id.txt_points_goal_dt);
        toolbar.setTitle(R.string.goal_det);
        toolbar.setBackgroundColor(getResources().getColor(R.color.gray));
        toolbar.setTitleTextColor(getResources().getColor(R.color.blanco));
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int id = extras.getInt("id_dt");
            String name_aux = extras.getString("name_dt");
            String points_aux = extras.getString("points_dt");
            String type_id = extras.getString("type_id_dt");
            String chall_id = extras.getString("chall_id_dt");
            String lat_aux = extras.getString("lat_dt");
            String lon_aux = extras.getString("lon_dt");
            name.setText(name_aux);
            point.setText(points_aux);
            lat.setText(lat_aux);
            lon.setText(lon_aux);
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
