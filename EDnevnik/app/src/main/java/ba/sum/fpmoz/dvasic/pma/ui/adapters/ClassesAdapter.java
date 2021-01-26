package ba.sum.fpmoz.dvasic.pma.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.dvasic.pma.TabbedClassesActivity;
import ba.sum.fpmoz.dvasic.pma.TabbedUserActivity;
import ba.sum.fpmoz.dvasic.pma.messages.MessagesController;
import ba.sum.fpmoz.dvasic.pma.model.Classes;
import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.model.Messages;

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

        Button classesDeleteBtn;
        Button classesEditBtn;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public ClassesViewHolder(@NonNull final View itemView) {


            super(itemView);
            this.classesId = itemView.findViewById(R.id.classesUidTxt);
            this.classesName = itemView.findViewById(R.id.classesNameTxt);
            this.classesDeleteBtn = itemView.findViewById(R.id.classesDeleteBtn);
            this.classesEditBtn = itemView.findViewById(R.id.classesEditBtn);

            classesDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getRef(getAdapterPosition()).removeValue();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    db.getReference("edenvnik/notifikacije").push().setValue(new Messages("Obavijest", "Pobrisan je razred."));
                    // MessagesController.notify(new Messages("Obavijest", "Pobrisan je razred."), itemView.getContext());
                }
            });

            this.classesEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Classes c = new Classes();
                    c.name = classesName.getText().toString();
                    c.uid = classesId.getText().toString();
                    getRef(getAdapterPosition()).setValue(c);
                }
            });

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
