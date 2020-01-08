package ba.sum.fpmoz.smart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SmartSum {
    @GET("parking")
    Call<List<Parking>> dajParking(@Query("withParkingSpaces") boolean mjesta);
}
