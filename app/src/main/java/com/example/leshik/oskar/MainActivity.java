package com.example.leshik.oskar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    public static final int PERMITIONS_REQUEST_CODE = 101;

    SupportMapFragment fragment;
    GoogleMap map;

    TextView linkTextView;
    TextView numberKyivstarTextView;
    TextView numberHomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        linkTextView = (TextView) findViewById(R.id.link_Oskar);
        numberKyivstarTextView = (TextView) findViewById(R.id.number_kiev);
        numberHomeTextView = (TextView) findViewById(R.id.number_home);

        linkTextView.setOnClickListener(this);
        numberKyivstarTextView.setOnClickListener(this);
        numberHomeTextView.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng oskar = new LatLng(49.449917, 32.047750);
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        map.addMarker(new MarkerOptions().position(oskar).title("Oskar bar"));

        CameraPosition pos = CameraPosition.builder().target(oskar).zoom(15).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(pos);
        map.moveCamera(cameraUpdate);
    }

    @Override
    public void onClick(View view) {
        Intent contactsIntent;
        switch (view.getId()) {
            case R.id.link_Oskar:
                contactsIntent = new Intent(Intent.ACTION_VIEW);
                contactsIntent.setData(Uri.parse("https://vk.com/oskar_bierstube"));
                startActivity(contactsIntent);
                break;
            case R.id.number_kiev:
                contactsIntent = new Intent(Intent.ACTION_CALL);
                contactsIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.phone_number_kiev)));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMITIONS_REQUEST_CODE);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(contactsIntent);
                    }
                } else {
                    startActivity(contactsIntent);
                }
                break;
            case R.id.number_home:
                contactsIntent = new Intent(Intent.ACTION_CALL);
                contactsIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.phone_number_home)));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMITIONS_REQUEST_CODE);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(contactsIntent);
                    }
                } else {
                    startActivity(contactsIntent);
                }
                break;
        }
    }
}
