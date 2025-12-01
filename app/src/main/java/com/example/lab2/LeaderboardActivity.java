package com.example.lab2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

/**
 * Leaderboard screen: displays top 5 players
 */
public class LeaderboardActivity extends AppCompatActivity {
    // Arrays for leaderboard UI elements (5 entries)
    private ImageView[] ivAvatars = new ImageView[5];
    private TextView[] tvNames = new TextView[5];
    private TextView[] tvScores = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Bind UI elements to arrays
        ivAvatars[0] = findViewById(R.id.iv_leaderboard_avatar1);
        ivAvatars[1] = findViewById(R.id.iv_leaderboard_avatar2);
        ivAvatars[2] = findViewById(R.id.iv_leaderboard_avatar3);
        ivAvatars[3] = findViewById(R.id.iv_leaderboard_avatar4);
        ivAvatars[4] = findViewById(R.id.iv_leaderboard_avatar5);

        tvNames[0] = findViewById(R.id.tv_leaderboard_name1);
        tvNames[1] = findViewById(R.id.tv_leaderboard_name2);
        tvNames[2] = findViewById(R.id.tv_leaderboard_name3);
        tvNames[3] = findViewById(R.id.tv_leaderboard_name4);
        tvNames[4] = findViewById(R.id.tv_leaderboard_name5);

        tvScores[0] = findViewById(R.id.tv_leaderboard_score1);
        tvScores[1] = findViewById(R.id.tv_leaderboard_score2);
        tvScores[2] = findViewById(R.id.tv_leaderboard_score3);
        tvScores[3] = findViewById(R.id.tv_leaderboard_score4);
        tvScores[4] = findViewById(R.id.tv_leaderboard_score5);

        // Load and display leaderboard data
        loadLeaderboardData();
    }

    // Populate leaderboard with player data
    private void loadLeaderboardData() {
        List<Player> playerList = Leaderboard.getInstance().getPlayerList();

        // Fill each leaderboard entry
        for (int i = 0; i < 5; i++) {
            if (i < playerList.size()) {
                Player player = playerList.get(i);
                ivAvatars[i].setImageDrawable(player.getPlayerAvatar());
                tvNames[i].setText(player.getPlayerName());
                tvScores[i].setText(String.valueOf(player.getPlayerScore()));
            } else {
                // Empty entry: default values
                ivAvatars[i].setImageResource(R.drawable.img_grey_mole);
                tvNames[i].setText("--");
                tvScores[i].setText("0");
            }
        }
    }
}