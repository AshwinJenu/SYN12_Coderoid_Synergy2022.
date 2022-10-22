package com.appsnipp.loginsamples;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;

public class ProfileFragment extends Fragment {

    TextView dob;
    TextView yop;
    TextView name;
    TextView email;
    TextView address;
    TextView roll;
    TextView dept;
    Button save;
    private Connection connect;
    private String query;
    private Statement st;
    private ResultSet rs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        dob = (TextView) view.findViewById(R.id.dob_tv);
        yop = (TextView) view.findViewById(R.id.yop_tv);
        name = (TextView) view.findViewById(R.id.profile_name);
        email = (TextView) view.findViewById(R.id.profile_email);
        save = (Button) view.findViewById(R.id.save_btn);
        address = (TextView) view.findViewById(R.id.address_tv);
        roll = (TextView) view.findViewById(R.id.roll_tv);
        dept = (TextView) view.findViewById(R.id.dept_tv);

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();

            if (connect != null) {
                query = "Select * from users where ID = '"+Global.ID+"'";
                st = connect.createStatement();
                rs = st.executeQuery(query);
                rs.next();
                name.setText(rs.getString("Name"));
                email.setText(rs.getString("Email"));
                connect.close();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        dob.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        dob.setText(year+"/"+month+"/"+dayOfMonth);
                    }
                },year,month,dayOfMonth);
                datePicker.show();
            }
        });

        yop.setOnClickListener(new View.OnClickListener() {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

            @Override
            public void onClick(View view) {

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        yop.setText(year+"/"+month+"/"+dayOfMonth);
                    }
                },year,month,dayOfMonth);
                datePicker.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();

                    if (connect != null) {
                        query = "Insert into application(Address,roll_no,dob,department,year_of_passing,sgpi,cgpi,honours)" +
                                " Values('')";
                        st = connect.createStatement();
                        st.executeUpdate(query);
                        name.setText(rs.getString("Name"));
                        email.setText(rs.getString("Email"));
                        connect.close();
                    }
                }catch (SQLException | ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        });

        return view;
    }


}
