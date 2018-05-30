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

import Modelo.Challenge;
import Modelo.Reward;

/**
 * Created by fauricio on 30/05/18.
 */

public class Reward_adapter extends BaseAdapter {
    private ArrayList<Reward> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public Reward_adapter(ArrayList<Reward> arrayItems, Context context) {
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
        View vistaItem = layoutInflater.inflate(R.layout.reward_item, parent, false);

        ImageView imageView = vistaItem.findViewById(R.id.ls_imagen);
        TextView nickname = vistaItem.findViewById(R.id.txt_nickname);
        TextView email = vistaItem.findViewById(R.id.txt_email_2);
        TextView type = vistaItem.findViewById(R.id.txt_type);

        imageView.setImageResource(R.drawable.user_icon);
        nickname.setText("Name: "+arrayItems.get(position).getNombre());
        email.setText("Description: "+arrayItems.get(position).getDescripcion());
        type.setText("Type: "+arrayItems.get(position).getTipo());
        return vistaItem;
    }
}
