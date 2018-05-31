package Vista.Admin;

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
import Controlador.User_adapter;
import Modelo.Challenge;
import Modelo.User;


public class challengeFragment extends Fragment {
    private View rootview;
    private FloatingActionButton new_challenge;
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
        rootview = inflater.inflate(R.layout.fragment_challenge,container,false);
        challenges = rootview.findViewById(R.id.LV_challenge);
        new_challenge = rootview.findViewById(R.id.FB_add_challenge);
        ArrayItem = new ArrayList<>();

        new_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),add_challenge.class);
                startActivity(intent);
            }
        });

        challenges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            String msj = "Challenge"+String.valueOf(i);
            ArrayItem.add(new Challenge(msj,msj));
        }
        adapter = new Challenge_adapter(ArrayItem, context);
        challenges.setAdapter(adapter);
    }
}
