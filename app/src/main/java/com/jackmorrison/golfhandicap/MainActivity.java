package com.jackmorrison.golfhandicap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.jackmorrison.golfhandicap.R;
import com.jackmorrison.golfhandicap.RoundsHistoryActivity;
import com.jackmorrison.golfhandicap.SelectCourseActivity;
import com.jackmorrison.golfhandicap.ViewHandicapActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAddScore, btnViewHandicap, btnViewRounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddScore = findViewById(R.id.btnAddScore);
        btnViewHandicap = findViewById(R.id.btnViewHandicap);
        btnViewRounds = findViewById(R.id.btnViewRounds);

        btnAddScore.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SelectCourseActivity.class)));

        btnViewHandicap.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ViewHandicapActivity.class)));

        btnViewRounds.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, RoundsHistoryActivity.class)));
    }
}
