package com.example.a14_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class topfragment extends Fragment {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private Button buttonApply;
    private MainActivity mainActivity;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_top, container, false);

        editTextFirstName = (EditText) view.findViewById(R.id.editText_firstName);
        editTextLastName = (EditText) view.findViewById(R.id.editText_lastName);

        buttonApply = (Button) view.findViewById(R.id.button_apply);

        buttonApply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                applyText();
            }
        });

        return view;
    }

    // Called when a fragment is first attached to its context.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }


    private void applyText() {
        String firstName = this.editTextFirstName.getText().toString();
        String lastName = this.editTextLastName.getText().toString();

        this.mainActivity.showText(firstName, lastName);
    }
}