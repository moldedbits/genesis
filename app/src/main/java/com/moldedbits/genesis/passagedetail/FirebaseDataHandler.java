package com.moldedbits.genesis.passagedetail;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moldedbits.genesis.models.Category;

import timber.log.Timber;

public class FirebaseDataHandler implements FirebaseInteractor {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseCallbacks callbacks;

    public FirebaseDataHandler() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void getDataCategories(final String categories) {
        DatabaseReference reference = firebaseDatabase.getReference(categories);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Category category = snapshot.getValue(Category.class);
                        if (category != null) {
                            Timber.d("Value for key %s is %s", snapshot.getKey(),
                                    category.getName());
                        }
                    }
                    if(callbacks!=null){
                        callbacks.onDataCategoriesFetched(dataSnapshot.getChildren());
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
