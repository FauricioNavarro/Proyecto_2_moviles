package Vista.Admin;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mygdx.game.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Controlador.Achievement_adapter;
import Controlador.Controller;
import Modelo.Achievement;
import Modelo.User;
import Vista.login;


public class achievementFragment extends Fragment {
    private View rootview;
    private FloatingActionButton new_achievement;
    private ListView achievement;
    private Achievement_adapter adapter;
    private ArrayList<Achievement> ArrayItem = null;
    private EditText filterText;
    private ImageButton exit;
    final static String projextToken = "7a672431d5118e82bf9f7478530f06b5";
    MixpanelAPI mixpanel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_achievement,container,false);
        achievement = rootview.findViewById(R.id.LV_achievement);
        mixpanel = MixpanelAPI.getInstance(getContext(),projextToken);
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
                Achievement achievement = ArrayItem.get(i);
                Intent intent = new Intent(rootview.getContext(),achievement_detail.class);
                Log.i("id achiev->",String.valueOf(achievement.getId()));
                intent.putExtra("achiev_admin",achievement.getId());
                startActivity(intent);
            }
        });

        exit = rootview.findViewById(R.id.imageButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    mixpanel.track("Logout Google Acc");
                    FirebaseAuth.getInstance().signOut();

                }
                else{
                    mixpanel.track("Logout Common Acc");
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
        }
        adapter = new Achievement_adapter(ArrayItem, context);
        achievement.setAdapter(adapter);
    }
}
