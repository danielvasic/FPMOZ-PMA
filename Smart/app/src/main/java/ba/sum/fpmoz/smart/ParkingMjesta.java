package ba.sum.fpmoz.smart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkingMjesta extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @Override
    public void onRefresh() {
        this.loadParkingSpaces();
    }

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SmartSum service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_spaces);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://smart.sum.ba/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SmartSum.class);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.refresh_layout);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        this.loadParkingSpaces();
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    private void loadParkingSpaces(){
        Call<List<Parking>> parkingCall = service.dajParking(true);
        parkingCall.enqueue(new Callback<List<Parking>>() {
            @Override
            public void onResponse(Call<List<Parking>> call, Response<List<Parking>> response) {
                Parking p = response.body().get(0);
                ParkingStatusAdapter psa = new ParkingStatusAdapter(p.getParkingMjesta());
                recyclerView.setAdapter(psa);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Parking>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
