package ca.ualberta.dorsa.seccam.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.ualberta.dorsa.seccam.entities.User;
import ca.ualberta.dorsa.seccam.entities.SecurityCamera;
import ca.ualberta.dorsa.seccam.singletons.CurrentUser;
import ca.ualberta.dorsa.seccam.singletons.SecurityCameraInUse;

public class DatabaseHelper {public final String TAG = getClass().getSimpleName();
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private Context context;
    private SecurityCameraInUse securityCamera;
    /**
     * The default constructor
     * @param context: the current construct
     */
    public DatabaseHelper(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        this.context = context;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    /**
     * Sets the current FireBaseAuth instance
     * @param firebaseAuth: the current FireBaseAuth instance
     */
    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    /**
     * Gets the current FireBaseDatabase instance
     * @return the current FireBaseDatabase instance
     */
    public FirebaseDatabase getDatabase() {
        return database;
    }

    /**
     * Sets the current FireBaseDatabase instance
     * @param database: the current FireBaseDatabase instance
     */
    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    /**
     * Gets the current FireBaseReference instance
     * @return the current FireBaseReference instance
     */
    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    /**
     * Sets the current FireBaseReference instance
     * @param databaseReference: the current FireBaseReference instance
     */
    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    /**
     * Sets the current context
     * @return the current context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets the context
     * @param context: the current context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Gets the current FirebaseUser instance
     * @return the current FirebaseUser instance
     */
    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    /**
     * Sets the current FirebaseUser instance
     * @param firebaseUser:the current FirebaseUser instance
     */
    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }


    public void getCurrentUser(){

        DatabaseReference rDatabase = FirebaseDatabase.getInstance().getReference("Users/" + firebaseUser.getUid());

        rDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        CurrentUser.setUser(user);
                        Log.v(TAG, "Set User. User ID:" + CurrentUser.getInstance().getuId());
                        setSecurityCameraInUse(CurrentUser.getInstance().getCameraCode());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void  setSecurityCameraInUse(String cameraCode){

        DatabaseReference rDatabase = FirebaseDatabase.getInstance().getReference("SecurityCameras/" + cameraCode);
        rDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        SecurityCamera secCam = dataSnapshot.getValue(SecurityCamera.class);
                        SecurityCameraInUse.setSecurityCameraInUse(secCam);
                        Log.v(TAG, "Set Camera. Camera ID:" + SecurityCameraInUse.getInstance().getCameraCode());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
