package ba.sum.fpmoz.dvasic.pma.ui.fragments.classes;

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
import ba.sum.fpmoz.dvasic.pma.model.Classes;
import ba.sum.fpmoz.dvasic.pma.ui.adapters.ClassesAdapter;

public class ListClassesFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    ClassesAdapter adapter;
    RecyclerView classesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View classesListView = inflater.inflate(R.layout.recycled_classes_admin, container, false);
        this.classesRecyclerView = classesListView.findViewById(R.id.classesListView);
        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("edenvnik/razredi");
        this.classesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Classes> options = new FirebaseRecyclerOptions
                .Builder<Classes>()
                .setQuery(this.ref, Classes.class).build();

        this.adapter = new ClassesAdapter(options);
        this.classesRecyclerView.setAdapter(this.adapter);
        return classesListView;
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
