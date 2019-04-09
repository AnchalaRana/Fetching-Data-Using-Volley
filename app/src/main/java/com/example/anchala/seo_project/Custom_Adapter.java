package com.example.anchala.seo_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.ViewHolder> {

    private Context mContext ;
    private ArrayList<User> mUsers;

    public Custom_Adapter(Context context, ArrayList<User> users){
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    @SuppressWarnings("deprecation")
    public Custom_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_layout , viewGroup , false);

        return new ViewHolder(v);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onBindViewHolder(@NonNull Custom_Adapter.ViewHolder viewHolder, int i) {

        User user = mUsers.get(i);
        viewHolder.name.setText(user.getName());
        viewHolder.email.setText(user.getEmail());
        viewHolder.home.setText(user.getPhone().getHome());
        viewHolder.mobile.setText(user.getPhone().getMobile());

    }

    @Override
    @SuppressWarnings("deprecation")
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name , email , home , mobile;

        public ViewHolder(View v) {
            super(v);

         name = v.findViewById(R.id.rname);
         email = v.findViewById(R.id.remail);
         home = v.findViewById(R.id.rhome);
         mobile = v.findViewById(R.id.rmobile);

        }
    }
}
