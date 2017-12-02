package proclub.froyo.unimaps.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import proclub.froyo.unimaps.Model.FloorModel;
import proclub.froyo.unimaps.R;
import proclub.froyo.unimaps.adapter.FloorAdapter;
import proclub.froyo.unimaps.databinding.ActivityBuildingBinding;
import proclub.froyo.unimaps.databinding.ActivityMainBinding;
import proclub.froyo.unimaps.tools.BaseFirebase;

public class BuildingActivity extends AppCompatActivity {

    private RecyclerView rvFloor;
    private TextView lblNotAvailable;
    private FloorAdapter mAdapter;
    private List<FloorModel> listFloor;

    private ActivityBuildingBinding mBinding;

    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_building);
        mBinding.setVm(this);

        rvFloor = mBinding.rvFloor;
        lblNotAvailable = mBinding.lblNotAvailable;

        listFloor = new ArrayList<>();

        data = getIntent();
        String univ = data.getStringExtra("univ");
        String building = data.getStringExtra("building");

        BaseFirebase bf = new BaseFirebase();
        bf.bInformationFloorRef(univ, building).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listFloor.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String floorName = ds.child("Name").getValue(String.class);
                        int rooms = ds.child("Rooms").getValue(Integer.class);
                        int toilet = ds.child("Toilet").getValue(Integer.class);
                        FloorModel fm = new FloorModel(floorName, rooms, toilet);
                        listFloor.add(fm);
                    }

                    mAdapter = new FloorAdapter(listFloor);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BuildingActivity.this);
                    rvFloor.setLayoutManager(layoutManager);
                    rvFloor.setAdapter(mAdapter);

                    mAdapter.notifyDataSetChanged();
                } else {
                    rvFloor.setVisibility(View.GONE);
                    lblNotAvailable.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
