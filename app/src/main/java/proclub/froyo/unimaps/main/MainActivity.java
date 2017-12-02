package proclub.froyo.unimaps.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import proclub.froyo.unimaps.R;
import proclub.froyo.unimaps.daos.LocationDao;
import proclub.froyo.unimaps.databinding.ActivityMainBinding;
import proclub.froyo.unimaps.maps.MapsFragment;
import proclub.froyo.unimaps.tools.BaseFirebase;
import proclub.froyo.unimaps.tools.QRCodeScannerActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private FloatingActionButton fabScanQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setVm(this);

        fabScanQRCode = mBinding.fab;

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        Fragment fragmentMaps = new MapsFragment();
        adapter.addFragment(fragmentMaps, "Maps");
        mBinding.vp.setAdapter(adapter);
        mBinding.tabs.setupWithViewPager(mBinding.vp);

        fabScanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QRCodeScannerActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            BaseFirebase a = new BaseFirebase();

            final String[] split = data.getStringExtra("result").split("_");
            a.bInformationBuildingRef(split[0], split[1]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Intent i = new Intent(MainActivity.this, BuildingActivity.class);
                    i.putExtra("univ",split[0]);
                    i.putExtra("building",split[1]);
                    startActivity(i);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}

