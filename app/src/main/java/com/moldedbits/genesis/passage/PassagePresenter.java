package com.moldedbits.genesis.passage;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moldedbits.genesis.models.Category;
import com.moldedbits.genesis.models.Passage;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

class PassagePresenter implements PassageContract.IPresenter {

    private final PassageContract.IView view;

    PassagePresenter(PassageContract.IView view) {
        this.view = view;
    }

    @Override
    public void getPassages(String passageKey) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("passages").child(passageKey);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Passage> passages = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                }
                view.showPassages(passages);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Timber.w("Failed to read value.", databaseError.toException());
            }
        });
    }
}
