package proclub.froyo.unimaps.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import proclub.froyo.unimaps.R;
import proclub.froyo.unimaps.daos.LocationDao;
import proclub.froyo.unimaps.databinding.ActivityMainBinding;
import proclub.froyo.unimaps.maps.MapsFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setVm(this);

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        Fragment fragmentMaps = new MapsFragment();
        adapter.addFragment(fragmentMaps, "Maps");
        mBinding.vp.setAdapter(adapter);
        mBinding.tabs.setupWithViewPager(mBinding.vp);

    }


}

