package com.moldedbits.genesis.passage;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moldedbits.languagetools.models.Passage;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

class PassagePresenter implements PassageContract.IPresenter {

    private final PassageContract.IView view;

    PassagePresenter(PassageContract.IView view) {
        this.view = view;
    }

    @Override
    public void getPassages(final String passageKey) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("passages");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshot1 = dataSnapshot.child(passageKey);
                List<Passage> passages = new ArrayList<>();

                   for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                    Passage passage = dataSnapshot2.getValue(Passage.class);
                    if (passage != null) {
                        passages.add(passage);
                    }
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
