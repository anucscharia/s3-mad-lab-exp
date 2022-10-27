package com.example.whatsappusingcountrycode;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    private ImageButton whatsappButton;
    private EditText phoneNumber;
    private EditText message;
    //private EditText countryCode;
    private CountryCodePicker countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whatsappButton = (ImageButton) findViewById(R.id.imageButton);
        phoneNumber = (EditText) findViewById(R.id.editTextPhone) ;
        countryCode = (CountryCodePicker) findViewById(R.id.countryCode_picker) ;
        message = (EditText) findViewById(R.id.editTextMessage);
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isWhatsappInstalled()) {
                    if(phoneNumber.getText().toString().isEmpty() ) {
                        Toast.makeText(MainActivity.this,"please enter valid number",Toast.LENGTH_SHORT).show();

                        return;
                    }
                    String cc = countryCode.getSelectedCountryCode();
                    Log.e("Husain", "getSelectedCountryCode: "+cc  );
                    String number = cc + phoneNumber.getText().toString();
                    String stippedPhoneNumber = PhoneNumberUtils.stripSeparators(number);
                    String jid = stippedPhoneNumber + "@s.whatsapp.net";
                    Log.e("Husain", "Number: "+number + " stippedPhoneNumber: "+stippedPhoneNumber + " jid: "+ jid);
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SENDTO);
                    sendIntent.putExtra("jid", jid);//phone number without "+" prefix
                    sendIntent.putExtra(Intent.EXTRA_TEXT, message.getText().toString() );
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp.Conversation");
                    //startActivity(sendIntent);


                    Uri uri1 = Uri.parse("https://api.whatsapp.com/send?phone=" + stippedPhoneNumber + "&text=" + message.getText().toString());
                    Intent sendIntent1 = new Intent(Intent.ACTION_VIEW, uri1);
                    sendIntent1.setPackage("com.whatsapp");
                    startActivity(sendIntent1);

                }
            }
        });
    }

    boolean isWhatsappInstalled() {
        PackageManager pkgMgr = getPackageManager();
        boolean installed = false;
        try {
            Log.e("ANU", "isWhatsappInstalled");
            PackageInfo packageInfo = pkgMgr.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            installed = true;

        } catch(PackageManager.NameNotFoundException e) {
            Log.v("ANU" ," response " +  e.getMessage());
            Toast.makeText(this,"whatsapp not installed", Toast.LENGTH_SHORT).show();
        }
        return installed;

    }
}