package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView num1;
    private TextView num2;
    private TextView operator;
    private EditText resultEntered;
    private TextView level;
    private TextView score;
    private TextView status;
    private Button nextLevel;
    private  SharedPreferences sharedPreferences;

    private void updateScore() {
        int scr;
        scr = Integer.parseInt(score.getText().toString());
        scr = scr + 10;
        score.setText("" + scr);
    }
    private void updateLevel() {
        int lvl = Integer.parseInt(level.getText().toString());
        lvl = lvl + 1;
    }
    private void resetUI() {
        Random r = new Random();
        int lvl = Integer.parseInt(String.valueOf((TextView) findViewById(R.id.levelvalue)));
        int scr = Integer.parseInt(String.valueOf((TextView) findViewById(R.id.scorevalue)));

        int r1 = r.nextInt(lvl * 10);
        int r2 = r.nextInt(lvl * 10);
        num1.setText("" + r1);
        num2.setText("" + r2);
        num1.setTextColor(Color.RED);
        num2.setTextColor(Color.RED);
        int op = r.nextInt( 3);
        String opr = "+";
        switch (op) {
            case 3:
                opr = "/";
                if(r1<r2) {
                    num1.setText("" + r2);
                    num2.setText("" + r1);
                }
                break;
            case 2:
                opr = "*";
                break;
            case 1:
                opr = "-";
                if(r1<r2) {
                    num1.setText("" + r2);
                    num2.setText("" + r1);
                }
                break;
        }
        score.setText("" + scr);
        level.setText("" + lvl);
        operator.setText(opr);
        resultEntered.setText("");
        resultEntered.setTextColor(Color.BLACK);
        nextLevel.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (TextView) findViewById(R.id.num1);
        num2 = (TextView) findViewById(R.id.num2);
        operator = (TextView)findViewById(R.id.operator);
        resultEntered = (EditText) findViewById(R.id.result);
        level = (TextView) findViewById(R.id.levelvalue) ;
        score=(TextView) findViewById(R.id.scorevalue);
        status=(TextView) findViewById(R.id.status);
        nextLevel = (Button) findViewById(R.id.next);
        nextLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateLevel();
                resetUI();
            }
        });
        resultEntered.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE) {
                    if(!TextUtils.isEmpty(resultEntered.getText().toString())) {
                        int result = Integer.parseInt(resultEntered.getText().toString());
                        int n1 = Integer.parseInt(num1.getText().toString());
                        int n2 = Integer.parseInt(num2.getText().toString());
                        String opr = operator.getText().toString();
                        int resultActual = 0;
                        switch (opr) {
                            case "+":
                                resultActual = n1 + n2;
                                break;
                            case "-":
                                resultActual = n1 - n2;
                                break;
                            case "*":
                                resultActual = n1 * n2;
                                break;
                            case "/":
                                resultActual = n1 / n2;
                                break;
                        }
                        if (result == resultActual) {
                            status.setText("Correct");
                            status.setTextColor(Color.GREEN);
                            resultEntered.setTextColor(Color.GREEN);
                            nextLevel.setVisibility(View.VISIBLE);
                            updateScore();
                        } else {
                            status.setText("Wrong");
                            status.setTextColor(Color.RED);
                            resultEntered.setTextColor(Color.RED);
                            nextLevel.setVisibility(View.INVISIBLE);
                        }
                        status.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
    }
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int l =sh.getInt("level",0);
        int s = sh.getInt("score", 0);
        int n1 = sh.getInt("num1", 0);
        int n2 = sh.getInt("num2", 0);
        String o=sh.getString("op","");
        level.setText(String.valueOf(l));
        score.setText(String.valueOf(s));
        num1.setText(String.valueOf(n1));
        num2.setText(String.valueOf(n2));
        operator.setText(o);
    }

    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("level", Integer.parseInt(level.getText().toString()));
        myEdit.putInt("score", Integer.parseInt(score.getText().toString()));
        myEdit.putInt("num1", Integer.parseInt(num1.getText().toString()));
        myEdit.putInt("num2", Integer.parseInt(num2.getText().toString()));
        myEdit.putString("op",operator.getText().toString());
        myEdit.apply();
    }
}