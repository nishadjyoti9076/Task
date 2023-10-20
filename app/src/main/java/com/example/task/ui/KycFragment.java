package com.example.task.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.util.Global;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class KycFragment extends Fragment implements LocationListener {

    TextView addProof, idProof, lat_long;
    Button submit;
    ImageView im_add, im_id;
    NavController navController;

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationManager locationManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_kyc, container, false);
        findIds(view);
        navController = new NavController(getContext());
        handlePermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        });


        addProof.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 10);

        });

        idProof.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 12);

        });

        getLocation();

        submit.setOnClickListener(view1 -> {
            navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_kycFragment_to_homeFragment2);

        });


        return view;
    }


    private void getLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this::onLocationChanged);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            if (bitmap !=null) {
                im_add.setImageBitmap(bitmap);
                Global.addBitmap=bitmap;
            }
        }else if (requestCode==12){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            if (bitmap !=null) {
                im_id.setImageBitmap(bitmap);
                Global.idBitmap=bitmap;
            }
        }
    }

    private void handlePermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> notGrantedPerms = new ArrayList<>();
            for (String p : permissions) {
                if (getContext().checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED)
                    notGrantedPerms.add(p);
            }
            permissions = notGrantedPerms.toArray(new String[0]);
            if (permissions != null && permissions.length > 0)
                this.requestPermissions(permissions, 701);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 701) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (String p : permissions) {
                    String msg = "";
                    if (getContext().checkSelfPermission(p) == PackageManager.PERMISSION_GRANTED)
                        msg = "Permission Granted for " + p;
                    else
                        msg = "Permission not Granted for " + p;
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void findIds(View view){
        addProof=view.findViewById(R.id.address_proof);
        idProof=view.findViewById(R.id.id_proof);
        submit=view.findViewById(R.id.btn_submit);
        im_add=view.findViewById(R.id.im_add);
        im_id=view.findViewById(R.id.im_idproof);
        lat_long=view.findViewById(R.id.lat_long);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

            lat_long.setText("Lattitude : " + location.getLatitude() + "\n" + "Longitude : " + location.getLongitude());


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}