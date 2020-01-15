package ba.sum.fpmoz.smart;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class ParkingStatusAdapter extends RecyclerView.Adapter<ParkingStatusAdapter.MyViewHolder> {

    private ArrayList<ParkingMjesto> parkingMjesta;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView statusView;
        public ImageView imageView;

        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.Naziv);
            statusView = v.findViewById(R.id.StatusTxt);
            imageView = v.findViewById(R.id.Status);
        }
    }

    public ParkingStatusAdapter(ArrayList<ParkingMjesto> parkingMjesta) {
        this.parkingMjesta = parkingMjesta;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_spaces, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ParkingMjesto pm = this.parkingMjesta.get(position);
        holder.textView.setText(pm.getNaziv());
        if (pm.isZauzeto() == 1) {
            holder.imageView.setImageResource(R.drawable.location_off);
            holder.statusView.setText("Zauzeto");
        } else {
            holder.imageView.setImageResource(R.drawable.location_on);
            holder.statusView.setText("Slobodno");
        }
    }

    @Override
    public int getItemCount() {
        return this.parkingMjesta.size();
    }
}

