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

public class LoginActivity extends AppCompatActivity {

    private Connection connect;
    private String query;
    private Statement st;
    private ResultSet rs;
    private TextView register;
    private EditText email;
    private EditText password;
    private Button login_btn;
    private String email_val;
    private String password_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.register);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        login_btn = (Button) findViewById(R.id.LoginButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_val = email.getText().toString();
                password_val = password.getText().toString();

                Validation validation = new Validation();

                if(!email_val.equals("")&&!password_val.equals("")){
                    if(validation.emailValidate(email_val)){
                        try {
                            ConnectionHelper connectionHelper = new ConnectionHelper();
                            connect = connectionHelper.connectionclass();

                            if (connect != null) {
                                query = "Select ID from users where email = '" + email_val + "' AND password='"+password_val+"'";
                                st = connect.createStatement();
                                rs = st.executeQuery(query);
                                if(!rs.isBeforeFirst()) {
                                    Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                                }else{
                                    rs.next();
                                    Global.ID = rs.getString("ID");
                                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                connect.close();
                            }

                        }catch (SQLException | ClassNotFoundException e){
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}