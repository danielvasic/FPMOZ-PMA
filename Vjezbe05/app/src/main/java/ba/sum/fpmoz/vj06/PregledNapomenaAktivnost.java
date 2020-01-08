package ba.sum.fpmoz.vj06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ba.sum.fpmoz.vj06.model.Napomena;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PregledNapomenaAktivnost extends AppCompatActivity {


    @BindView(R.id.lista_napomena)
    ListView listaNapomena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_napomena_aktivnost);
        ButterKnife.bind(this);

        List<Napomena> napomene = Napomena.listAll(Napomena.class);
        List<String> napomeneString = new ArrayList<>();

        for (Napomena napomena : napomene) {
            napomeneString.add(napomena.getNaslov());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                napomeneString
                );
        listaNapomena.setAdapter(adapter);
    }
}
