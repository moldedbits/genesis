package com.moldedbits.genesis.category;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moldedbits.genesis.models.Category;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

class CategoryPresenter implements CategoryContract.IPresenter {

    private final CategoryContract.IView view;

    CategoryPresenter(CategoryContract.IView view) {
        this.view = view;
    }

    @Override
    public void getCategories() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categories");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Category> categories = new ArrayList<>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category = snapshot.getValue(Category.class);
                    category.setKey(snapshot.getKey());
                    categories.add(category);
                    }
                view.showCategories(categories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Timber.w("Failed to read value.", databaseError.toException());
            }
        });
    }
}
