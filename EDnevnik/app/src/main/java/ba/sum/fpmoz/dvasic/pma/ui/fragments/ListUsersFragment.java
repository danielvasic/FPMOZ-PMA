package ba.sum.fpmoz.dvasic.pma.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.model.Student;
import ba.sum.fpmoz.dvasic.pma.ui.adapters.StudentAdapter;

public class ListUsersFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    StudentAdapter adapter;
    RecyclerView studentListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View userListView = inflater.inflate(R.layout.activity_user_list, container, false);
        this.studentListView = userListView.findViewById(R.id.studentListView);
        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("edenvnik/ucenici");
        this.studentListView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions
                .Builder<Student>()
                .setQuery(this.ref, Student.class).build();
        this.adapter = new StudentAdapter(options);
        this.studentListView.setAdapter(this.adapter);
        return userListView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
