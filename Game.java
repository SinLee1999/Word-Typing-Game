package com.sinlee.wordtyping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *This class manages the core logic of the game.
 *Interacts with Player, Stats, and FileRead to control the flow of the game. 
 **/
public class Game {
	private static int highScore = 0;
	private Player player;
	private List<String> words;
	private int count = 0;
	private String currentWord;
	private Random random = new Random();
	private Map<String, Stats> diffMap = new HashMap<>();

	public Game() {
		diffMap.put("easy", new Stats(5, 10)); // 5 lives, 10 total words
		diffMap.put("medium", new Stats(4, 15));
		diffMap.put("hard", new Stats(2, 20));
	}

	/**
	 * Returns a list of words based on the selected difficulty level
	 * 
	 * @param level -difficulty of the level
	 * @param fr    - list of words from file
	 * @return the list of words based on the given difficulty
	 */

	public void start(String level, String nickname, FileRead fr) {
		words = getList(level, fr);//update word list based on the user input
		Stats stats = diffMap.get(level);// get lives and # words depend on difficulty.
		player = new Player(nickname, stats);
		loadNextWord();
	}

	public List<String> getList(String level, FileRead fr) {
		if (level.equals("easy")) {
			return fr.getEasyWords();
		} else if (level.equals("medium")) {
			return fr.getMediumWords();
		} else {
			return fr.getHardWords();
		}
	}
	
	/*
	 *Compares user input with the original word and highlights incorrect characters.
	 */
	public String getErrorDisplay(String userInput, String original) {
		if (userInput.length() != original.length()) {
			return "Length mismatch! " + userInput.length() + " vs " + original.length();
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < original.length(); i++) {
			if (userInput.charAt(i) != original.charAt(i)) {
				result.append("(").append(userInput.charAt(i)).append(")");
			} else {
				result.append(original.charAt(i));
			}
		}

		return result.toString();
	}

	public String getCurrentWord() {
		return currentWord;
	}

	public Player getPlayer() {
		return player;
	}

	// current word gets next random word
	public void loadNextWord() {
		currentWord = words.get(random.nextInt(words.size()));
	}

	public boolean checkAnswer(String input) {
		if (input.equalsIgnoreCase(currentWord)) {// user input is correct
			player.addScore(currentWord.length());
			count++;
			return true;
		} else {// player input is wrong
			player.getDifficulty().lose1life();
			return false;
		}
	}

	public static int getHighScore() {
		return highScore;
	}

	public static void setHighScore(int playerScore) {
		highScore = playerScore;
	}

	public boolean isGameOver() {
		return player.getDifficulty().getLives() <= 0;
	}

	public boolean isVictory() {
		return count >= player.getDifficulty().getTotalWords();
	}

	public int getCount() {
		return count;
	}

	public void reset(String level, String nickname, FileRead fr) {// basically restart after game to keep highscore
		count = 0;
		words = getList(level, fr);
		Stats stats = diffMap.get(level);
		player = new Player(nickname, stats);
		loadNextWord();
	}
}
