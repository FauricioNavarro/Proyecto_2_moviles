package Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygdx.game.R;

import java.util.ArrayList;

import Modelo.User;

/**
 * Created by fauricio on 29/05/18.
 */

public class User_adapter extends BaseAdapter {
    private ArrayList<User> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public User_adapter(ArrayList<User> arrayItems, Context context) {
        this.arrayItems = arrayItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayItems.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vistaItem = layoutInflater.inflate(R.layout.user_item, parent, false);

        ImageView imageView = vistaItem.findViewById(R.id.ls_imagen);
        TextView nickname = vistaItem.findViewById(R.id.txt_nickname);
        TextView email = vistaItem.findViewById(R.id.txt_email_2);

        imageView.setImageResource(R.drawable.user_icon);
        nickname.setText("Nickname: "+arrayItems.get(position).getNickname());
        email.setText("Email: "+arrayItems.get(position).getEmail());
        return vistaItem;
    }
}
