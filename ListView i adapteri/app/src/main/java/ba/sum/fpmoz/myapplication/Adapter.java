package ba.sum.fpmoz.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    List<String> kontakti;
    Context ctx;

    public Adapter(List<String> kontakti, Context ctx) {
        this.kontakti = kontakti;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return this.kontakti.size();
    }

    @Override
    public Object getItem(int position) {
        return this.kontakti.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater l = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = l.inflate(R.layout.list_item, parent, false);
        }

        TextView t = convertView.findViewById(R.id.imeTxt);
        t.setText((String) this.getItem(position));
        return convertView;
    }
}
