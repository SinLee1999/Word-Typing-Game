package com.sinlee.wordtyping;
/*
 * This class is the profile of the player
 */
public class Player {
	private String nickName;
	private int score;
	private Stats difficulty; //for level

	//player chooses name and difficulty
	public Player(String nickname, Stats difficulty) {
		this.nickName = nickname;
		this.difficulty = difficulty;
		this.score = 0;// set score to 0
	}

	public int getScore() {
		return score;
	}

	//convert score to String to use as label later (gui)
	public String scoreToString() {
		String s = String.valueOf(score);
		return s;
	}

	public void addScore(int length) {//1 letter = 1 point
		score += length;
	}

	public Stats getDifficulty() {
		return difficulty;
	}

	public String getNickName() {
		return nickName;
	}
}
