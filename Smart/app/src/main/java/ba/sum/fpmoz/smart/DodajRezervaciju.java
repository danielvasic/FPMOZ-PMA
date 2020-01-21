package ba.sum.fpmoz.smart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DodajRezervaciju extends DialogFragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public DodajRezervaciju(){}

    public static DodajRezervaciju newInstance(String parkingId) {
        DodajRezervaciju frag = new DodajRezervaciju();
        Bundle args = new Bundle();
        args.putString("parkingId", parkingId);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rezerviraj_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseReference referenca = database.getReference("rezervacije");

        String parkingId = getArguments().getString("parkingId", "0");

        DatePicker datumDP = view.findViewById(R.id.datum);
        TimePicker vrijemeOD = view.findViewById(R.id.od_vrijeme);
        TimePicker vrijemeDO = view.findViewById(R.id.do_vrijeme);
        EditText registracijaEdit = view.findViewById(R.id.unosRegistracije);
        Button spasi = view.findViewById(R.id.spasi);

        spasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar kalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                kalendar.set(datumDP.getYear(), datumDP.getMonth(), datumDP.getDayOfMonth());

                kalendar.set(Calendar.HOUR_OF_DAY, vrijemeOD.getHour());
                kalendar.set(Calendar.MINUTE, vrijemeOD.getMinute());

                Long vrijemeODTS = kalendar.getTimeInMillis();

                kalendar.set(Calendar.HOUR_OF_DAY, vrijemeDO.getHour());
                kalendar.set(Calendar.MINUTE, vrijemeDO.getMinute());

                Long vrijemeDOTS = kalendar.getTimeInMillis();

                String registracija = registracijaEdit.getText().toString();

                /* ovdje dodati provjere */

                referenca.child(parkingId).push().setValue(new Rezervacija(vrijemeODTS, vrijemeDOTS, registracija));

                dismiss();
            }
        });



    }
}
