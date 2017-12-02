package proclub.froyo.unimaps.maps;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import proclub.froyo.unimaps.R;
import proclub.froyo.unimaps.databinding.FragmentMapsBinding;
import proclub.froyo.unimaps.tools.BaseFirebase;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {


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
                            int zoom) {
        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));
    }
}
