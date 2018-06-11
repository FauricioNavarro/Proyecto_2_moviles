package Vista.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mygdx.game.R;

public class achievement_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_detail);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
//            String achiev_admin = extras.getString("achiev_admin");
//            Log.i("PUT EXTRA->", achiev_admin);
        }
    }
}
