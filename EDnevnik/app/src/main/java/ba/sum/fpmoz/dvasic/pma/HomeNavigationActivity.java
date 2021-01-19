package ba.sum.fpmoz.dvasic.pma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeNavigationActivity extends AppCompatActivity {

    private ImageButton userAdminBtn;
    private ImageButton classesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        setTitle("Dobrodošli u administraciju");
        this.userAdminBtn = findViewById(R.id.usersBtn);
        this.userAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabbedUserAdminActivity.class);
                startActivity(i);
            }
        });

        this.classesBtn = findViewById(R.id.classesBtn);
        this.classesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabbedClassesActivity.class);
                startActivity(i);
            }
        });
    }
}