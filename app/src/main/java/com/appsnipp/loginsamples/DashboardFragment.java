package com.appsnipp.loginsamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.razorpay.Checkout;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardFragment extends Fragment {


    Button payment;
    Button apply;
    Button clg_id;
    Button sem_rep;
    Button aadhaar;
    private Connection connect;
    private String query;
    private Statement st;
    private ResultSet rs;
    EditText sgpi_et;
    EditText cgpi_et;
    EditText honours_et;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        payment = (Button) view.findViewById(R.id.payment_btn);
        apply = (Button) view.findViewById(R.id.apply_btn);
        clg_id = (Button) view.findViewById(R.id.clg_id_btn);
        sem_rep = (Button) view.findViewById(R.id.sem_rep_btn);
        aadhaar = (Button) view.findViewById(R.id.aadhaar_btn);
        sgpi_et = (EditText) view.findViewById(R.id.sgpi_et);
        cgpi_et = (EditText) view.findViewById(R.id.cgpi_et);
        honours_et = (EditText) view.findViewById(R.id.hon_et);

        clg_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(view.getContext(),PdfActivity.class);
                startActivity(intent);



            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sgpi = sgpi_et.getText().toString();
                String cgpi = cgpi_et.getText().toString();
                String honours = honours_et.getText().toString();

                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connect = connectionHelper.connectionclass();

                    if (connect != null) {
                        query = "Insert into application(ID,sgpi,cgpi,honours)" +
                                " Values('"+Global.ID+"','"+sgpi+"','"+cgpi+"','"+honours+"');";
                        st = connect.createStatement();
                        st.executeUpdate(query);
                        connect.close();
                    }
                }catch (SQLException | ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });
        return view;
    }

    private void startPayment() {
        {

            /**
             * Instantiate Checkout
             */
            Checkout checkout = new Checkout();
            checkout.setKeyID("rzp_test_DCsKikVQIQzNj4");

            /**
             * Set your logo here
             */
            checkout.setImage(R.drawable.app_logo);

            /**
             * Reference to current activity
             */
            final DashboardFragment fragment = this;

            /**
             * Pass your payment options to the Razorpay Checkout as a JSONObject
             */
            try {
                JSONObject options = new JSONObject();

                options.put("name", "CodeRoid");
                options.put("description", "Reference No. #123456");
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
                // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                options.put("theme.color", "#3399cc");
                options.put("currency", "INR");
                options.put("amount", "50000");//pass amount in currency subunits
                options.put("prefill.email", "roland.r.dcruz@gmail.com");
                options.put("prefill.contact", "9167483484");
                JSONObject retryObj = new JSONObject();
                retryObj.put("enabled", true);
                retryObj.put("max_count", 4);
                options.put("retry", retryObj);

                checkout.open(fragment.getActivity(), options);

            } catch (Exception e) {
                Log.e("TAG", "Error in starting Razorpay Checkout", e);
            }
        }
    }

    public void onPaymentSuccess(String s) {
        Log.d("ONSUCCESS", "onPaymentSuccess: "+s);
    }


    public void onPaymentError(int i, String s) {
        Log.d("ONERROR", "onPaymentError: "+s);
    }
}


