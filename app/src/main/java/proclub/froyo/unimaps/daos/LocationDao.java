package proclub.froyo.unimaps.daos;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Created by MAqielHilmanM on 11/26/2017.
 */

@IgnoreExtraProperties
public class LocationDao {
    private Double longitude,latitude;

    public LocationDao(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public HashMap<String , Object> toMap(){
        HashMap<String , Object> map = new HashMap<>();
        map.put("latitude",latitude);
        map.put("longtitude",longitude);
        return map;
    }

    public LocationDao() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
