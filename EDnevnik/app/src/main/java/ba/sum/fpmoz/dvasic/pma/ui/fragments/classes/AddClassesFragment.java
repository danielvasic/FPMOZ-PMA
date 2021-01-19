package ba.sum.fpmoz.dvasic.pma.ui.fragments.classes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.model.Classes;

public class AddClassesFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;

    EditText classesName;
    EditText classesUid;
    Button addClassesBtn;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View userAdminView = inflater.inflate(R.layout.fragment_add_classes_admin, container, false);

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("edenvnik/razredi");

        this.classesName = userAdminView.findViewById(R.id.classesNameAddTxt);
        this.classesUid = userAdminView.findViewById(R.id.classesUidAddTxt);

        this.addClassesBtn = userAdminView.findViewById(R.id.addClassesBtn);
        this.addClassesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = classesUid.getText().toString();
                String name = classesName.getText().toString();
                ref.push().setValue(new Classes(uid, name));
                classesUid.setText("");
                classesName.setText("");
            }
        });
        return userAdminView;
    }
}