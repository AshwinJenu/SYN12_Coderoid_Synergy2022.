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

public class ProfileFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    TextView dob;
    TextView yop;
    TextView name;
    TextView email;
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
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(),"Date Picker");
                dob.setText("ok");
            }
        });

        yop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(),"Date Picker");
            }
        });

        return view;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dob.setText(currentDate);
    }
}
