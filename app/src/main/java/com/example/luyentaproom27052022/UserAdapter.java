package com.example.luyentaproom27052022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.luyentaproom27052022.databinding.ActivityUpdateBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    ActivityUpdateBinding activityUpdateBinding;

    private List<User> mListUser;

    private IClickItemUser iClickItemUser;

    public UserAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }

    public void setData(List<User> list)
    {
        this.mListUser=list;
        notifyDataSetChanged();
    }

    public interface IClickItemUser
    {
        void updateUser(User user);
        void deleteUser(User user);
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
        //holder.mTxtYear.setText(user.getYear());

        holder.mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUser.updateUser(user);
            }
        });

        holder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUser.deleteUser(user);
            }
        });
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

        TextView mTxtUsername, mTxtAddress, mTxtYear;
        Button mBtnEdit, mBtnDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtAddress=itemView.findViewById(R.id.txtAddress);
            mTxtUsername=itemView.findViewById(R.id.txtUserName);
            mBtnEdit=itemView.findViewById(R.id.btnEdit);
            mBtnDelete=itemView.findViewById(R.id.btnDelete);
            mTxtYear=itemView.findViewById(R.id.txtYear);
        }
    }


}
