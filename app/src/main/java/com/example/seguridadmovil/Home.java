package com.example.seguridadmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    Button gps;
    TextView ubicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ubicacion = (TextView)findViewById(R.id.ubicacion_textView);
        gps =(Button)findViewById(R.id.gps_button);

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGPS();
            }
        });

        int permiso = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permiso == PackageManager.PERMISSION_GRANTED){


                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Mostrar diálogo explicativo
                } else {
                    // Solicitar permiso
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},
                            1);
                }
            }

    }
    private void getGPS(){
        Log.d("espejo", "onLocationChanged:1");
        LocationManager locMan = (LocationManager) Home.this.getSystemService(Context.LOCATION_SERVICE);

        Log.d("espejo", "onLocationChanged:2" );
        LocationListener locLis = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("espejo", "onLocationChanged:" +location.getLatitude());
                ubicacion.setText(""+location.getLatitude()+"   "+location.getLongitude());
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) { }
            @Override
            public void onProviderEnabled(String provider) { }
            @Override
            public void onProviderDisabled(String provider) { }
        };




        Boolean permiso = ContextCompat.checkSelfPermission(Home.this,
                Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED;
        if (permiso) {
            assert locMan != null;
            locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locLis);
        }else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }










    }
}
