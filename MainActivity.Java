package com.example.labdexp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    String current = "";
    int first = 0, second = 0;
    String op = "";
    boolean operatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);

        int numbers[] = {
            R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4,
            R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9
        };

        for(int id : numbers) {
            Button b = findViewById(id);
            b.setOnClickListener(v -> {
                Button btn = (Button)v;
                pressNumber(btn.getText().toString());
            });
        }

        findViewById(R.id.bplus).setOnClickListener(v -> setOp("+"));
        findViewById(R.id.bminus).setOnClickListener(v -> setOp("-"));
        findViewById(R.id.bmul).setOnClickListener(v -> setOp("*"));
        findViewById(R.id.bdiv).setOnClickListener(v -> setOp("/"));

        findViewById(R.id.bclear).setOnClickListener(v -> {
            show("Clear");
            current = "";
            first = second = 0;
            operatorPressed = false;
            txt.setText("0");
        });

        findViewById(R.id.beq).setOnClickListener(v -> {
            show("Pressed =");
            calculate();
        });
    }

    void pressNumber(String n) {

        show("Pressed " + n);

        current = current + n;
        txt.setText(current);

        if(!operatorPressed)
            first = Integer.parseInt(current);
        else
            second = Integer.parseInt(current);
    }

    void setOp(String o) {
        show("Pressed " + o);
        op = o;
        operatorPressed = true;
        current = "";
    }

    void calculate() {

        int result = 0;

        if(op.equals("+"))
            result = first + second;

        if(op.equals("-"))
            result = first - second;

        if(op.equals("*"))
            result = first * second;

        if(op.equals("/")) {
            if(second == 0) {
                txt.setText("Cannot divide by zero");
                return;
            }
            result = first / second;
        }

        txt.setText("Result: " + result);

        first = result;
        current = "";
    }

    void show(String s) {

        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

        AlertDialog.Builder d =
                new AlertDialog.Builder(MainActivity.this);

        d.setTitle("Info");
        d.setMessage(s);
        d.setPositiveButton("OK", null);
        d.show();
    }
}
