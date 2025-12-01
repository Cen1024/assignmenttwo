package com.example.lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the game leaderboard (max 5 players)
 * Uses Singleton pattern to ensure only one instance
 */
public class Leaderboard {
    public static final int MAX_LEADERBOARD_SIZE = 5;
    private List<Player> playerList;
    private static Leaderboard instance; // Singleton instance

    // Private constructor (Singleton)
    private Leaderboard() {
        playerList = new ArrayList<>();
    }

    // Get singleton instance
    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    // Add player to leaderboard and sort
    public void add(Player player) {
        playerList.add(player);
        Collections.sort(playerList); // Sort by score descending

        // Keep only top 5 players
        if (playerList.size() > MAX_LEADERBOARD_SIZE) {
            playerList.remove(MAX_LEADERBOARD_SIZE);
        }
    }

    // Get leaderboard list
    public List<Player> getPlayerList() {
        return playerList;
    }
}
