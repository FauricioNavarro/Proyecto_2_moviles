package Vista.Player;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mygdx.game.AndroidLauncher;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private float lat, lon;
    private String nm;
    private static final int radio = 20;
    private LatLng sydney;
    final static String projextToken = "7a672431d5118e82bf9f7478530f06b5";
    MixpanelAPI mixpanel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mixpanel = MixpanelAPI.getInstance(this,projextToken);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle b = getIntent().getExtras();
        lat = Float.valueOf(b.getString("lat"));
        lon = Float.valueOf(b.getString("lon"));
        nm = b.getString("nombre");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        float[] results = new float[1];
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title(nm));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15.5f));
        CircleOptions Cotp = new CircleOptions()
                .center(sydney)
                .radius(radio)
                .strokeColor(Color.RED)
                .clickable(true);
        Circle circle = mMap.addCircle(Cotp);
        //Verifica que clickeo el circulo dentro del rango que se puede acceder al reto
        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle circle) {
                float[] results = new float[1];
                Location.distanceBetween(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude(),sydney.latitude,sydney.longitude,results);
                if (results[0]<= radio){
                    mixpanel.track("User accessed to the challenge");
                    Intent  intent = new Intent(getApplicationContext(),AndroidLauncher.class);
                    startActivity(intent);
                }
                else {
                    mixpanel.track("User trying to access the challenge outside the range");
                    Toast.makeText(getApplicationContext(),"No se encuentra dentro del radio para iniciar el reto",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


    }

    @Override
    protected void onDestroy() {
        mixpanel.flush();
        super.onDestroy();
    }



}
