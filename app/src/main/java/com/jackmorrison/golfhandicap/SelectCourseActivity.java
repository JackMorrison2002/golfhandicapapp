package com.jackmorrison.golfhandicap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.jackmorrison.golfhandicap.R;


import java.util.*;

public class SelectCourseActivity extends AppCompatActivity {

    // ✅ Views
    private AutoCompleteTextView autoCompleteCourses;
    private Button btnConfirmCourse;

    // ✅ Fallback static course list
    private String[] staticCourses = {
            "Foyle Golf Club",
            "City of Derry Golf Club",
            "Ballyliffin Golf Club",
            "Royal Portrush Golf Club",
            "Castlerock Golf Club",
            "Royal County Down",
            "Ardglass Golf Club",
            "Lahinch Golf Club",
            "Portmarnock Golf Club",
            "Adare Manor Golf Club",
            "Druids Glen Golf Club",
            "Carton House Montgomerie",
            "The K Club Palmer North",
            "Fota Island Golf Club",
            "Rosapenna Sandy Hills",
            "Carne Golf Links",
            "County Louth Golf Club",
            "The Island Golf Club",
            "Tralee Golf Club",
            "Donegal Golf Club (Murvagh)",
            "Waterville Golf Links",
            "Old Head Golf Links",
            "Enniscrone Golf Club",
            "Mount Juliet Estate",
            "Dooks Golf Club",
            "Castlemartyr Resort",
            "Carton House O'Meara",
            "Royal Belfast Golf Club",
            "Portstewart Golf Club",
            "Belvoir Park Golf Club",
            "Lough Erne Resort",
            "Galgorm Castle Golf Club",
            "Slieve Russell Golf Club",
            "Seapoint Golf Links",
            "Powerscourt Golf Club (East)",
            "Powerscourt Golf Club (West)",
            "Glasson Lakehouse Golf Club",
            "The Heritage Golf Resort",
            "Knightsbrook Golf Club",
            "Tulfarris Golf Resort",
            "Headfort Golf Club (New)",
            "Headfort Golf Club (Old)",
            "The European Club",
            "Greystones Golf Club",
            "Royal Dublin Golf Club",
            "Dun Laoghaire Golf Club",
            "Bray Golf Club",
            "Grange Golf Club",
            "Millicent Golf Club"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        // ✅ Link views
        autoCompleteCourses = findViewById(R.id.autoCompleteCourses);
        btnConfirmCourse = findViewById(R.id.btnConfirmCourse);

        // ✅ Sort course list alphabetically
        List<String> courseList = Arrays.asList(staticCourses);
        Collections.sort(courseList);

        // ✅ Set adapter for AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item, // ✅ Using same style for consistency
                courseList
        );
        autoCompleteCourses.setAdapter(adapter);

        // ✅ Show dropdown immediately on click
        autoCompleteCourses.setOnClickListener(view -> autoCompleteCourses.showDropDown());

        // ✅ Confirm button logic
        btnConfirmCourse.setOnClickListener(view -> {
            String selectedCourse = autoCompleteCourses.getText().toString().trim();

            if (!courseList.contains(selectedCourse)) {
                Toast.makeText(this, "Please select a valid course!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Selected: " + selectedCourse, Toast.LENGTH_SHORT).show();

            // ✅ Pass course to AddScoreActivity
            Intent intent = new Intent(this, AddScoreActivity.class);
            intent.putExtra("COURSE_NAME", selectedCourse);
            startActivity(intent);
        });
    }
}
