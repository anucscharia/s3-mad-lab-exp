package com.example.a4_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView res,text;
    Double sum;
    String op,r,t,last,str;
    int s,i,status_add,status_div,status_mul,status_sub,status_dot=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res=findViewById(R.id.res);
        text=findViewById(R.id.text);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("1");
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("2");
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("3");
            }
        });
        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("4");
            }
        });
        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("5");
            }
        });
        findViewById(R.id.btn_6).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("6");
            }
        });
        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("7");
            }
        });
        findViewById(R.id.btn_8).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("8");
            }
        });
        findViewById(R.id.btn_9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("9");
            }
        });
        findViewById(R.id.btn_0).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("10");
            }
        });
        findViewById(R.id.dot).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(status_dot!=1) {
                    addvalue( ".");
                    status_dot=1;
                }
            }
        });
        findViewById(R.id.button_open_bracket).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue("(");
            }
        });
        findViewById(R.id.button_close_bracket).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addvalue(")");
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==status_add) {
                    last = text.getText().toString();
                    op = "+";
                    status_add=1;
                    if ((last.endsWith("-")) || (last.endsWith("*")) || (last.endsWith("/"))) {
                        text.setText(last.substring(0, last.length() - 1));
                        status_div=status_mul=status_sub=0;
                    }
                    text.setText(text.getText().toString() + op);
                }
                status_dot=0;
            }
        });
        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==status_sub) {
                    last = text.getText().toString();
                    op = "-";
                    status_sub=1;
                    if ((last.endsWith("+")) || (last.endsWith("*")) || (last.endsWith("/"))) {
                        text.setText(last.substring(0, last.length() - 1));
                        status_add=status_div=status_mul=0;
                    }
                    text.setText(text.getText().toString() + op);
                }
                status_dot=0;
            }
        });
        findViewById(R.id.mul).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==status_mul) {
                    last = text.getText().toString();
                    op = "*";
                    status_mul=1;
                    if ((last.endsWith("-")) || (last.endsWith("+")) || (last.endsWith("/"))) {
                        text.setText(last.substring(0, last.length() - 1));
                        status_add=status_div=status_sub=0;
                    }
                    text.setText(text.getText().toString() + op);
                }
                status_dot=0;
            }
        });
        findViewById(R.id.div).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==status_div) {
                    last = text.getText().toString();
                    op = "/";
                    status_div=1;
                    if ((last.endsWith("-")) || (last.endsWith("*")) || (last.endsWith("+"))) {
                        text.setText(last.substring(0, last.length() - 1));
                        status_add=status_mul=status_sub=0;
                    }
                    text.setText(text.getText().toString() + op);
                }
                status_dot=0;
            }
        });
        findViewById(R.id.eq).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                str=text.getText().toString();
                if ((str.endsWith("+")) ||(str.endsWith("-")) || (str.endsWith("*")) || (str.endsWith("/"))) {
                    text.setText(str.substring(0, str.length() - 1));
                    s=calculate();
                    res.setText(""+s);
                }
                else {
                    s = calculate();
                    text.setText("" + s);
                    res.setText("");
                }
                status_dot = 0;
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                str=text.getText().toString();
                text.setText(str.substring(0, str.length() - 1));
                str=text.getText().toString();
                s=calculate();
                res.setText(""+s);
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text.setText("");
                res.setText("");
            }
        });
    }
    public void addvalue(String str){
        text.setText(text.getText().toString() +str);
        status_add=status_div=status_mul=status_sub=0;
        s=calculate();
        res.setText(""+s);
    }
    public int calculate(){
        str=text.getText().toString();
        s=evaluate(str);
        status_sub=status_add=status_div=status_mul=0;
        return s;
    }
    public int evaluate (String exp)
    {
        Stack<Integer> operands = new Stack<>();  //Operand stack
        Stack<Character> operations = new Stack<>();  //Operator stack
        for (i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c))   //check if it is number
            {
                //Entry is Digit, and it could be greater than a one-digit number
                int num = 0;
                while (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                    i++;
                    if (i < exp.length()) {
                        c = exp.charAt(i);
                    } else {
                        break;
                    }
                }
                i--;
                operands.push(num);
            } else if (c == '(') {
                operations.push(c);   //push character to operators stack
            }
            //Closed brace, evaluate the entire brace
            else if (c == ')') {
                while (operations.peek() != '(') {
                    int output = performOperation(operands, operations);
                    operands.push(output);   //push result back to stack
                }
                operations.pop();
            }

            // current character is operator
            else if (isOperator(c)) {
                while (!operations.isEmpty() && precedence(c) <= precedence(operations.peek())) {
                    int output = performOperation(operands, operations);
                    operands.push(output);   //push result back to stack
                }
                operations.push(c);   //push the current operator to stack
            }
        }

        while (!operations.isEmpty()) {
            int output = performOperation(operands, operations);
            operands.push(output);   //push final result back to stack
        }
        return operands.pop();
    }

    static int precedence ( char c)
    {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public int performOperation (Stack < Integer > operands, Stack < Character > operations)
    {
        int a = operands.pop();
        int b = operands.pop();
        char operation = operations.pop();
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                if (a != 0)
                    return b / a;
        }
        return 0;
    }

    public boolean isOperator ( char c)
    {
        return (c == '+' || c == '-' || c == '/' || c == '*' || c == '^');
    }

}
