package com.example.a4_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView e1,t1;
    Double sum;
    String op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.e1);
        t1=findViewById(R.id.t1);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "1");

            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "2");
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "3");
            }
        });
        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "4");
            }
        });
        findViewById(R.id.btn_5).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "5");
            }
        });
        findViewById(R.id.btn_6).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "6");
            }
        });
        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "7");
            }
        });
        findViewById(R.id.btn_8).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "8");
            }
        });
        findViewById(R.id.btn_9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "9");
            }
        });
        findViewById(R.id.btn_0).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "0");
            }
        });
        findViewById(R.id.dot).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + ".");
            }
        });
        findViewById(R.id.button_open_bracket).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + "(");
            }
        });
        findViewById(R.id.button_close_bracket).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText(e1.getText().toString() + ")");
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                t1.setText(t1.getText().toString() + e1.getText().toString());
                e1.setText("");
                op="+";
                t1.setText(t1.getText().toString() + op);
            }
        });
        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                t1.setText(t1.getText().toString() + e1.getText().toString());
                e1.setText("");
                op = "-";
                t1.setText(t1.getText().toString() + op);
            }
        });
        findViewById(R.id.mul).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                t1.setText(t1.getText().toString() + e1.getText().toString());
                e1.setText("");
                op = "*";
                t1.setText(t1.getText().toString() + op);
            }
        });
        findViewById(R.id.div).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                t1.setText(t1.getText().toString() + e1.getText().toString());
                e1.setText("");
                op = "/";
                t1.setText(t1.getText().toString() + op);
            }
        });
        findViewById(R.id.eq).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                t1.setText(t1.getText().toString()+e1.getText().toString());
                String str = t1.getText().toString();
                int s=evaluate(str);
                t1.setText(""+s);e1.setText("");
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String text=e1.getText().toString();
                e1.setText(text.substring(0, text.length() - 1));
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                e1.setText("");
                t1.setText("");
            }
        });
    }
        public int evaluate (String exp)
        {
            Stack<Integer> operands = new Stack<>();  //Operand stack
            Stack<Character> operations = new Stack<>();  //Operator stack
            for (int i = 0; i < exp.length(); i++) {
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
                    if (a == 0) {
                        System.out.println("Cannot divide by zero");
                        return 0;
                    }
                    return b / a;
            }
            return 0;
        }

        public boolean isOperator ( char c)
        {
            return (c == '+' || c == '-' || c == '/' || c == '*' || c == '^');
        }

    }