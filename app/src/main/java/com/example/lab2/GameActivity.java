package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Game screen: displays mole grid and handles game interaction
 */
public class GameActivity extends AppCompatActivity implements GameLogic.GameEndListener {
    private TextView tvTimer, tvScore;
    private ImageView[] moleHoles = new ImageView[9];
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Bind UI elements
        tvTimer = findViewById(R.id.tv_timer_text);
        tvScore = findViewById(R.id.tv_score_text);

        // Initialize mole hole array (1-9)
        moleHoles[0] = findViewById(R.id.iv_mole_1);
        moleHoles[1] = findViewById(R.id.iv_mole_2);
        moleHoles[2] = findViewById(R.id.iv_mole_3);
        moleHoles[3] = findViewById(R.id.iv_mole_4);
        moleHoles[4] = findViewById(R.id.iv_mole_5);
        moleHoles[5] = findViewById(R.id.iv_mole_6);
        moleHoles[6] = findViewById(R.id.iv_mole_7);
        moleHoles[7] = findViewById(R.id.iv_mole_8);
        moleHoles[8] = findViewById(R.id.iv_mole_9);

        // Initialize game logic
        gameLogic = new GameLogic(tvScore, tvTimer, moleHoles, this);
        gameLogic.startGame();
    }

    // Handle mole click event (from XML onClick)
    public void onMoleClick(View view) {
        // Find index of clicked mole hole
        int index = -1;
        for (int i = 0; i < moleHoles.length; i++) {
            if (view.getId() == moleHoles[i].getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            gameLogic.onMoleClicked(index);
        }
    }

    // Game end callback: navigate to PlayerActivity
    @Override
    public void onGameEnd(int finalScore) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra("SCORE", finalScore);
        startActivity(intent);
        finish(); // Close GameActivity
    }
}
