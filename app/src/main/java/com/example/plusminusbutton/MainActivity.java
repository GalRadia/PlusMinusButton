package com.example.plusminusbutton;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plusminusbuttonlibrary.PlusMinusButton;

public class MainActivity extends AppCompatActivity {
    private PlusMinusButton plusMinusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        plusMinusButton = findViewById(R.id.plusMinusButton);
        plusMinusButton.setRange(1, 5);
        plusMinusButton.setOnPlusClickListener(view -> {
            Toast.makeText(this, "Plus clicked the number is " + plusMinusButton.getCurrentNumber(), Toast.LENGTH_SHORT).show();
        });
        plusMinusButton.setOnMinusClickListener(view -> {
            Toast.makeText(this, "Minus clicked the number is " + plusMinusButton.getCurrentNumber(), Toast.LENGTH_SHORT).show();
        });

    }
}