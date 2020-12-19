package ba.sum.fpmoz.dvasic.pma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeNavigationActivity extends AppCompatActivity {

    private ImageButton userAdminBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        this.userAdminBtn = findViewById(R.id.usersBtn);
        this.userAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabbedUserAdminActivity.class);
                startActivity(i);
            }
        });
    }
}