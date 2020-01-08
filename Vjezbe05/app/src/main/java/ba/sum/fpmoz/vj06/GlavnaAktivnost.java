package ba.sum.fpmoz.vj06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ba.sum.fpmoz.vj06.model.Napomena;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlavnaAktivnost extends AppCompatActivity {

    @BindView(R.id.naslov_napomena_txt)
    EditText naslovNapomenaTxt;

    @BindView(R.id.napomena_napomena_txt)
    EditText napomenaNapomenaTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavna_aktivnost);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.spremi_napomena_btn)
    public void dodajNapomenu (View view) {
        String naslov = this.naslovNapomenaTxt.getText().toString();
        String napomena = this.napomenaNapomenaTxt.getText().toString();
        Napomena n = new Napomena(naslov, napomena);
        n.save();
        this.naslovNapomenaTxt.setText("");
        this.napomenaNapomenaTxt.setText("");
    }

    @OnClick(R.id.otvori_napomene_btn)
    public void otvoriNapomene (View view) {
        Intent i = new Intent(this, PregledNapomenaAktivnost.class);
        startActivity(i);
    }
}
