package Vista.Admin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mygdx.game.R;

import java.util.ArrayList;

import Controlador.Goal_adapter;
import Controlador.User_adapter;
import Modelo.Goal;
import Modelo.User;

public class goal_list extends AppCompatActivity {
    private FloatingActionButton new_goal;
    private ListView goals;
    private Goal_adapter adapter;
    private ArrayList<Goal> ArrayItem = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_list);
        new_goal = findViewById(R.id.FB_add_goal);
        goals = findViewById(R.id.LV_goal);
        ArrayItem = new ArrayList<>();

        new_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),add_goal.class);
                startActivity(intent);
            }
        });

        goals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(),goal_detail.class);
                startActivity(intent);
            }
        });

        cargarLista(this);
    }

    public void cargarLista(Context context){
        for(int i = 0 ; i<12;i++){
            String msj = "Goal"+String.valueOf(i);
            ArrayItem.add(new Goal(msj,msj,msj));
        }
        adapter = new Goal_adapter(ArrayItem, context);
        goals.setAdapter(adapter);
    }
}
