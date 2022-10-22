package com.appsnipp.loginsamples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context;
    private ArrayList<User> users;

    public UserAdapter(Context context, ArrayList<User> users) {
       this.context = context;
        this.users = users;
    }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.user_layout_item,parent, false);
            return new UserHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {
            User user = users.get(position);
            holder.SetDetails(user);
        }

        @Override
        public int getItemCount() {

            return users.size();
        }


        class UserHolder extends RecyclerView.ViewHolder{

            private TextView txtName, txtRollNo, txtBranch;

            public UserHolder(@NonNull View itemView) {
                super(itemView);
                txtName = itemView.findViewById(R.id.txtName);
                txtRollNo = itemView.findViewById(R.id.txtRollNo);
                txtBranch = itemView.findViewById(R.id.txtBranch);
            }

            void SetDetails(User user){
                txtName.setText(user.getUserName());
                txtRollNo.setText(String.format(Locale.US,"Roll no: %d",user.getRollNo()));
                txtBranch.setText(String.format(Locale.US,"Branch: %s",user.getBranch()));

            }
        }
    }

