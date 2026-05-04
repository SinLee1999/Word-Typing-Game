package com.sinlee.wordtyping;

/*
 * This class handles player's stats
 * 
 * Harder level = longer words and less lives with more word count
 */
public class Stats {
	int lives;
	int totalWords;

	public Stats(int lives, int totalWords) {
		this.lives = lives;
		this.totalWords = totalWords;
	}

	public int getLives() {
		return lives;
	}

	public int getTotalWords() {
		return totalWords;
	}

	public void lose1life() {
		lives = lives - 1;
	}

}
