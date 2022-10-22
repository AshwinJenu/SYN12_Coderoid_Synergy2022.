package com.appsnipp.loginsamples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {

    private TextView login;
    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText password;
    private Button register_btn;
    private Connection connect;
    private String query;
    private Statement st;
    private ResultSet rs;
    private String name_val;
    private String phone_val;
    private String email_val;
    private String password_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = (TextView) findViewById(R.id.login);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register_btn = (Button) findViewById(R.id.register_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name_val = name.getText().toString();
                phone_val = phone.getText().toString();
                email_val = email.getText().toString();
                password_val = password.getText().toString();

                Validation validation = new Validation();

                if(!name_val.equals("")&&!phone_val.equals("")&&
                        !email_val.equals("")&&!password_val.equals("")){

                    if(validation.phoneValidate(phone_val)&&
                            validation.emailValidate(email_val)){
                        try {
                            ConnectionHelper connectionHelper = new ConnectionHelper();
                            connect = connectionHelper.connectionclass();

                            if (connect != null) {
                                query = "Select ID from users where email = '"+email_val+"'";
                                st = connect.createStatement();
                                rs = st.executeQuery(query);
                                if(!rs.isBeforeFirst()) {
                                    query = "INSERT INTO users (name, phone, email ,password)" +
                                            " VALUES ('" + name_val + "', '" + phone_val + "' ,'" + email_val + "' ,'" + password_val + "');";
                                    st = connect.createStatement();
                                    st.executeUpdate(query);
                                    connect.close();
                                    Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(RegisterActivity.this,"Email Already Exists",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }catch (SQLException | ClassNotFoundException e){
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(RegisterActivity.this, "Enter Valid Phone or Email", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Enter all Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
