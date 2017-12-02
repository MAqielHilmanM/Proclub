package proclub.froyo.unimaps.Model;

/**
 * Created by Naufal on 02/12/2017.
 */

public class FloorModel {
    private String mFloorName;
    private int mRoom;
    private int mToilet;

    public FloorModel(String mFloorName, int mRoom, int mToilet) {
        this.mFloorName = mFloorName;
        this.mRoom = mRoom;
        this.mToilet = mToilet;
    }

    public String getmFloorName() {
        return mFloorName;
    }

    public int getmRoom() {
        return mRoom;
    }

    public int getmToilet() {
        return mToilet;
    }
}
