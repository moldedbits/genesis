package com.moldedbits.genesis;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moldedbits.genesis.models.Category;

import timber.log.Timber;


public class ExampleAppActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_app);

        testFireBase();
    }

    private void testFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categories");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category = snapshot.getValue(Category.class);
                    if (category != null) {
                        Timber.d("Value for key %s is %s", snapshot.getKey(),
                                category.getName());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Timber.w("Failed to read value.", databaseError.toException());
            }
        });
    }
}
