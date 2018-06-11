package Vista.Admin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mygdx.game.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Controlador.Controller;
import Controlador.Goal_adapter;
import Controlador.User_adapter;
import Modelo.Goal;
import Modelo.User;

public class goal_list extends AppCompatActivity {
    private FloatingActionButton new_goal;
    private ListView goals;
    private Goal_adapter adapter;
    private ArrayList<Goal> ArrayItem = null;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_list);
        new_goal = findViewById(R.id.FB_add_goal);
        goals = findViewById(R.id.LV_goal);
        ArrayItem = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            id = extras.getString("id_challenge");
            String goals = Controller.getInstance().goals_challenge(id);
            Log.i("PUT EXTRA->", id);
            Log.i("REquest->", goals);

        }
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
                Goal goal_actual = ArrayItem.get(i);
                int id = goal_actual.getId();
                String name = goal_actual.getName();
                String points = goal_actual.getPoints();
                String type_id = goal_actual.getType();
                String chall_id = goal_actual.getChallenge_id();
                String lat = goal_actual.getLatitude();
                String lon = goal_actual.getLongitude();
                Log.i("GOAL X ->",name+","+points+","+type_id+","+chall_id+","+lat+","+lon);
                Intent intent= new Intent(getApplicationContext(),goal_detail.class);
                intent.putExtra("id_dt",id);
                intent.putExtra("name_dt",name);
                intent.putExtra("points_dt",points);
                intent.putExtra("type_id_dt",type_id);
                intent.putExtra("chall_id_dt",chall_id);
                intent.putExtra("lat_dt",lat);
                intent.putExtra("lon_dt",lon);
                startActivity(intent);
            }
        });

        cargarLista(this);
    }

    public void cargarLista(Context context){
        String goals_aux = Controller.getInstance().goals_challenge(id);
        JSONArray array = null;
        try {
            array = new JSONArray(goals_aux);
            for(int i = 0;i<array.length();i++){
                try {
                    JSONObject object = (JSONObject) array.getJSONObject(i);
                    int id = object.getInt("id");
                    String name = object.getString("name");
                    String points = object.getString("points");
                    String type_id = object.getString("type_id");
                    String chall_id = object.getString("challenge_id");
                    String lat = object.getString("latitud");
                    String lon = object.getString("longitud");
                    ArrayItem.add(new Goal(id,name,points,type_id,lat,lon,chall_id));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new Goal_adapter(ArrayItem, context);
        goals.setAdapter(adapter);
    }
}
