package com.appsnipp.loginsamples;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    String uname, pass, ip, port,database;
    @SuppressLint("NewApi")

    public Connection connectionclass() throws SQLException, ClassNotFoundException {
        ip = "103.233.24.136";
        database = "Coderoid";
        uname = "wintech";
        pass = "wintech@123456!";
        port = "1433";




        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;


        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
        connection = DriverManager.getConnection(ConnectionURL);


        return connection;
    }
}
