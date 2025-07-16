package com.jackmorrison.golfhandicap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jackmorrison.golfhandicap.R;
import com.jackmorrison.golfhandicap.ScoreDao;

import java.util.List;

public class ViewHandicapActivity extends AppCompatActivity {

    private TextView textHandicap;
    private AppDatabase db;
    private ScoreDao scoreDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_handicap);

        textHandicap = findViewById(R.id.textHandicap);

        // ✅ Setup Room database
        db = AppDatabase.getInstance(this);
        scoreDao = db.scoreDao();

        // ✅ Get last 3 rounds
        List<ScoreEntity> rounds = scoreDao.getLastThreeRounds();

        if (rounds.size() < 3) {
            textHandicap.setText("Need at least 3 rounds to calculate handicap.");
        } else {
            double handicap = calculateHandicap(rounds);
            textHandicap.setText("Your Handicap: " + String.format("%.1f", handicap));
        }
    }

    private double calculateHandicap(List<ScoreEntity> rounds) {
        double totalDifferential = 0.0;
        for (ScoreEntity round : rounds) {
            double differential = (round.score - round.courseRating) * 113 / round.slopeRating;
            totalDifferential += differential;
        }
        return totalDifferential / rounds.size();
    }
}
