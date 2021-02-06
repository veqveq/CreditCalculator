package ru.veqveq.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private double sum, percentage;
    private int duration;
    private TextView sumTxt, durationTxt, percentageTxt, annuity;
    private Switch sheduleSwitcher;
    private LinearLayout shedule;
    private TextView payNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTxt = findViewById(R.id.cash);
        durationTxt = findViewById(R.id.duration);
        percentageTxt = findViewById(R.id.percentage);
        annuity = findViewById(R.id.annuity);
        sheduleSwitcher = findViewById(R.id.sheduleSwitcher);
        payNumber = findViewById(R.id.payNumder);
        shedule = findViewById(R.id.shedule);
        shedule.setVisibility(View.INVISIBLE);
        annuity.setVisibility(View.INVISIBLE);
        sumTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                sumTxt.setHintTextColor(Color.GRAY);
                sumTxt.setHint("Сумма кредита, руб.");
            }
        });
        durationTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                durationTxt.setHintTextColor(Color.GRAY);
                durationTxt.setHint("Срок кредита, месяцев");
            }
        });
        percentageTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                percentageTxt.setHintTextColor(Color.GRAY);
                percentageTxt.setHint("Ставка, % годовых");
            }
        });
        sheduleSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sheduleSwitcher.isChecked()){
                    shedule.setVisibility(View.VISIBLE);
                }else{
                    shedule.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void calculate(View view) {
        if (checking()) {
            sum = Double.parseDouble(sumTxt.getText().toString());
            percentage = Double.parseDouble(percentageTxt.getText().toString()) / 100 / 12;
            duration = Integer.parseInt(durationTxt.getText().toString());
            double calculation = sum * (percentage * Math.pow((1 + percentage), duration)) / (Math.pow((1 + percentage), duration) - 1);
            annuity.setText(String.format("%.2f руб", calculation));
            annuity.setVisibility(View.VISIBLE);
        }
    }

    private boolean checking() {
        boolean checkResult = true;
        if (sumTxt.getText().toString().equals("")) {
            sumTxt.setHintTextColor(Color.RED);
            sumTxt.setHint("Введите сумму кретида!");
            annuity.setVisibility(View.INVISIBLE);
            checkResult = false;
        }
        if (durationTxt.getText().toString().equals("")) {
            durationTxt.setHintTextColor(Color.RED);
            durationTxt.setHint("Введите срок кредита!");
            annuity.setVisibility(View.INVISIBLE);
            checkResult = false;
        }
        if (percentageTxt.getText().toString().equals("")) {
            percentageTxt.setHintTextColor(Color.RED);
            percentageTxt.setHint("Введите процентную ставку!");
            annuity.setVisibility(View.INVISIBLE);
            checkResult = false;
        }
        return checkResult;
    }

    public void fillTable(View view) {
        payNumber.setText(payNumber.getText()+"\n 1");
    }
}