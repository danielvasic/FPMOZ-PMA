package ba.sum.fpmoz.dvasic.pma.ui.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.UserAdminEditActivity;
import ba.sum.fpmoz.dvasic.pma.model.Student;

public class StudentAdapter extends FirebaseRecyclerAdapter<Student, StudentAdapter.StudentViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StudentAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull StudentViewHolder holder, int position, @NonNull Student model) {
        holder.studentName.setText(model.getName());
        holder.studentSurname.setText(model.getSurname());
        holder.studentUid.setText(model.getUid());
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_student_admin, parent, false);
        StudentViewHolder viewHolder = new StudentAdapter.StudentViewHolder(view);

        viewHolder.setOnClickListener(new Adapter.ClickListener() {
            @Override
            public void OnClickListener(View v, int position) {

            }

            @Override
            public void OnLongClickListener(View v, int position) {
                getRef(position);
            }
        });
        return viewHolder;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView studentSurname;
        TextView studentUid;
        Button studentEditBtn;
        Button studentDeleteBtn;

        Adapter.ClickListener clickListener;

        public void setOnClickListener(Adapter.ClickListener clickListener){
            this.clickListener = clickListener;
        }

        public StudentViewHolder(@NonNull final View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentNameTxt);
            studentSurname = itemView.findViewById(R.id.studentSurnameTxt);
            studentUid = itemView.findViewById(R.id.studentUidTxt);
            studentEditBtn = itemView.findViewById(R.id.studentEditBtn);
            studentDeleteBtn = itemView.findViewById(R.id.studentDeleteBtn);

            studentDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getRef(getAdapterPosition()).removeValue();
                }
            });

            studentEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key = getRef(getAdapterPosition()).getKey();
                    Intent i = new Intent(itemView.getContext(), UserAdminEditActivity.class);
                    i.putExtra("USER_ID", key);
                    itemView.getContext().startActivity(i);
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
