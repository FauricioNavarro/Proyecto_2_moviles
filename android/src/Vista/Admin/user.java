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

import Controlador.User_adapter;
import Modelo.User;


public class user extends Fragment {
    private View rootview;
    private FloatingActionButton new_user;
    private ListView users;
    private User_adapter adapter;
    private ArrayList<User> ArrayItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_user,container,false);
        users = rootview.findViewById(R.id.LV_users);
        ArrayItem = new ArrayList<>();

        users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),user_detail.class);
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
            String msj = "User"+String.valueOf(i);
            ArrayItem.add(new User(msj,msj));
        }
        adapter = new User_adapter(ArrayItem, context);
        users.setAdapter(adapter);
    }
}
