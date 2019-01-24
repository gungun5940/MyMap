package com.surinthorn.mymap;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng pt1, pt2, pt3, pt4;
    private Marker mPt1, mPt2, mPt3, mPt4;
    private LatLngBounds bounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;

        pt1 = new LatLng(18.234376,99.487508);
        pt2 = new LatLng(18.235181,99.486274);
        pt3 = new LatLng(18.233469,99.486135);
        pt4 = new LatLng(18.234356,99.488120);

        mPt1 = mMap.addMarker(new MarkerOptions()
                .position(pt1)
                .title("LPRU")
                .snippet("มหาวิทยาลัยราชภัฎลำปาง")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mPt2 = mMap.addMarker(new MarkerOptions()
                .position(pt2)
                .title("ComCenter")
                .snippet("ศูนย์คอมพิวเตอร์")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mPt3 = mMap.addMarker(new MarkerOptions()
                .position(pt3)
                .title("SCI")
                .snippet("คณะวิทยาศาสตร์")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mPt4 = mMap.addMarker(new MarkerOptions()
                .position(pt4)
                .title("Human")
                .snippet("คณะมนุษยศาสตร์")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        bounds = new LatLngBounds.Builder()
                .include(pt1)
                .include(pt2)
                .include(pt3)
                .include(pt4)
                .build();

        final View mapview = getSupportFragmentManager()
                .findFragmentById(R.id.map).getView();
        if (mapview.getViewTreeObserver().isAlive()) {
            mapview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation")
                @SuppressLint("NewApi")
                @Override
                public void onGlobalLayout() {
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(pt1)
                            .include(pt2)
                            .include(pt3)
                            .include(pt4)
                            .build();
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapview.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                    }else {
                        mapview.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                    }
                    mMap.moveCamera(
                            CameraUpdateFactory.newLatLngBounds(bounds,50)
                    );
                }
            });
        }
        // Add a marker in Sydney and move the camera
//        LatLng Home = new LatLng(18.320055,  99.490752);
//        mMap.addMarker(new MarkerOptions()
//                .position(Home)
//                .title("My Home")
//                .snippet("My Dog")
////                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marill)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(Home));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Home,17));

//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(Home)
//                .zoom(17)
//                .tilt(60)
//                .build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
