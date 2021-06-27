package com.ahwajkafabi.pencatatandatalimbah.Helpers;

import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ahwajkafabi.pencatatandatalimbah.CekhlistActivity;
import com.ahwajkafabi.pencatatandatalimbah.Data.Cekhlist;
import com.ahwajkafabi.pencatatandatalimbah.Data.MyAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static com.ahwajkafabi.pencatatandatalimbah.Helpers.Utils.DataCache;

public class FirebaseCRUDHelper {

    /**
     * This method will allow us post into firebase realtime database
     * @param a
     * @param mDatabaseRef
     * @param pb
     * @param cekhlist
     */
    public void insert(final AppCompatActivity a,
                       final DatabaseReference mDatabaseRef,
                       final ProgressBar pb, final Cekhlist cekhlist) {
        //check if they have passed us a valid scientist. If so then return false.
        if (cekhlist == null) {
            Utils.showInfoDialog(a,"VALIDATION FAILED","Cekhlist is null");
            return;
        } else {
            //otherwise try to push data to firebase database.
                Utils.showProgressBar(pb);
                //push data to FirebaseDatabase. Table or Child called Scientist will be
                // created.
                mDatabaseRef.child("Cekhlist").push().setValue(cekhlist).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Utils.hideProgressBar(pb);

                        if(task.isSuccessful()){
                            Utils.openActivity(a, CekhlistActivity.class);
                            Utils.show(a," DATA BERHASIL");
                        }else{
                            Utils.showInfoDialog(a,"DATA GAGAL",task.getException().
                            getMessage());
                        }
                    }

                });
        }
    }

    /**
     * This method will allow us retrieve from firebase realtime database
     * @param a
     * @param db
     * @param pb
     * @param rv
     * @param adapter
     */
    public void select(final AppCompatActivity a, DatabaseReference db,
                       final ProgressBar pb,
                       final RecyclerView rv, MyAdapter adapter) {
        Utils.showProgressBar(pb);

        db.child("Cekhlists").addValueEventListener(new ValueEventListener() {
        @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            DataCache.clear();
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        //Now get Scientist Objects and populate our arraylist.
                        Cekhlist cekhlist = ds.getValue(Cekhlist.class);
                        cekhlist.setKey(ds.getKey());
                        DataCache.add(cekhlist);
                    }

                    adapter.notifyDataSetChanged();

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            Utils.hideProgressBar(pb);
                            rv.smoothScrollToPosition(DataCache.size());
                        }
                    });
                }else {
                    Utils.show(a,"No more item found");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE Cekhlist", databaseError.getMessage());
                Utils.hideProgressBar(pb);
                Utils.showInfoDialog(a,"CANCELLED",databaseError.getMessage());
            }
        });
    }

    /**
     * This method will allow us update existing data in firebase realtime database
     * @param a
     * @param mDatabaseRef
     * @param pb
     * @param updatedCekhlist
     */
    public void update(final AppCompatActivity a,
                       final DatabaseReference mDatabaseRef,
                       final ProgressBar pb,
                       final Cekhlist updatedCekhlist) {

			if(updatedCekhlist == null){
				Utils.showInfoDialog(a,"VALIDATION FAILED","Cekhlist is null");
				return;
			}

        Utils.showProgressBar(pb);
            mDatabaseRef.child("Cekhlist").child(updatedCekhlist.getKey()).setValue(
                updatedCekhlist)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Utils.hideProgressBar(pb);

                            if(task.isSuccessful()){
                                Utils.show(a, updatedCekhlist.getName() + " Update Successful.");
                                Utils.openActivity(a, CekhlistActivity.class);
                            }else {
                                Utils.showInfoDialog(a,"UNSUCCESSFUL",task.getException().
                                getMessage());
                            }
                        }
                    });
    }


    /**
     * This method will allow us to delete from firebase realtime database
     * @param a
     * @param mDatabaseRef
     * @param pb
     * @param selectedCekhlist
     */
    public void delete(final AppCompatActivity a, final DatabaseReference mDatabaseRef,
                       final ProgressBar pb, final Cekhlist selectedCekhlist) {
        Utils.showProgressBar(pb);
        final String selectedCekhlistKey = selectedCekhlist.getKey();
        mDatabaseRef.child("Cekhlist").child(selectedCekhlistKey).removeValue().
        addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Utils.hideProgressBar(pb);

                if(task.isSuccessful()){
                    Utils.show(a, selectedCekhlist.getName() + " DATA BERHASIL.");
                    Utils.openActivity(a, CekhlistActivity.class);
                }else{
                    Utils.showInfoDialog(a,"DATA GAGAL",task.getException().getMessage());
                }
            }
        });

    }
}
//end
