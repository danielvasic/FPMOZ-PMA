package ba.sum.fpmoz.dvasic.pma.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.dvasic.pma.model.Classes;
import ba.sum.fpmoz.dvasic.pma.R;
public class ClassesAdapter extends FirebaseRecyclerAdapter<Classes, ClassesAdapter.ClassesViewHolder> {


    public ClassesAdapter(@NonNull FirebaseRecyclerOptions<Classes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ClassesAdapter.ClassesViewHolder holder, int position, @NonNull Classes model) {
        holder.classesName.setText(model.name);
        holder.classesId.setText(model.uid);
    }

    @NonNull
    @Override
    public ClassesAdapter.ClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_classes_admin, parent, false);
        ClassesViewHolder viewHolder = new ClassesViewHolder(view);

        viewHolder.setOnClickListener(new Adapter.ClickListener() {
            @Override
            public void OnClickListener(View v, int position) {

            }

            @Override
            public void OnLongClickListener(View v, int position) {

            }
        });
        return viewHolder;
    }

    public class ClassesViewHolder extends RecyclerView.ViewHolder {
        EditText classesId;
        EditText classesName;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public ClassesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.classesId = itemView.findViewById(R.id.classesUidTxt);
            this.classesName = itemView.findViewById(R.id.classesNameTxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.OnClickListener(v, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.OnLongClickListener(v, getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
