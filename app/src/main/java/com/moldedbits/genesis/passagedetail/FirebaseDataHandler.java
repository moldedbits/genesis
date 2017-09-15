package com.moldedbits.genesis.passagedetail;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moldedbits.genesis.models.response.PassageDetails;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataHandler implements FirebaseInteractor {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseCallbacks callbacks;

    public FirebaseDataHandler() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void getDataPassageDetails(final String passageDetails, final String key, final int index) {
        final List<PassageDetails> passageDetailList = new ArrayList<>();
        DatabaseReference reference = firebaseDatabase.getReference(passageDetails);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshot1 = dataSnapshot.child(key);
                for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                    PassageDetails passageDetails = dataSnapshot2.getValue(PassageDetails.class);
                    if (passageDetails != null) {
                        passageDetailList.add(passageDetails);
                    }
                }
                if (callbacks != null) {
                    callbacks.onDataPassagesFetched(passageDetailList.get(index));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void setCallBacks(DatabaseCallbacks callBacks) {
        this.callbacks = callBacks;
    }
}
