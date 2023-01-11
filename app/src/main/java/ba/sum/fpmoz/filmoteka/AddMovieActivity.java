package ba.sum.fpmoz.filmoteka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import ba.sum.fpmoz.filmoteka.model.Movie;

public class AddMovieActivity extends AppCompatActivity {

    Button selectImageBtn;
    Button uploadImageBtn;
    ImageView imagePreview;

    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://filmoteka-8adc2-default-rtdb.europe-west1.firebasedatabase.app/");


    Uri filePath;
    String movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        this.selectImageBtn = findViewById(R.id.selectImageBtn);
        this.uploadImageBtn = findViewById(R.id.uploadImageBtn);
        this.imagePreview = findViewById(R.id.imagePreview);

        this.storage = FirebaseStorage.getInstance();
        this.storageReference = this.storage.getReference();

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

        EditText movieTitleEdTxt = findViewById(R.id.movieTitleEdTxt);
        EditText movieScoreEdTxt = findViewById(R.id.movieScoreEdTxt);
        EditText movieYearEdTxt = findViewById(R.id.movieYearEdTxt);
        EditText movieGenereEdTxt = findViewById(R.id.movieGenereEdTxt);

        Button movieSaveBtn = findViewById(R.id.saveMovieBtn);

        DatabaseReference moviesReference = mDatabase.getReference("movie");

        movieSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = movieTitleEdTxt.getText().toString();
                Double score = Double.valueOf(movieScoreEdTxt.getText().toString());
                Long year = Long.valueOf(movieYearEdTxt.getText().toString());
                String genere = movieGenereEdTxt.getText().toString();
                Movie m = new Movie(title, genere, score, year, movieImage);
                moviesReference.push().setValue(m);
                Intent i = new Intent(AddMovieActivity.this, MovieActivity.class);
                startActivity(i);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 &&
                resultCode == RESULT_OK &&
                data != null &&
                data.getData() != null) {
            this.filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imagePreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void selectImage () {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(i, "Odaberite sliku filma"), 22
        );
    }

    private void uploadImage () {
        if (filePath != null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Učitavam sliku");
            progressDialog.show();
            StorageReference ref = storageReference.child("images/"
                    + UUID.randomUUID().toString()
            );
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.hide();
                    Toast.makeText(
                            getApplicationContext(),
                            "Slika je učitana na server",
                            Toast.LENGTH_LONG).show();
                    ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            movieImage = task.getResult().toString();
                        }
                    });
                }
            });


        }
    }
}