package com.example.plusminusbuttonlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlusMinusButton extends RelativeLayout {
    private Button plusBtn, minusBtn;
    private TextView itemQuanEt;
    private int currentNumber;
    private int maxNumber;
    private int minNumber; // Added minNumber
    private OnClickListener plusClickListener;
    private OnClickListener minusClickListener;

    public PlusMinusButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlusMinusButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PlusMinusButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutInflater.from(context).inflate(R.layout.plus_minus_button, this, true);

        plusBtn = findViewById(R.id.addBtn);
        minusBtn = findViewById(R.id.removeBtn);
        itemQuanEt = findViewById(R.id.itemQuanEt);

        // Load attributes
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PlusMinusButton, defStyleAttr, defStyleRes);
        try {
            currentNumber = a.getInt(R.styleable.PlusMinusButton_initialNumber, 0);
            maxNumber = a.getInt(R.styleable.PlusMinusButton_finalNumber, Integer.MAX_VALUE);
            minNumber = 0; // Default min number
            float textSize = a.getDimension(R.styleable.PlusMinusButton_textSize, 13);
            int backgroundColor = a.getColor(R.styleable.PlusMinusButton_backGroundColor, 0);
            int textColor = a.getColor(R.styleable.PlusMinusButton_textColor, 0);
            Drawable backgroundDrawable = a.getDrawable(R.styleable.PlusMinusButton_backgroundDrawable);

            // Apply attributes
            itemQuanEt.setTextSize(textSize);
            itemQuanEt.setTextColor(textColor);
            plusBtn.setTextColor(textColor);
            minusBtn.setTextColor(textColor);

            if (backgroundDrawable != null) {
                backgroundDrawable.setColorFilter(new PorterDuffColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN));
                setBackground(backgroundDrawable);
            } else {
                setBackgroundColor(backgroundColor);
            }
        } finally {
            a.recycle();
        }

        // Set initial number after attributes are loaded
        setCurrentNumber(currentNumber);

        // Set click listeners
        plusBtn.setOnClickListener(v -> {
            if (currentNumber < maxNumber) {
                updateNumber(currentNumber + 1);
            }
            if (plusClickListener != null) plusClickListener.onClick(v);
        });

        minusBtn.setOnClickListener(v -> {
            if (currentNumber > minNumber) { // Check against minNumber
                updateNumber(currentNumber - 1);
            }
            if (minusClickListener != null) minusClickListener.onClick(v);
        });
    }

    private void updateNumber(int newNumber) {
        if (newNumber >= minNumber && newNumber <= maxNumber) {
            currentNumber = newNumber;
            itemQuanEt.setText(String.valueOf(currentNumber));
        }
    }

    public void setCurrentNumber(int number) {
        if (number >= minNumber && number <= maxNumber) {
            currentNumber = number;
            itemQuanEt.setText(String.valueOf(currentNumber));
        }
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setOnPlusClickListener(OnClickListener listener) {
        this.plusClickListener = listener;
    }

    public void setOnMinusClickListener(OnClickListener listener) {
        this.minusClickListener = listener;
    }

    // New setRange method
    public void setRange(int min, int max) {
        if (min <= max) {
            this.minNumber = min;
            this.maxNumber = max;
            // Adjust currentNumber if it is out of new range
            if (currentNumber < minNumber) {
                setCurrentNumber(minNumber);
            } else if (currentNumber > maxNumber) {
                setCurrentNumber(maxNumber);
            }
        } else {
            throw new IllegalArgumentException("min must be less than or equal to max");
        }
    }
}
