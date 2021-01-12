package ba.sum.fpmoz.dvasic.pma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import ba.sum.fpmoz.dvasic.pma.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.dvasic.pma.ui.fragments.users.AddUsersFragment;
import ba.sum.fpmoz.dvasic.pma.ui.fragments.users.ListUsersFragment;

public class TabbedUserAdminActivity extends AppCompatActivity {

    TabbedAdapter adapter;
    TabLayout layout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_student);
        setTitle("Administracija korisnika");
        this.layout = findViewById(R.id.userTabLayout);
        this.pager = findViewById(R.id.userViewPager);

        this.adapter = new TabbedAdapter(getSupportFragmentManager(), 1);
        this.adapter.addFragment(
                new ListUsersFragment(), "Prikaz svih učenika"
        );

        this.adapter.addFragment(
                new AddUsersFragment(), "Dodavanje novih učenika"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);
    }
}
