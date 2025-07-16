package com.jackmorrison.golfhandicap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "score_table")
public class ScoreEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String courseName;
    public double courseRating;
    public int slopeRating;
    public int score;
    public String date;
}
