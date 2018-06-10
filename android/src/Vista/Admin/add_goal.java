package Vista.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mygdx.game.R;

public class add_goal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
    }

    public void add_goal(View v){
        Toast.makeText(getApplicationContext(),"Add",Toast.LENGTH_SHORT).show();
    }
}
