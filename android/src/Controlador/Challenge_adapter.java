package Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.mygdx.game.R;

import java.util.ArrayList;

import Modelo.Challenge;
import Modelo.User;

/**
 * Created by fauricio on 30/05/18.
 */

public class Challenge_adapter extends BaseAdapter implements Filterable {
    private ArrayList<Challenge> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Challenge> listRetos = null;

    public Challenge_adapter(ArrayList<Challenge> arrayItems, Context context) {
        this.arrayItems = arrayItems;
        this.listRetos = new ArrayList<>();
        this.listRetos.addAll(arrayItems);
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
        TextView nickname = vistaItem.findViewById(R.id.txt_nickname_user);
        TextView email = vistaItem.findViewById(R.id.txt_email_user);

        imageView.setImageResource(R.drawable.challenge_icon_1);
        nickname.setText("Name: "+arrayItems.get(position).getNombre());
        email.setText("Description: "+arrayItems.get(position).getDescripcion());
        return vistaItem;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase();
        arrayItems.clear();
        if (charText.length() == 0) {
            arrayItems.addAll(listRetos);
        }
        else
        {
            for (Challenge wp : listRetos)
            {
                if (wp.getNombre().toLowerCase().contains(charText.toLowerCase()))
                {
                    arrayItems.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
