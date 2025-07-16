package com.jackmorrison.golfhandicap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import java.util.List;

public class RoundsHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RoundAdapter adapter;
    private AppDatabase db;
    private ScoreDao scoreDao;
    private List<ScoreEntity> rounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds_history);

        recyclerView = findViewById(R.id.recyclerRounds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);
        scoreDao = db.scoreDao();

        loadRounds();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ScoreEntity score = rounds.get(position);
                scoreDao.deleteScore(score);
                Toast.makeText(RoundsHistoryActivity.this, "Round deleted", Toast.LENGTH_SHORT).show();
                loadRounds();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void loadRounds() {
        rounds = scoreDao.getAllScores();
        adapter = new RoundAdapter(rounds);
        recyclerView.setAdapter(adapter);
    }
}
