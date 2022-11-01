package com.example.a4_calculator;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView res,text;
    Double s;
    String op,r,t,last,str;
    int i,operator,status_dot=0,open,close;
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
                text.setText(text.getText().toString() +"(");
                operator=status_dot=0;
                open=1;
                close=0;
            }
        });
        findViewById(R.id.button_close_bracket).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(open==1) {
                    text.setText(text.getText().toString() + ")");
                    operator= status_dot = 0;
                    close=1;
                    open=0;
                }
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==operator) {
                    last = text.getText().toString();
                    op = "+";
                    operator=1;
                    if ((last.endsWith("-")) || (last.endsWith("*")) || (last.endsWith("/"))) {
                        text.setText(last.substring(0, last.length() - 1));
                    }
                    text.setText(text.getText().toString() + op);
                }
                status_dot=0;
            }
        });
        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==operator) {
                    last = text.getText().toString();
                    op = "-";
                    operator=1;
                    if ((last.endsWith("+")) || (last.endsWith("*")) || (last.endsWith("/"))) {
                        text.setText(last.substring(0, last.length() - 1));
                    }
                    text.setText(text.getText().toString() + op);
                }
                status_dot=0;
            }
        });
        findViewById(R.id.mul).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==operator) {
                    last = text.getText().toString();
                    op = "*";
                    operator=1;
                    if ((last.endsWith("-")) || (last.endsWith("+")) || (last.endsWith("/"))) {
                        text.setText(last.substring(0, last.length() - 1));
                    }
                    text.setText(text.getText().toString() + op);
                }
                status_dot=0;
            }
        });
        findViewById(R.id.div).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(0==operator) {
                    last = text.getText().toString();
                    op = "/";
                    operator=1;
                    if ((last.endsWith("-")) || (last.endsWith("*")) || (last.endsWith("+"))) {
                        text.setText(last.substring(0, last.length() - 1));
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
                    str=str.substring(0, str.length() - 1);
                    if(str.endsWith("(")){
                        str=str.substring(0, str.length() - 1);
                        open=0;
                    }
                    if(open==1&&close==0) {
                        str=str+")";
                    }
                    s = calculate(str);
                    text.setText(""+s);
                }
                else if(str.endsWith("(")){
                    str=str.substring(0, str.length() - 1);
                    open=0;
                    if ((str.endsWith("+")) ||(str.endsWith("-")) || (str.endsWith("*")) || (str.endsWith("/"))) {
                        str=str.substring(0, str.length() - 1);
                    }
                    s = calculate(str);
                    text.setText(""+s);
                }
                else {
                    if(open==1&&close==0)
                        str=str+")";
                    s = calculate(str);
                    text.setText(""+s);
                }
                res.setText("");
                status_dot =open=close= 0;
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                str=text.getText().toString();
                text.setText(str.substring(0, str.length() - 1));
                String l=str.substring(str.length() -1);
                if((l=="+")||(l=="-")||(l=="*")||(l=="/"))
                    operator=0;
                else if(l==".")
                    status_dot=0;
                else if(l==")")
                    close=0;
                else if(l=="(")
                    open=0;
                str=text.getText().toString();
                if (!(str.endsWith("+")) &&!(str.endsWith("-")) && !(str.endsWith("*")) && !(str.endsWith("/"))&&!(str.endsWith("("))&&!(open==1&&close==0)) {
                    s = calculate(str);
                    res.setText(""+s);
                }
                else  if ((str.endsWith("+")) ||(str.endsWith("-")) || (str.endsWith("*")) || (str.endsWith("/"))) {
                    res.setText("");
                }
                else if(str.endsWith("(")){
                    res.setText("");
                }
                else if(open==1&&close==0) {
                    res.setText("");
                }
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                text.setText("");
                res.setText("");
                operator=status_dot=open=close=0;
            }
        });
    }
    public void addvalue(String str){
        text.setText(text.getText().toString() +str);
        operator=0;
        str=text.getText().toString();
        if ((str.endsWith("+")) ||(str.endsWith("-")) || (str.endsWith("*")) || (str.endsWith("/")))
            str=str.substring(0, str.length() - 1);
        if((open==1)&&(close==0))
            str=str+")";
        s=calculate(str);
        res.setText(""+s);
    }
    public Double calculate(String str){

        s=evaluate(str);
        operator=0;
        return s;
    }
    public Double evaluate(String expression) {
        System.out.println(expression);
        //stack for operands
        Stack<Double> numberStack = new Stack<>();

        //stack for characters
        Stack<Character> operatorStack = new Stack<>();

        char []expToken = expression.toCharArray();
        for(int i=0; i<expToken.length; i++) {

            char ch = expToken[i];

            //Handling space in the expression
            if(ch == ' ') {
                continue;
            }
            else if(Character.isDigit(ch) || ch == '.') {
                StringBuffer buff = new StringBuffer();
                while((i < expToken.length) && (Character.isDigit(expToken[i]) || expToken[i] == '.')) {
                    buff.append(expToken[i++]);
                }
                i--;
                numberStack.push(Double.parseDouble(buff.toString()));
            }
            else if(ch == '(') {
                //push to the operator stack
                operatorStack.push(ch);
            }
            else if(ch == ')') {
                while(operatorStack.peek() != '(') {
                    double output = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop());
                    numberStack.push(output);
                }
                operatorStack.pop();
            }
            else if(isOperator(ch)) {
                while(!operatorStack.isEmpty() && (precedence(ch) < precedence(operatorStack.peek()))) {
                    double output = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop());
                    numberStack.push(output);
                }
                operatorStack.push(ch);
            }
        }

        while(!operatorStack.isEmpty()) {
            double output = performOperation(numberStack.pop(), numberStack.pop(), operatorStack.pop());
            numberStack.push(output);
        }
        return numberStack.peek();
    }

    public int precedence(char op) {
        if(op == '*' || op == '/') {
            return 2;
        }else if(op == '+' || op == '-') {
            return 1;
        }else if(op == '^') {
            return 3;
        }
        return -1;
    }

    public boolean isOperator(char op) {
        return (op == '+' || op == '-' || op == '*' || op == '/' || op == '^');
    }

    public double performOperation(Double a, Double b, Character op) {
        switch(op) {
            case '+' : return a+b;
            case '-' : return b-a;
            case '*' : return a*b;
            case '^' : return Math.pow(b, a);
            case '/' :
                if(a == 0) {
                    throw new UnsupportedOperationException("Cannot divide by 0");
                }else {
                    return b/a;
                }
        }
        return 0;
    }
}