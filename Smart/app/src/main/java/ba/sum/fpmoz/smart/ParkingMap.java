package ba.sum.fpmoz.smart;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkingMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Call<List<Parking>> parkingCall = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://smart.sum.ba/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SmartSum service = retrofit.create(SmartSum.class);
        this.parkingCall = service.dajParking(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        this.parkingCall.enqueue(new Callback<List<Parking>>() {
            @Override
            public void onResponse(Call<List<Parking>> call, Response<List<Parking>> response) {
                Parking p = response.body().get(0);
                Toast.makeText(getApplicationContext(), p.getNaziv(), Toast.LENGTH_LONG).show();
                LatLng parkingSpace = new LatLng(p.getLatituda(), p.getLongituda());
                mMap.addMarker(new MarkerOptions().position(parkingSpace).title(p.getNaziv()).snippet(p.getAdresa()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parkingSpace, 17.0f));
            }

            @Override
            public void onFailure(Call<List<Parking>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
