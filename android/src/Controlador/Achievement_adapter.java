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

import Modelo.Achievement;

/**
 * Created by fauricio on 30/05/18.
 */

public class Achievement_adapter extends BaseAdapter implements Filterable {
    private ArrayList<Achievement> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Achievement> listLogros = null;

    public Achievement_adapter(ArrayList<Achievement> arrayItems, Context context) {
        this.arrayItems = arrayItems;
        this.listLogros = new ArrayList<>();
        this.listLogros.addAll(arrayItems);
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
        View vistaItem = layoutInflater.inflate(R.layout.achievement_item, parent, false);

        ImageView imageView = vistaItem.findViewById(R.id.ls_imagen);
        TextView nickname = vistaItem.findViewById(R.id.txt_name_achievement);
        TextView email = vistaItem.findViewById(R.id.txt_description_achievement);

        imageView.setImageResource(R.drawable.achievement_icon);
        nickname.setText("Name: "+arrayItems.get(position).getName());
        email.setText("Description: "+arrayItems.get(position).getDescription());

        return vistaItem;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase();
        arrayItems.clear();
        if (charText.length() == 0) {
            arrayItems.addAll(listLogros);
        }
        else
        {
            for (Achievement wp : listLogros)
            {
                if (wp.getName().toLowerCase().contains(charText.toLowerCase()))
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
