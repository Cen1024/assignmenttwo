package com.example.lab2;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Handles core game logic: mole spawning, scoring, and timing
 */
public class GameLogic {
    // Constants
    public static final long MOLE_DISPLAY_TIME = 1000; // Mole visible for 1 second
    public static final long GAME_DURATION = 30000;    // Game lasts 30 seconds

    private int currentScore = 0;
    private TextView tvScore;       // Score display TextView
    private TextView tvTimer;       // Timer display TextView
    private ImageView[] moleHoles;  // Array of 9 mole hole ImageViews
    private Handler moleHandler;    // Controls mole spawning loop
    private Runnable moleRunnable;
    private CountDownTimer countDownTimer;
    private Random random;          // Random for mole position
    private GameEndListener listener; // Listener for game end

    // Constructor
    public GameLogic(TextView tvScore, TextView tvTimer, ImageView[] moleHoles, GameEndListener listener) {
        this.tvScore = tvScore;
        this.tvTimer = tvTimer;
        this.moleHoles = moleHoles;
        this.listener = listener;
        this.moleHandler = new Handler();
        this.random = new Random();
    }

    // Start the game
    public void startGame() {
        currentScore = 0;
        updateScoreText();
        startMoleCycle();
        startCountdown();
    }

    // Spawn moles in a loop
    private void startMoleCycle() {
        moleRunnable = new Runnable() {
            @Override
            public void run() {
                int randomIndex = random.nextInt(9); // Random mole position (0-8)
                showMole(randomIndex);

                // Hide mole after half the display time
                moleHandler.postDelayed(() -> hideMole(randomIndex), MOLE_DISPLAY_TIME / 2);
                // Repeat loop
                moleHandler.postDelayed(this, MOLE_DISPLAY_TIME);
            }
        };
        moleHandler.post(moleRunnable);
    }

    // Show mole (change image)
    private void showMole(int index) {
        moleHoles[index].setImageResource(R.drawable.img_with_mole);
    }

    // Hide mole (reset image)
    private void hideMole(int index) {
        moleHoles[index].setImageResource(R.drawable.img_without_mole);
    }

    // Handle mole click (increase score)
    public void onMoleClicked(int index) {
        Context context = tvScore.getContext();
        // Check if mole is visible
        if (moleHoles[index].getDrawable().getConstantState() ==
                context.getResources().getDrawable(R.drawable.img_with_mole).getConstantState()) {
            currentScore++;
            updateScoreText();
            hideMole(index); // Hide mole immediately after click
        }
    }

    // Update score display
    private void updateScoreText() {
        tvScore.setText("Score: " + currentScore);
    }

    // Start game countdown
    private void startCountdown() {
        countDownTimer = new CountDownTimer(GAME_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                stopGame();
                listener.onGameEnd(currentScore); // Notify game end
            }
        }.start();
    }

    // Stop the game
    public void stopGame() {
        moleHandler.removeCallbacks(moleRunnable);
        countDownTimer.cancel();

        // Hide all moles
        for (ImageView moleHole : moleHoles) {
            moleHole.setImageResource(R.drawable.img_without_mole);
        }
    }

    // Get current score
    public int getCurrentScore() {
        return currentScore;
    }

    // Listener interface for game end
    public interface GameEndListener {
        void onGameEnd(int finalScore);
    }
}