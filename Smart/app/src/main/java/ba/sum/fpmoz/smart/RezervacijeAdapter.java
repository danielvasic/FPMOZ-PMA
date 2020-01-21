package ba.sum.fpmoz.smart;


import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class RezervacijeAdapter extends RecyclerView.Adapter<RezervacijeAdapter.MyViewHolder> {

    private ArrayList<Rezervacija> rezervacije;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView zauzetoOD;
        public TextView zauzetoDO;
        public TextView registracija;

        public MyViewHolder(View v) {
            super(v);
            zauzetoOD = v.findViewById(R.id.zauzetoOD);
            zauzetoDO = v.findViewById(R.id.zauzetoDO);
            registracija = v.findViewById(R.id.registracija);
        }
    }

    public RezervacijeAdapter(ArrayList<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rezervacija, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Rezervacija rezervacija = this.rezervacije.get(position);
        holder.zauzetoDO.setText(rezervacija.getVrijemeDOString());
        holder.zauzetoOD.setText(rezervacija.getVrijemeODString());
        holder.registracija.setText(rezervacija.getRegistracija());
    }

    public void updateList(ArrayList<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.rezervacije.size();
    }
}


