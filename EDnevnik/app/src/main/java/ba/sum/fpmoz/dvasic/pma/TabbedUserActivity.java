package ba.sum.fpmoz.dvasic.pma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

import ba.sum.fpmoz.dvasic.pma.messages.MessagesController;
import ba.sum.fpmoz.dvasic.pma.model.Messages;
import ba.sum.fpmoz.dvasic.pma.ui.adapters.TabbedAdapter;
import ba.sum.fpmoz.dvasic.pma.ui.fragments.users.LoginUserFragment;
import ba.sum.fpmoz.dvasic.pma.ui.fragments.users.RegisterUserFragment;

public class TabbedUserActivity extends AppCompatActivity {

    TabbedAdapter adapter;
    TabLayout layout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_user);
        setTitle("Prijava na sustav");
        this.layout = findViewById(R.id.tabLayout);
        this.pager = findViewById(R.id.viewPager);

        FirebaseMessaging.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("ednevnik");


        this.adapter = new TabbedAdapter(getSupportFragmentManager(), 1);
        this.adapter.addFragment(
                new LoginUserFragment(), "Prijavite se na sustav"
        );

        this.adapter.addFragment(
                new RegisterUserFragment(), "Registracija na sustav"
        );

        this.pager.setAdapter(this.adapter);
        this.layout.setupWithViewPager(this.pager);

    }
}