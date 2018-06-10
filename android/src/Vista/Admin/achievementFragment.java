package Vista.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mygdx.game.R;

import java.util.ArrayList;

import Controlador.Achievement_adapter;
import Modelo.Achievement;


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
        for(int i = 0 ; i<12;i++){
            String msj = "Achievement"+String.valueOf(i);
            ArrayItem.add(new Achievement(msj,msj));
        }
        adapter = new Achievement_adapter(ArrayItem, context);
        achievement.setAdapter(adapter);
    }
}
