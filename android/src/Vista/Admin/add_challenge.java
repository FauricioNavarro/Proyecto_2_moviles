package Vista.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mygdx.game.R;

import Controlador.Controller;

public class add_challenge extends AppCompatActivity {
    private EditText name,description,latitud_inicial,longitud_inicial,
            latitud_final,longitud_final,zombies_probability;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
        name = findViewById(R.id.ch_name);
        description = findViewById(R.id.ch_des);
        latitud_inicial = findViewById(R.id.ch_lat_inicial);
        longitud_inicial = findViewById(R.id.ch_lon_inicial);
        latitud_final = findViewById(R.id.ch_lat_fin);
        longitud_final = findViewById(R.id.ch_lon_fin);
        zombies_probability = findViewById(R.id.ch_zombie_pro);
    }

    public void add_challenge(View v){
        String name_aux = name.getText().toString();
        String description_aux = description.getText().toString();
        String latitud_inicial_aux = latitud_inicial.getText().toString();
        String longitud_inicial_aux = longitud_inicial.getText().toString();
        String latitud_final_aux = latitud_final.getText().toString();
        String longitud_final_aux = longitud_final.getText().toString();
        String zombies_probability_aux = zombies_probability.getText().toString();
        if(!name_aux.equals("") && !description_aux.equals("") && !latitud_inicial_aux.equals("") &&
                !longitud_inicial_aux.equals("") && !latitud_final_aux.equals("") &&
                !longitud_final_aux.equals("") && !zombies_probability_aux.equals("")){

            Controller.getInstance().add_challenge(name_aux,description_aux,latitud_inicial_aux,
                    longitud_inicial_aux,latitud_final_aux,longitud_final_aux,zombies_probability_aux);
            clean();
        }
    }

    public void clean(){
        name.setText("");
        description.setText("");
        latitud_inicial.setText("");
        longitud_inicial.setText("");
        latitud_final.setText("");
        longitud_final.setText("");
        zombies_probability.setText("");
    }
}
