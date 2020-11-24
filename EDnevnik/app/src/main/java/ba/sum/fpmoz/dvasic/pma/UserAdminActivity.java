package ba.sum.fpmoz.dvasic.pma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.dvasic.pma.model.Student;

public class UserAdminActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText studentNameInp;
    EditText studentSurnameInp;
    EditText studentUidInp;
    Button addStudentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("edenvnik/ucenici");

        this.studentNameInp = findViewById(R.id.studentNameInp);
        this.studentSurnameInp = findViewById(R.id.studentSurnameInp);
        this.studentUidInp = findViewById(R.id.studentUid);
        this.addStudentBtn = findViewById(R.id.addStudentBtn);

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
                        getApplicationContext(),
                        "Uspješno ste dodali učenika.", Toast.LENGTH_LONG).show();
            }
        });


    }
}
