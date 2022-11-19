package com.example.a14_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class bottomfragment extends Fragment {

    private TextView textViewFullName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_bottom, container, false);

        textViewFullName = (TextView) view.findViewById(R.id.textView_fullName);

        return view;
    }


    public void showText(String firstName, String lastName) {
        textViewFullName.setText(firstName + " " + lastName);
    }
}
