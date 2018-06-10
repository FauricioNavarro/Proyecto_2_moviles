package Vista.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mygdx.game.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Controlador.Achievement_adapter;
import Controlador.Controller;
import Modelo.Achievement;
import Modelo.User;


public class achievementFragment extends Fragment {
    private View rootview;
    private FloatingActionButton new_achievement;
    private ListView achievement;
    private Achievement_adapter adapter;
    private ArrayList<Achievement> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_achievement,container,false);
        achievement = rootview.findViewById(R.id.LV_achievement);
        new_achievement = rootview.findViewById(R.id.FB_add_achivement);
        ArrayItem = new ArrayList<>();
        Log.i("REQUEST->",Controller.getInstance().get_achievs());
        new_achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),add_achievement.class);
                startActivity(intent);
            }
        });

        achievement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        cargarLista(rootview.getContext());
        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void cargarLista(Context context){
        /*
        JSONArray array = Controller.getInstance().getAchievements();
        for(int i = 0;i<array.length();i++){
            try {
                JSONObject object = (JSONObject) array.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                String description = object.getString("description");
                ArrayItem.add(new Achievement(id,name,description));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
        for(int i = 0 ; i<12;i++){
            String msj = "Achievement"+String.valueOf(i);
            ArrayItem.add(new Achievement(i,msj,msj));
        }
        adapter = new Achievement_adapter(ArrayItem, context);
        achievement.setAdapter(adapter);
    }
}
