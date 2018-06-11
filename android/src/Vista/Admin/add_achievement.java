package Vista.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mygdx.game.R;

import Controlador.Controller;

public class add_achievement extends AppCompatActivity {
    private EditText name,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reward);
        name = findViewById(R.id.rw_name);
        description = findViewById(R.id.rw_des);
    }

    public void add_reward(View v){
        String name_aux = name.getText().toString();
        String description_aux = description.getText().toString();
        if(!name_aux.equals("") && !description_aux.equals("")){
            String out = Controller.getInstance().add_achiev(name_aux,description_aux);
            Log.i("ACHIEV->",out);
        }else{
            Toast.makeText(getApplicationContext(),"Empty error", Toast.LENGTH_LONG).show();
        }
        clean();
    }

    public void clean(){
        name.setText("");
        description.setText("");
    }
}
