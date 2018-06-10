package Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mygdx.game.R;

import Controlador.Controller;
import Controlador.Dao_api;

public class signIn extends AppCompatActivity {
    private EditText name,mail,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name = findViewById(R.id.txt_nickname_sign);
        mail = findViewById(R.id.txt_email_sign);
        password = findViewById(R.id.txt_password_sign);
    }

    public void signIn(View v){
        String name_aux = name.getText().toString();
        String mail_aux = mail.getText().toString();
        String pass_aux = password.getText().toString();
        if(!name_aux.equals("") && !mail_aux.equals("") && !pass_aux.equals("")){
            String out = Controller.getInstance().signin(name_aux,mail_aux,pass_aux);
            Toast.makeText(getApplicationContext(),out,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Sign in error",Toast.LENGTH_SHORT).show();
        }

    }
}
