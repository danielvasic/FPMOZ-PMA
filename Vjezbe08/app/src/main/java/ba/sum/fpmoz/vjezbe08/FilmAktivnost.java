package ba.sum.fpmoz.vjezbe08;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import ba.sum.fpmoz.vjezbe08.model.Movie;

public class FilmAktivnost extends AppCompatActivity {
    private static final int ODABERI_SLIKU = 100;
    TextView naslovFilmTxt;
    TextView opisFilmTxt;
    Button otvoriGalerijuBtn;
    Button spasiFilmBtn;

    Uri putanjaSlike;

    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_aktivnost);

        this.storage = FirebaseStorage.getInstance();
        this.storageReference = this.storage.getReference("uploads");

        this.database = FirebaseDatabase.getInstance();
        this.databaseReference = this.database.getReference("filmovi");

        this.naslovFilmTxt = findViewById(R.id.naziv_filma_txt);
        this.opisFilmTxt = findViewById(R.id.opis_filma_txt);
        this.otvoriGalerijuBtn = findViewById(R.id.otvori_galeriju_btn);
        this.spasiFilmBtn = findViewById(R.id.dodaj_film_btn);

        otvoriGalerijuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Odaberite sliku"), ODABERI_SLIKU);
            }
        });

        spasiFilmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naslov = naslovFilmTxt.getText().toString();
                String opis = opisFilmTxt.getText().toString();
                String slika = putanjaSlike.toString();
                Movie film = new Movie(naslov, opis, slika, 0.0f);
                // Jos jedan nacin generiranja kljuceva
                // String _id = UUID.randomUUID().toString();
                String _id = databaseReference.push().getKey();

                databaseReference.child(_id).setValue(film);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ODABERI_SLIKU) {
                ucitajSliku(data.getData());
            }
        }
    }

    private void ucitajSliku (Uri datotekaUri) {
        final StorageReference datotekaPutanja = storageReference.child(UUID.randomUUID().toString());
        datotekaPutanja.putFile(datotekaUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return datotekaPutanja.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    putanjaSlike = task.getResult();
                }
            }
        });
    }


}
