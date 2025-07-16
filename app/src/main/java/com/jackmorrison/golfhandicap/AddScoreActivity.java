package com.jackmorrison.golfhandicap;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import com.jackmorrison.golfhandicap.R;

public class AddScoreActivity extends AppCompatActivity {

    // ✅ Views
    private TextView textCourseName, textCourseRating, textSlopeRating;
    private EditText editScore;
    private Button btnSaveScore;

    // ✅ Database
    private AppDatabase db;
    private ScoreDao scoreDao;

    // ✅ Other variables
    private String selectedCourse;
    private Map<String, CourseDetails> courseDetailsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);

        // ✅ Linking views
        textCourseName = findViewById(R.id.textCourseName);
        textCourseRating = findViewById(R.id.textCourseRating);
        textSlopeRating = findViewById(R.id.textSlopeRating);
        editScore = findViewById(R.id.editScore);
        btnSaveScore = findViewById(R.id.btnSaveScore);

        // ✅ Setup database connection
        db = AppDatabase.getInstance(this);
        scoreDao = db.scoreDao();

        // ✅ Get selected course from previous activity
        selectedCourse = getIntent().getStringExtra("COURSE_NAME");
        textCourseName.setText("Course: " + selectedCourse);

        loadCourseDetails();

        CourseDetails details = courseDetailsMap.get(selectedCourse);
        if (details != null) {
            textCourseRating.setText("Course Rating: " + details.courseRating);
            textSlopeRating.setText("Slope Rating: " + details.slopeRating);
        } else {
            textCourseRating.setText("Course Rating: Unknown");
            textSlopeRating.setText("Slope Rating: Unknown");
        }

        // ✅ Handle Save Button
        btnSaveScore.setOnClickListener(view -> {
            String scoreText = editScore.getText().toString().trim();
            if (scoreText.isEmpty()) {
                Toast.makeText(this, "Please enter your score!", Toast.LENGTH_SHORT).show();
                return;
            }

            int score = Integer.parseInt(scoreText);

            // ✅ Block scores under 50
            if (score < 50) {
                Toast.makeText(this, "Score must be at least 50!", Toast.LENGTH_SHORT).show();
                return;
            }

            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            // ✅ Save to database
            ScoreEntity newScore = new ScoreEntity(
                    selectedCourse,
                    details.courseRating,
                    details.slopeRating,
                    score,
                    date
            );

            scoreDao.insertScore(newScore);
            Toast.makeText(this, "Score Saved!", Toast.LENGTH_SHORT).show();

            // ✅ Pop-up dialog after saving
            new AlertDialog.Builder(this, R.style.DialogTheme)
                    .setTitle("Score Saved")
                    .setMessage("Do you want to add another score?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        editScore.setText("");
                        dialog.dismiss();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                        finish();
                    })
                    .show();
        });
    }

    // ✅ Static course details
    private void loadCourseDetails() {
        courseDetailsMap.put("Royal Portrush Golf Club", new CourseDetails(73.1, 134));
        courseDetailsMap.put("Ballyliffin Golf Club (Old Links)", new CourseDetails(72.3, 130));
        courseDetailsMap.put("Lahinch Golf Club", new CourseDetails(72.0, 127));
        courseDetailsMap.put("Portmarnock Golf Club", new CourseDetails(71.0, 124));
        courseDetailsMap.put("Adare Manor Golf Club", new CourseDetails(74.4, 145));
        courseDetailsMap.put("Druids Glen Golf Club", new CourseDetails(71.2, 130));
        courseDetailsMap.put("Carton House Montgomerie", new CourseDetails(72.6, 136));
        courseDetailsMap.put("The K Club Palmer North", new CourseDetails(72.0, 134));
        courseDetailsMap.put("Fota Island Golf Club", new CourseDetails(71.8, 128));
        courseDetailsMap.put("Castlerock Golf Club", new CourseDetails(71.0, 123));
        courseDetailsMap.put("Enniscrone Golf Club", new CourseDetails(73.2, 132));
        courseDetailsMap.put("Donegal Golf Club (Murvagh)", new CourseDetails(73.4, 131));
        courseDetailsMap.put("The Island Golf Club", new CourseDetails(72.0, 133));
        courseDetailsMap.put("County Sligo Golf Club (Rosses Point)", new CourseDetails(72.9, 130));
        courseDetailsMap.put("Tralee Golf Club", new CourseDetails(72.3, 130));
        courseDetailsMap.put("Dooks Golf Club", new CourseDetails(71.0, 122));
        courseDetailsMap.put("Rosapenna Sandy Hills", new CourseDetails(73.5, 135));
        courseDetailsMap.put("Carne Golf Links", new CourseDetails(72.9, 134));
        courseDetailsMap.put("Galway Bay Golf Club", new CourseDetails(72.3, 129));
        courseDetailsMap.put("County Louth Golf Club (Baltray)", new CourseDetails(73.0, 132));
        courseDetailsMap.put("Royal Dublin Golf Club", new CourseDetails(72.0, 130));
        courseDetailsMap.put("Powerscourt Golf Club (East)", new CourseDetails(71.0, 127));
        courseDetailsMap.put("Glasson Lakehouse Golf Club", new CourseDetails(73.3, 131));
        courseDetailsMap.put("Slieve Russell Golf Club", new CourseDetails(72.2, 129));
        courseDetailsMap.put("Knightsbrook Golf Club", new CourseDetails(72.4, 132));
        courseDetailsMap.put("Royal Belfast Golf Club", new CourseDetails(70.3, 122));
        courseDetailsMap.put("Belvoir Park Golf Club", new CourseDetails(71.5, 127));
        courseDetailsMap.put("Royal County Down (Championship)", new CourseDetails(73.7, 142));
        courseDetailsMap.put("Ardglass Golf Club", new CourseDetails(71.2, 126));
        courseDetailsMap.put("Foyle Golf Club", new CourseDetails(69.5, 120));
        courseDetailsMap.put("City of Derry Golf Club", new CourseDetails(70.8, 125));

    }
}
