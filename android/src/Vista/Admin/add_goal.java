package Vista.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mygdx.game.R;

import Controlador.Controller;

public class add_goal extends AppCompatActivity {
    private EditText name,latitud,longitud,
            points,type,challenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        name = findViewById(R.id.txt_name_goal);
        latitud = findViewById(R.id.txt_latitud_goal);
        longitud = findViewById(R.id.txt_longitude_goal);
        points = findViewById(R.id.txt_points_goal);
        type = findViewById(R.id.txt_type_goal);
        challenge = findViewById(R.id.txt_challg_goal);
    }

    public void add_goal(View v){
        String name_aux = name.getText().toString();
        String latitud_aux = latitud.getText().toString();
        String longitud_aux = longitud.getText().toString();
        String points_aux = points.getText().toString();
        String type_aux = type.getText().toString();
        String challenge_aux = challenge.getText().toString();
        Controller.getInstance().add_goal(name_aux,latitud_aux,longitud_aux,points_aux,type_aux,challenge_aux);
        clean();
    }

    public void clean(){
        name.setText("");
        latitud.setText("");
        longitud.setText("");
        points.setText("");
        type.setText("");
        challenge.setText("");
    }
}
