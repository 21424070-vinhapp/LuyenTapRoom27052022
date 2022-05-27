package com.example.luyentaproom27052022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luyentaproom27052022.databinding.ActivityMainBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> mListUser;
    private void setData(List<User> list)
    {
        this.mListUser=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user=mListUser.get(position);
        if(user==null)
        {
            return;
        }

        holder.mTxtUsername.setText(user.getUsername());
        holder.mTxtAddress.setText(user.getAddress());
    }

    @Override
    public int getItemCount() {
        if(mListUser!=null)
        {
            return mListUser.size();
        }
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView mTxtUsername, mTxtAddress;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtAddress=itemView.findViewById(R.id.txtAddress);
            mTxtUsername=itemView.findViewById(R.id.txtUserName);
        }


    }
}
