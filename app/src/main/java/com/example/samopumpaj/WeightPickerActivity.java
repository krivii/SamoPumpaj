package com.example.samopumpaj;

import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WeightPickerActivity extends AppCompatActivity {

    private NumberPicker wholeNumberPicker;
    private NumberPicker decimalNumberPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_picker);

        wholeNumberPicker = findViewById(R.id.wholeNumberPicker);
        decimalNumberPicker = findViewById(R.id.decimalNumberPicker);

        wholeNumberPicker.setMinValue(5);
        wholeNumberPicker.setMaxValue(150);
        wholeNumberPicker.setValue(20);

        decimalNumberPicker.setMinValue(0);
        decimalNumberPicker.setMaxValue(9);
        decimalNumberPicker.setValue(0);

    }
}
