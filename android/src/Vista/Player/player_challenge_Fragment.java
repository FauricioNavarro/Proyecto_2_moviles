package Vista.Player;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
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
import Vista.login;

public class player_challenge_Fragment extends Fragment {
    private View rootview;
    private ListView challenges;
    private Challenge_adapter adapter;
    private ArrayList<Challenge> ArrayItem = null;
    private SharedPreferences sharedpreferences;
    private EditText filterText;
    private ImageButton exit;

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

        exit = rootview.findViewById(R.id.imageButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    FirebaseAuth.getInstance().signOut();
                }
                Intent i = new Intent(getContext(), login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        filterText = rootview.findViewById(R.id.filter);
        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = filterText.getText().toString().toLowerCase();
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
        filterText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
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
