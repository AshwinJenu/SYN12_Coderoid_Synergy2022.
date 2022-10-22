package com.appsnipp.loginsamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TPOActivity extends AppCompatActivity {

    private Connection connect;
    private String query;
    private Statement st;
    private ResultSet rs;

    RecyclerView normal_recyler;
    private UserAdapter userAdapter;
    //private ArrayList<User> users = ArrayList<>(Global.userArrayList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo);

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();

            if (connect != null) {
                query = "Select name, roll_no, department from users,profile where Users.ID = '"+Global.ID+"'";
                st = connect.createStatement();
                rs = st.executeQuery(query);
                while(rs.next()){
                 Global.userArrayList.add(new User(rs.getString("name"), Integer.parseInt(rs.getString("roll_no")),rs.getString("department")));
                    System.out.println("ok");
                }
                connect.close();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        normal_recyler = findViewById(R.id.normal_recy);
        normal_recyler.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, Global.userArrayList);
        normal_recyler.setAdapter(userAdapter);
        normal_recyler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //createListData();
    }

    /*private void createListData(){
        User user = new User("Mark Smith", 9410, "COMPS");
        Global.userArrayList.add(user);
        user = new User("Clement Shelly", 9423, "COMPS");
        Global.userArrayList.add(user);
        user = new User("Shanon Jonah", 9643, "EXTC");
        userArrayList.add(user);
        user = new User("Philip Jepson", 9423, "AIDS");
        userArrayList.add(user);
        user = new User("Deven Maynard", 9445, "AIDS");
        userArrayList.add(user);
    }*/
}