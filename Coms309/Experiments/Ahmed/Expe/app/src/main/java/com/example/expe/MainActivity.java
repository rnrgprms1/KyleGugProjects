package com.example.expe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);




    }
    public void app(View view) {
        final String[] motivations= new String[5];

        motivations[0] = "You can do it!";
        motivations[1] = "I Believe in you!";
        motivations[2] = "Good, better, best. Never let it rest. 'Til your good is better and your better is best.";
        motivations[3] = "The Way To Get Started Is To Quit Talking And Begin Doing.";
        motivations[4] = "We May Encounter Many Defeats But We Must Not Be Defeated.";






        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView motive = findViewById(R.id.textView3);
                int random = new Random().nextInt(5);

                motive.setText(motivations[random]);

            }
        });

    }
}
