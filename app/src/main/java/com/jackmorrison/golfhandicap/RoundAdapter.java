package com.jackmorrison.golfhandicap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.RoundViewHolder> {

    private List<ScoreEntity> rounds;

    public RoundAdapter(List<ScoreEntity> rounds) {
        this.rounds = rounds;
    }

    @Override
    public RoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_round, parent, false);
        return new RoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoundViewHolder holder, int position) {
        ScoreEntity round = rounds.get(position);
        holder.textCourse.setText(round.courseName + " | " + round.date);
        holder.textScore.setText("Score: " + round.score);
        holder.textRatingSlope.setText("Rating: " + round.courseRating + " | Slope: " + round.slopeRating);
    }

    @Override
    public int getItemCount() {
        return rounds.size();
    }

    static class RoundViewHolder extends RecyclerView.ViewHolder {
        TextView textCourse, textScore, textRatingSlope;

        public RoundViewHolder(View itemView) {
            super(itemView);
            textCourse = itemView.findViewById(R.id.textCourse);
            textScore = itemView.findViewById(R.id.textScore);
            textRatingSlope = itemView.findViewById(R.id.textRatingSlope);
        }
    }
}
