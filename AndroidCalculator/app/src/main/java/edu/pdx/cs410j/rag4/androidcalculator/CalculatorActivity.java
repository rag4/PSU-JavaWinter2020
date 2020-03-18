package edu.pdx.cs410J.rag4.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void addNumbers(View view) {
        EditText left = findViewById(R.id.left_operand);
        EditText right = findViewById(R.id.right_operand);

        String leftText = left.getText().toString();
        String rightText = right.getText().toString();

        int leftNumber = Integer.parseInt(leftText);
        int rightNumber = Integer.parseInt(rightText);

        int sumNumber = leftNumber + rightNumber;

        EditText sum = findViewById(R.id.sum_operand);
        sum.setText(String.valueOf(sumNumber));
    }
}
