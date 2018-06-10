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

import Controlador.Challenge_adapter;
import Modelo.Challenge;
import Vista.Admin.add_challenge;
import Vista.Admin.goal_list;

public class player_challenge_Fragment extends Fragment {
    private View rootview;
    private ListView challenges;
    private Challenge_adapter adapter;
    private ArrayList<Challenge> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_player_challenge_,container,false);
        challenges = rootview.findViewById(R.id.ply_LV_challenge);
        ArrayItem = new ArrayList<>();

        challenges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),goal_listply_Activity.class);
                startActivity(intent);
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
            String msj = "Challenge"+String.valueOf(i);
            ArrayItem.add(new Challenge(i,msj,msj));
        }
        adapter = new Challenge_adapter(ArrayItem, context);
        challenges.setAdapter(adapter);
    }
}
