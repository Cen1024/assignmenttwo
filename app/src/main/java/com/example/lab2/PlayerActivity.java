package com.example.lab2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Player screen: input name, select avatar, submit score
 */
public class PlayerActivity extends AppCompatActivity {
    private TextView tvPlayerScore;
    private EditText etPlayerName;
    private RadioGroup rgAvatar;
    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Get score from GameActivity
        finalScore = getIntent().getIntExtra("SCORE", 0);

        // Bind UI elements
        tvPlayerScore = findViewById(R.id.tv_player_score);
        etPlayerName = findViewById(R.id.et_player_name);
        rgAvatar = findViewById(R.id.rg_avatar);

        // Display final score
        tvPlayerScore.setText("Score: " + finalScore);
    }

    // Submit button click: save player and go to leaderboard
    public void onClickSubmit(View view) {
        String playerName = etPlayerName.getText().toString().trim();
        // Set default name if empty
        if (playerName.isEmpty()) {
            playerName = "Anonymous";
        }

        // Get selected avatar
        Drawable avatar = getSelectedAvatar();

        // Create player and add to leaderboard
        Player player = new Player(playerName, finalScore, avatar);
        Leaderboard.getInstance().add(player);

        // Navigate to LeaderboardActivity
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
        finish(); // Close PlayerActivity
    }

    // Get avatar based on selected RadioButton
    private Drawable getSelectedAvatar() {
        int selectedId = rgAvatar.getCheckedRadioButtonId();

        if (selectedId == R.id.rb_blue) {
            return getResources().getDrawable(R.drawable.img_blue_mole, getTheme());
        } else if (selectedId == R.id.rb_orange) {
            return getResources().getDrawable(R.drawable.img_orange_mole, getTheme());
        } else if (selectedId == R.id.rb_green) {
            return getResources().getDrawable(R.drawable.img_green_mole, getTheme());
        } else if (selectedId == R.id.rb_purple) {
            return getResources().getDrawable(R.drawable.img_purple_mole, getTheme());
        } else if (selectedId == R.id.rb_pink) {
            return getResources().getDrawable(R.drawable.img_pink_mole, getTheme());
        } else {
            // 默认返回灰色头像
            return getResources().getDrawable(R.drawable.img_grey_mole, getTheme());
        }

    }
}
