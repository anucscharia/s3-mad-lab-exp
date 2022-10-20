package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCE_NAME ="com.example.com.shared-preferences";
    public static final String SHARED_PREFERENCE_KEY_NUM1 ="NUM1";
    public static final String SHARED_PREFERENCE_KEY_NUM2 ="NUM2";
    public static final String SHARED_PREFERENCE_KEY_LEVEL ="LEVEL";
    public static final String SHARED_PREFERENCE_KEY_SCORE ="SCORE";
    public static final String SHARED_PREFERENCE_KEY_OPERATOR = "OPERATOR";

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
        int scr = Integer.parseInt(score.getText().toString());
        scr = scr + 10;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_KEY_SCORE, scr);
        editor.commit();
        score.setText("" + scr);
    }
    private void updateLevel() {
        int lvl = Integer.parseInt(level.getText().toString());
        lvl = lvl + 1;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_KEY_LEVEL, lvl);
        editor.commit();
    }

    private void resetUI() {
        Random r = new Random();
        int lvl = sharedPreferences.getInt(SHARED_PREFERENCE_KEY_LEVEL, 1);
        int scr = sharedPreferences.getInt(SHARED_PREFERENCE_KEY_SCORE, 0);
        int r1 = sharedPreferences.getInt(SHARED_PREFERENCE_KEY_NUM1, 0);
        int r2 = sharedPreferences.getInt(SHARED_PREFERENCE_KEY_NUM2, 0);
        String opr = sharedPreferences.getString(SHARED_PREFERENCE_KEY_OPERATOR,"");
       if((r1==0&&r2==0)||(r1==Integer.parseInt(num1.getText().toString()))||(r2==Integer.parseInt(num2.getText().toString()))) {
            r1 = r.nextInt(lvl * 10);
            r2 = r.nextInt(lvl * 10);
        }
        int op;
        if(opr=="") {
            opr = "+";
            op = r.nextInt(3);
            switch (op) {
                case 3:
                    opr = "/";
                    if (r1 < r2) {
                        int temp=r1;
                        r1=r2;
                        r2=temp;
                    }
                    break;
                case 2:
                    opr = "*";
                    break;
                case 1:
                    opr = "-";
                    if (r1 < r2) {
                        int temp=r1;
                        r1=r2;
                        r2=temp;
                    }
                    break;
            }
        }
        num1.setText("" + r1);
        num2.setText("" + r2);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_KEY_NUM1,r1);
        editor.putInt(SHARED_PREFERENCE_KEY_NUM2,r2);
        editor.putString(SHARED_PREFERENCE_KEY_OPERATOR, String.valueOf(opr));
        editor.commit();
        num1.setTextColor(Color.RED);
        num2.setTextColor(Color.RED);
        score.setText("" + scr);
        level.setText("" + lvl);
        operator.setText(opr);
        status.setText("");
        resultEntered.setText("");
        resultEntered.setTextColor(Color.BLACK);
        nextLevel.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        num1 = (TextView) findViewById(R.id.num1);
        num2 = (TextView) findViewById(R.id.num2);
        operator = (TextView) findViewById(R.id.operator);

        resultEntered = (EditText) findViewById(R.id.result);
        level = (TextView) findViewById(R.id.levelvalue);
        score = (TextView) findViewById(R.id.scorevalue);
        status = (TextView) findViewById(R.id.status);
        nextLevel = (Button) findViewById(R.id.next);
        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLevel();
                resetUI();
            }
        });
        resultEntered.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    if (!TextUtils.isEmpty(resultEntered.getText().toString())) {
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
                    }
                }
                return false;
            }
        });
        resetUI();
    }
}