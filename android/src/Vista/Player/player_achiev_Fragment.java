package Vista.Player;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import Vista.Admin.add_achievement;

public class player_achiev_Fragment extends Fragment {
    private View rootview;
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
        rootview = inflater.inflate(R.layout.fragment_player_achiev_,container,false);
        achievement = rootview.findViewById(R.id.ply_LV_achievement);
        ArrayItem = new ArrayList<>();

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
