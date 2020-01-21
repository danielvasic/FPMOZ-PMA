package ba.sum.fpmoz.smart;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class ParkingStatusAdapter extends RecyclerView.Adapter<ParkingStatusAdapter.MyViewHolder> {

    private ArrayList<ParkingMjesto> parkingMjesta;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView statusView;
        public ImageView imageView;
        public View mView;

        public MyViewHolder(View v) {
            super(v);
            mView = v;
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
        final ParkingMjesto pm = this.parkingMjesta.get(position);
        holder.textView.setText(pm.getNaziv());
        if (pm.isZauzeto() == 1) {
            holder.imageView.setImageResource(R.drawable.location_off);
            holder.statusView.setText("Zauzeto");
        } else {
            holder.imageView.setImageResource(R.drawable.location_on);
            holder.statusView.setText("Slobodno");
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RezervacijaParkinga.class);
                i.putExtra("parkingId", String.valueOf(pm.getId()));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.parkingMjesta.size();
    }
}

