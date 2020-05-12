package com.example.worddrawing;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.worddrawing.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1= (Button) findViewById(R.id.buttonStart);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent a = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(a);
            }
        });
    }
}
