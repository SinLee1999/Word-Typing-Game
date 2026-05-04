package com.sinlee.wordtyping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is about reading file and putting them into list.
 * easy = length < 6, medium = 7 - 11, hard = length > 11.
 */
public class FileRead {
	private List<String> easyWords = new ArrayList<>();
	private List<String> mediumWords = new ArrayList<>();
	private List<String> hardWords = new ArrayList<>();

	public void loadWords() {
		Scanner scan = null;
		try {
			FileReader f = new FileReader("words.txt");
			scan = new Scanner(f);
			while (scan.hasNext()) {// get next token
				String word = scan.next();// store the word from the file

				/*
				 *Create lists based on the word's length for different levels. 
				 */
				if (word.length() <= 6) {
					easyWords.add(word);
				} else if (word.length() > 6 && word.length() <= 11) {
					mediumWords.add(word);
				} else {// add to hard difficulty
					hardWords.add(word);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFound");
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}

	public List<String> getEasyWords() {
		return easyWords;
	}

	public List<String> getMediumWords() {
		return mediumWords;
	}

	public List<String> getHardWords() {
		return hardWords;
	}
}
