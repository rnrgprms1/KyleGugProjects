package com.example.myfirstwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        setContentView(R.layout.activity_main);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setTextSize(40);
        t1.setText("To write something at screen");
        layout.addView(t1);

        setContentView(layout);
    }
}
