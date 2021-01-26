package ba.sum.fpmoz.dvasic.pma.messages;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ba.sum.fpmoz.dvasic.pma.model.Messages;


public class MessagesService extends Application {
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    public void onCreate() {
        super.onCreate();
        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("edenvnik/notifikacije");

        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    Messages m = null;
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        m = ds.getValue(Messages.class);
                    }
                    MessagesController.notify(m, getApplicationContext());
                    ref.removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
