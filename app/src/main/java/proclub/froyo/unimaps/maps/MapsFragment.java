package proclub.froyo.unimaps.maps;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import proclub.froyo.unimaps.R;
import proclub.froyo.unimaps.databinding.FragmentMapsBinding;
import proclub.froyo.unimaps.main.BuildingActivity;
import proclub.froyo.unimaps.tools.BaseFirebase;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private static String TAG = "MapsFragment";
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    // location accuracy settings
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    private Context mContext;
    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;

    private BaseFirebase mBaseFirebase;
    private ChildEventListener mChildEventListener;
    private ValueEventListener mValueEventListener;
    private List<ValueEventListener> mListValueEventListener;

    private FragmentMapsBinding mBinding;

    private Marker m;

    private String type, univ, building;

    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_maps, container, false);
        View v = mBinding.getRoot();
        mBinding.setVm(this);
        mBinding.mapView.onCreate(savedInstanceState);
        mBaseFirebase = new BaseFirebase();
        mContext = container.getContext();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mBinding.mapView.onResume();
        mBinding.mapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        moveCamera(new LatLng(-6.973671,107.6281913),12);
    }

    public void moveCamera(LatLng latLng,
                            float zoom) {
        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));

    }

    public void addMark(LatLng latLng, String title, String type, String univ, String building) {
        this.type = type;
        this.univ = univ;
        this.building = building;

        m = mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title));
        m.setTag(0);
        mGoogleMap.setOnMarkerClickListener(this);
    }

    public void removeMark() {
        if(m != null) m.remove();
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getTag().equals(0)) {
            Intent i = new Intent(mContext, BuildingActivity.class);
            i.putExtra("type", type);
            i.putExtra("univ", univ);
            i.putExtra("building", building);
            startActivity(i);
        }
        return false;
    }
}
