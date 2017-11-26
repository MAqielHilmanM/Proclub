package proclub.froyo.unimaps.tools;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by MAqielHilmanM on 11/26/2017.
 */

public class BaseFirebase {
    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference bRef = firebaseDatabase.getReference();
    public DatabaseReference bLocationRef = firebaseDatabase.getReference("information");
    public DatabaseReference bInformationRef = firebaseDatabase.getReference("location");
    public DatabaseReference bLocationBuildingRef = bLocationRef.child("building");
    public DatabaseReference bLocationUniversityRef = bLocationRef.child("university");

    public DatabaseReference bLocationBuildingRef(String id){
        return bLocationBuildingRef.child(id);
    }
    public DatabaseReference bLocationBuildingRef(String parent,String child){
        return bLocationBuildingRef.child(parent).child(child);
    }
    public DatabaseReference bLocationUniversityRef(String id){
        return bLocationUniversityRef.child(id);
    }



}
