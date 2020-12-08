package ba.sum.fpmoz.dvasic.pma.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ba.sum.fpmoz.dvasic.pma.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view, parent, false);
        return new StudentAdapter.StudentViewHolder(view);
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView studentSurname;
        TextView studentUid;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentNameTxt);
            studentSurname = itemView.findViewById(R.id.studentSurnameTxt);
            studentUid = itemView.findViewById(R.id.studentUidTxt);
        }
    }
}
