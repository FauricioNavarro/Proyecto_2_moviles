package Vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;

import Controlador.Controller;
import Vista.Player.playerActivity;
import com.mygdx.game.AndroidLauncher;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mygdx.game.R;

import org.json.JSONException;
import org.json.JSONObject;

import Vista.Admin.dashboard_admin;

public class login extends AppCompatActivity {
    private EditText mail1,password1;


    SignInButton button;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 2;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail1 = findViewById(R.id.txt_email_login);
        password1 = findViewById(R.id.txt_password_login);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(login.this,dashboard_admin.class));
                }
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(login.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        button = (SignInButton) findViewById(R.id.googleBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }





    public void logIn(View v){
        String mail_aux = mail1.getText().toString();
        String pass_aux = password1.getText().toString();
        //String mail_aux = "admin@mail.com";
        //String pass_aux = "a1234";
        if(!mail_aux.equals("") && !pass_aux.equals("")){
            String res = Controller.getInstance().login(mail_aux,pass_aux);
            try {
                JSONObject player = new JSONObject(res);
                String state = player.getJSONObject("data").getString("type_id");
                Toast.makeText(getApplicationContext(),state,Toast.LENGTH_LONG).show();
                //SharedPreferences.Editor sharedPreferences =
                //        getSharedPreferences("myref", Context.MODE_PRIVATE).edit();
                String token = player.getString("token");
                if(state.equals("1")){
                    Intent intent = new Intent(getApplicationContext(),dashboard_admin.class);
                    //    Controller.getInstance().setToken(token);
                    //    sharedPreferences.putString("token",token);
                    startActivity(intent);
                }else if(state.equals("2")){
                    Intent intent = new Intent(getApplicationContext(),playerActivity.class);
                    //    Controller.getInstance().setToken(token);
                    //    sharedPreferences.putString("token",token);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Log in error",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Empty error",Toast.LENGTH_LONG).show();
        }

    }

    public void signIn(View v){
        Intent  intent = new Intent(getApplicationContext(),signIn.class);
        startActivity(intent);
    }

    public void temp(View v){
        //Intent  intent = new Intent(getApplicationContext(),playerActivity.class);
        Intent  intent = new Intent(getApplicationContext(),AndroidLauncher.class);
        startActivity(intent);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(login.this,"Auth went wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Se puede obtener el correo para saber si es o no admin y redirigir la app.
                            //Log.d("TAG", user.getEmail() + user.getDisplayName());
                            startActivity(new Intent(login.this,dashboard_admin.class));
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}

