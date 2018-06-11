package Vista.Admin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import Controlador.Challenge_adapter;
import Controlador.Controller;
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
                Challenge challenge = ArrayItem.get(i);
                Intent intent = new Intent(getContext(),goal_list.class);
                Log.i("Challenges id->",String.valueOf(challenge.getId()));
                intent.putExtra("id_challenge",String.valueOf(challenge.getId()));
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
        JSONArray array = Controller.getInstance().getChallenges();
        for(int i = 0;i<array.length();i++){
            try {
                JSONObject object = (JSONObject) array.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                String des = object.getString("description");
                ArrayItem.add(new Challenge(id,name,des));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter = new Challenge_adapter(ArrayItem, context);
        challenges.setAdapter(adapter);
    }
}
