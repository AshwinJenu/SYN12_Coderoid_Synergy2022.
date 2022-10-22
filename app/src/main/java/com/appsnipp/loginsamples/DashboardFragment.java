package com.appsnipp.loginsamples;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    Button payment;
    Button clg_id;
    Button sem_rep;
    Button aadhaar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        payment = (Button) view.findViewById(R.id.payment_btn);
        clg_id = (Button) view.findViewById(R.id.clg_id_btn);
        sem_rep = (Button) view.findViewById(R.id.sem_rep_btn);
        aadhaar = (Button) view.findViewById(R.id.aadhaar_btn);

        clg_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/pdf");


            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startPayment();
            }
        });
        return view;
    }



}
