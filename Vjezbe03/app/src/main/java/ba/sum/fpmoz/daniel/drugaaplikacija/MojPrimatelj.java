package ba.sum.fpmoz.daniel.drugaaplikacija;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MojPrimatelj extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Ovo je moj primatelj", Toast.LENGTH_SHORT).show();
    }
}
