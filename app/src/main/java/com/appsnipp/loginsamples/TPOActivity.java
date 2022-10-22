package com.appsnipp.loginsamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TPOActivity extends AppCompatActivity {

    RecyclerView normal_recyler;
    private UserAdapter userAdapter;
    private ArrayList<User> userArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo);

        normal_recyler = findViewById(R.id.normal_recy);
        normal_recyler.setLayoutManager(new LinearLayoutManager(this));
        userArrayList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userArrayList);
        normal_recyler.setAdapter(userAdapter);
        normal_recyler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        createListData();
    }

    private void createListData(){
        User user = new User("Mark Smith", 9410, "COMPS");
        userArrayList.add(user);
        user = new User("Clement Shelly", 9423, "COMPS");
        userArrayList.add(user);
        user = new User("Shanon Jonah", 9643, "EXTC");
        userArrayList.add(user);
        user = new User("Philip Jepson", 9423, "AIDS");
        userArrayList.add(user);
        user = new User("Deven Maynard", 9445, "AIDS");
        userArrayList.add(user);
    }
}