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

import Modelo.Goal;
import Modelo.User;

/**
 * Created by fauricio on 08/06/18.
 */

public class Goal_adapter extends BaseAdapter {
    private ArrayList<Goal> arrayItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public Goal_adapter(ArrayList<Goal> arrayItems, Context context) {
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
        View vistaItem = layoutInflater.inflate(R.layout.goal_item, parent, false);

        ImageView imageView = vistaItem.findViewById(R.id.ls_imagen);
        TextView name = vistaItem.findViewById(R.id.txt_name_goal);
        TextView points = vistaItem.findViewById(R.id.txt_point_goal);
        TextView type = vistaItem.findViewById(R.id.txt_type_goal);

        imageView.setImageResource(R.drawable.goal_icon);
        name.setText("Name: "+arrayItems.get(position).getName());
        points.setText("Points: "+arrayItems.get(position).getPoints());
        type.setText("Type: "+arrayItems.get(position).getType());

        return vistaItem;
    }
}
