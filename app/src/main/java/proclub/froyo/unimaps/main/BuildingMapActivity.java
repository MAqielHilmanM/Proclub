package proclub.froyo.unimaps.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import proclub.froyo.unimaps.R;
import proclub.froyo.unimaps.databinding.ActivityBuildingMapBinding;

public class BuildingMapActivity extends AppCompatActivity {

    private ActivityBuildingMapBinding mBinding;

    private ImageView imgMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_map);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_building_map);
        mBinding.setVm(this);

        imgMap = mBinding.imgMap;

        Intent data = getIntent();

        String name = data.getStringExtra("name");
        if (name.equals("IT.01")) {
            imgMap.setImageDrawable(getResources().getDrawable(R.drawable.fit_01));
        } else if (name.equals("IT.02")) {
            imgMap.setImageDrawable(getResources().getDrawable(R.drawable.fit_02));
        } else {
            imgMap.setImageDrawable(getResources().getDrawable(R.drawable.fit_03));
        }
    }
}
