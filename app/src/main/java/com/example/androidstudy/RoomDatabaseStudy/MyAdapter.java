package com.example.androidstudy.RoomDatabaseStudy;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudy.R;
import com.example.androidstudy.RoomDatabaseStudy.database.User;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//    private String[] mDataset;
    private List<User> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView_id;
        public LinearLayout linearLayout;
        public MyViewHolder(LinearLayout l) {
            super(l);
            linearLayout = l;
//            textView_id = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<User> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.database_view, parent, false);
        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.database_view,parent,false);
        MyViewHolder vh = new MyViewHolder(l);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.textView_id.setText(mDataset.get(position));
        TextView textView_id= holder.linearLayout.findViewById(R.id.database_id);
        textView_id.setText(String.valueOf(mDataset.get(position).uid));
        TextView textView_name = holder.linearLayout.findViewById(R.id.database_name);
        textView_name.setText(mDataset.get(position).name);
        TextView textView_pass = holder.linearLayout.findViewById(R.id.database_password);
        textView_pass.setText(mDataset.get(position).password);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.i("databaseActivity","length:"+mDataset.size());
        return mDataset.size();
    }
}
