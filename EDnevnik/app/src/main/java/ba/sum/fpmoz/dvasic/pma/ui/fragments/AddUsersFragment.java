package ba.sum.fpmoz.dvasic.pma.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.model.Student;

public class AddUsersFragment extends Fragment {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText studentNameInp;
    EditText studentSurnameInp;
    EditText studentUidInp;
    Button addStudentBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View userAdminView = inflater.inflate(R.layout.activity_user_admin, container, false);

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("edenvnik/ucenici");

        this.studentNameInp = userAdminView.findViewById(R.id.studentNameInp);
        this.studentSurnameInp = userAdminView.findViewById(R.id.studentSurnameInp);
        this.studentUidInp = userAdminView.findViewById(R.id.studentUid);
        this.addStudentBtn = userAdminView.findViewById(R.id.addStudentBtn);

        this.addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = studentNameInp.getText().toString();
                String studentSurname = studentSurnameInp.getText().toString();
                String studentUid = studentUidInp.getText().toString();
                ref.push().setValue(
                        new Student(studentUid, studentName, studentSurname)
                );
                studentNameInp.setText("");
                studentSurnameInp.setText("");
                studentUidInp.setText("");
                Toast.makeText(
                        userAdminView.getContext(),
                        "Uspješno ste dodali učenika.", Toast.LENGTH_LONG).show();
            }
        });

        return userAdminView;
    }
}
