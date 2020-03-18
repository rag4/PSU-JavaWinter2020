package edu.pdx.cs410J.rag4.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculatorActivity extends AppCompatActivity {

    private EditText left;
    private EditText right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        left = findViewById(R.id.left_operand);
        right = findViewById(R.id.right_operand);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            loadOperandsFromFile();
        } catch (IOException e) {
            Log.e("Calculator", "onStart: ", e);
        }
    }

    private void loadOperandsFromFile() throws IOException {
        File file = getOperandsFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        setRightOperandValue(reader.readLine());
        setLeftOperandValue(reader.readLine());

    }

    private void setRightOperandValue(String value) {
        this.right.setText(value);

    }

    private void setLeftOperandValue(String value) {
        this.left.setText(value);
    }

    @Override
    protected void onStop() {
        try {
            saveOperandsToFile();
        } catch (IOException e) {
            Log.e("Calculator", "onStop: ", e);
        }

        super.onStop();
    }

    private void saveOperandsToFile() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(getOperandsFile()));
        writer.println(getLeftOperandValue());
        writer.println(getRightOperandValue());
        writer.flush();
        writer.close();
    }

    private String getRightOperandValue() {
        return left.getText().toString();
    }

    private String getLeftOperandValue() {
        return right.getText().toString();
    }

    private File getOperandsFile() {
        File dir = getFilesDir();
        return new File(dir, "operands.txt");
    }

    public void addNumbers(View view) {

        String leftText = getLeftOperandValue();
        String rightText = getRightOperandValue();

        int leftNumber = Integer.parseInt(leftText);
        int rightNumber = Integer.parseInt(rightText);

        int sumNumber = leftNumber + rightNumber;

        EditText sum = findViewById(R.id.sum_operand);
        sum.setText(String.valueOf(sumNumber));
    }
}
