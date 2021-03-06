package Vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Controlador.Controller;
import Vista.Player.playerActivity;

import com.badlogic.gdx.utils.Timer;
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
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mygdx.game.AndroidLauncher;
import com.mygdx.game.R;

import org.json.JSONException;
import org.json.JSONObject;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import Vista.Admin.dashboard_admin;

public class login extends AppCompatActivity {
    private EditText mail1,password1;
    SignInButton button;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 2;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;
    final static String projextToken = "7a672431d5118e82bf9f7478530f06b5";
    MixpanelAPI mixpanel;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mixpanel = MixpanelAPI.getInstance(this,projextToken);
        Fabric.with(this, new Crashlytics());
        mail1 = findViewById(R.id.txt_email_login);
        password1 = findViewById(R.id.txt_password_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String Password = "sn6wcNDFTNhHEkhMpo0D"+user.getEmail();
                    String res = Controller.getInstance().login(user.getEmail(),Password);
                    JSONObject player = null;
                    try {
                        player = new JSONObject(res);
                        String state = player.getJSONObject("data").getString("type_id");

                        SharedPreferences.Editor sharedPreferences =
                                getSharedPreferences("myref", Context.MODE_PRIVATE).edit();
                        String token = player.getString("token");
                        Intent intent = new Intent(getApplicationContext(),playerActivity.class);
                        Controller.getInstance().setToken(token);
                        sharedPreferences.putString("token",token);
                        sharedPreferences.putString("mail",user.getEmail());
                        sharedPreferences.commit();
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
                signInG();
            }
        });

    }

    public void logIn(View v){
        String mail_aux = mail1.getText().toString();
        String pass_aux = password1.getText().toString();
        //String mail_aux = "admin@mail.com";
        //String pass_aux = "admin";
        //String mail_aux = "mario@mail.com";
        //String pass_aux = "m1234";
        if(!mail_aux.equals("") && !pass_aux.equals("")){
            String res = Controller.getInstance().login(mail_aux,pass_aux);
            try {
                //Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                JSONObject player = new JSONObject(res);
                String state = player.getJSONObject("data").getString("type_id");

                SharedPreferences.Editor sharedPreferences =
                        getSharedPreferences("myref", Context.MODE_PRIVATE).edit();
                String token = player.getString("token");
                //Toast.makeText(getApplicationContext(),"TOKEN->"+token,Toast.LENGTH_LONG).show();
                if(state.equals("1")){
                    Intent intent = new Intent(getApplicationContext(),dashboard_admin.class);
                        Controller.getInstance().setToken(token);
                        sharedPreferences.putString("token",token);
                        sharedPreferences.commit();
                    mixpanel.track("User " + mail_aux + " Logged");
                    startActivity(intent);
                }else if(state.equals("2")){
                    Intent intent = new Intent(getApplicationContext(),playerActivity.class);
                        Controller.getInstance().setToken(token);
                        sharedPreferences.putString("token",token);
                        sharedPreferences.putString("mail",mail_aux);
                    sharedPreferences.commit();
                    mixpanel.track("User " + mail_aux + " Logged");
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


    private void signInG() {
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
                            //Password "sn6wcNDFTNhHEkhMpo0D"+user.getEmail()
                            String Password = "sn6wcNDFTNhHEkhMpo0D"+user.getEmail();
                            Log.d("TAG", user.getEmail() + user.getDisplayName());

                            String out = Controller.getInstance().signin(user.getEmail(),user.getEmail(),Password);

                            String res = Controller.getInstance().login(user.getEmail(),Password);
                            JSONObject player = null;
                            try {
                                player = new JSONObject(res);
                                String state = player.getJSONObject("data").getString("type_id");

                                SharedPreferences.Editor sharedPreferences =
                                        getSharedPreferences("myref", Context.MODE_PRIVATE).edit();
                                String token = player.getString("token");
                                Intent intent = new Intent(getApplicationContext(),playerActivity.class);
                                Controller.getInstance().setToken(token);
                                sharedPreferences.putString("token",token);
                                sharedPreferences.putString("mail",user.getEmail());
                                sharedPreferences.commit();
                                mixpanel.track("User " + user.getEmail() + " Logged with Google");
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }




                            //startActivity(new Intent(login.this,dashboard_admin.class));
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

    @Override
    protected void onDestroy() {
        mixpanel.flush();
        super.onDestroy();
    }
}

