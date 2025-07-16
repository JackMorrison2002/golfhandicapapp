package com.jackmorrison.golfhandicap;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDao {

    @Insert
    void insertScore(ScoreEntity score);

    @Delete
    void deleteScore(ScoreEntity score);

    @Query("SELECT * FROM score_table ORDER BY id DESC")
    List<ScoreEntity> getAllScores();

    @Query("SELECT * FROM score_table ORDER BY id DESC LIMIT 3")
    List<ScoreEntity> getLastThreeRounds();
}
