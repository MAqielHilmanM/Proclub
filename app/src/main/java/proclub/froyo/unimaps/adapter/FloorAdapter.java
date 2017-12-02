package proclub.froyo.unimaps.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import proclub.froyo.unimaps.Model.FloorModel;
import proclub.froyo.unimaps.R;
import proclub.froyo.unimaps.main.BuildingMapActivity;

/**
 * Created by Naufal on 02/12/2017.
 */

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.ViewHolder> {
    private Context mContex;
    private List<FloorModel> listFloor;

    public FloorAdapter(Context mContex, List<FloorModel> listFloor) {
        this.mContex = mContex;
        this.listFloor = listFloor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FloorModel fm = listFloor.get(position);
        holder.lblFloor.setText(fm.getmFloorName());
        holder.lblRoom.setText(String.valueOf(fm.getmRoom()) + " Rooms");
        holder.lblToliet.setText(String.valueOf(fm.getmToilet()) + " Toilet");
        holder.cvFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContex, BuildingMapActivity.class);
                i.putExtra("name", fm.getmFloorName());
                mContex.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFloor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvFloor;
        private TextView lblFloor, lblRoom, lblToliet;

        public ViewHolder(View itemView) {
            super(itemView);
            cvFloor = itemView.findViewById(R.id.cvFloor);
            lblFloor = itemView.findViewById(R.id.lblFloor);
            lblRoom = itemView.findViewById(R.id.lblRoom);
            lblToliet = itemView.findViewById(R.id.lblToliet);
        }
    }
}
