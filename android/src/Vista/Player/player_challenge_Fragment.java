package Vista.Player;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import Modelo.Challenge;
import Vista.Admin.add_challenge;
import Vista.Admin.goal_list;

public class player_challenge_Fragment extends Fragment {
    private View rootview;
    private ListView challenges;
    private Challenge_adapter adapter;
    private ArrayList<Challenge> ArrayItem = null;
    private SharedPreferences sharedpreferences;
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
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("myref", Context.MODE_PRIVATE);
        challenges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Challenge challenge = ArrayItem.get(i);
                Intent intent = new Intent(getContext(),goal_listply_Activity.class);
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
        //Log.i("ChallengesXuser",array.toString());
        for(int i = 0;i<array.length();i++){
            try {
                JSONObject object = (JSONObject) array.getJSONObject(i);
                int id = object.getInt("challenge_id");
                String challenge = Controller.getInstance().get_single_challenge(String.valueOf(id));
                //Log.i("ChallengesXuser->",challenge);
                JSONObject jsonObject = new JSONObject(challenge);
                int id_aux = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String des = jsonObject.getString("description");
                ArrayItem.add(new Challenge(id_aux,name,des));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter = new Challenge_adapter(ArrayItem, context);
        challenges.setAdapter(adapter);
    }
}
