package com.example.lab2;

import android.graphics.drawable.Drawable;

/**
 * Represents a player with name, score, and avatar
 * Implements Comparable for sorting by score (descending)
 */
public class Player implements Comparable<Player> {
    private String playerName;
    private int playerScore;
    private Drawable playerAvatar; // Player's avatar image

    // Constructor
    public Player(String playerName, int playerScore, Drawable playerAvatar) {
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.playerAvatar = playerAvatar;
    }

    // Getters
    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Drawable getPlayerAvatar() {
        return playerAvatar;
    }

    // Sort by score (highest first)
    @Override
    public int compareTo(Player other) {
        return Integer.compare(other.playerScore, this.playerScore);
    }
}
