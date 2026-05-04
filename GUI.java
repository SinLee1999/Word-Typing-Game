package com.sinlee.wordtyping;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * Handles Graphic User Interface for the typing game.
 * Manages screen transition from start to the game screen.
 */
public class GUI {

	private Game game = new Game();
	private FileRead fr = new FileRead();

	JFrame frame;
	JLabel wordLabel;
	JLabel scoreLabel;
	JLabel livesLabel;
	JLabel wordCountLabel;
	JTextField inputField;
	JLabel errorLabel = new JLabel("");
	JButton restartButton;
	JLabel highScoreLabel = new JLabel("High Score: " + Game.getHighScore());

	public GUI() {
		fr.loadWords(); // load words
		createStartScreen(); // first screen (pick level, nickname)
	}

	private void createStartScreen() {
		frame = new JFrame("Typing Game");
		frame.setSize(500, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel levelLabel = new JLabel("Choose level: easy / medium / hard");
		JTextField levelField = new JTextField(10);
		JLabel nameLabel = new JLabel("Enter your nickname:");
		JTextField nameField = new JTextField(10);

		JButton startButton = new JButton("Start Game");

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String level = levelField.getText().toLowerCase();// get user input of difficulty into lowercase String
				String name = nameField.getText();
	
				if (!level.equals("easy") && !level.equals("medium") && !level.equals("hard")) {//make sure user input is one of the level
					levelLabel.setText("Invalid level! Type easy / medium / hard");
					return;
			}

			// move to next screen
			game = new Game(); // reset game object
			game.start(level, name, fr);
			createGameScreen();
			}
		});

		panel.add(levelLabel);
		panel.add(levelField);
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(startButton);

		frame.add(panel);
		frame.setVisible(true);
	}

	private void createGameScreen() {
		frame.getContentPane().removeAll();// clear previous UI
		frame.repaint();// add new UI

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		restartButton = new JButton("Restart");
		restartButton.setVisible(false); // hidden during game

		wordLabel = new JLabel("Word: " + game.getCurrentWord());
		scoreLabel = new JLabel(game.getPlayer().getNickName() + "'s Score : 0");
		wordCountLabel = new JLabel("Word: 0");
		livesLabel = new JLabel("Lives : " + game.getPlayer().getDifficulty().getLives());
		inputField = new JTextField(15);

		inputField.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        handleInput();
		    }
		});

		panel.add(errorLabel);
		panel.add(wordLabel);
		panel.add(inputField);
		panel.add(scoreLabel);
		panel.add(wordCountLabel);
		panel.add(livesLabel);
		panel.add(restartButton);
		panel.add(highScoreLabel);

		frame.add(panel);
		frame.revalidate();

		restartButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        frame.dispose();
		        new GUI();
		    }
		});
	}

	private void handleInput() {
		String typed = inputField.getText(); // convert user input to String
		inputField.setText("");// flush text field

		boolean correct = game.checkAnswer(typed);

		if (correct) {// user input is correct
			scoreLabel.setText(game.getPlayer().getNickName() + "'s Score: " + game.getPlayer().getScore());
			wordCountLabel.setText("Word: " + game.getCount() + ",  Goal : " + game.getPlayer().getDifficulty().getTotalWords());
			errorLabel.setText("");// clear error (so it does not show again, confusion)
		} else { // user input is wrong
			livesLabel.setText("Lives: " + game.getPlayer().getDifficulty().getLives());
			String showError = game.getErrorDisplay(typed, game.getCurrentWord());
			errorLabel.setText(showError);
		}

		if (game.isGameOver()) {// player life hit 0
			wordLabel.setText("Game Over!");
			inputField.setEnabled(false);
			restartButton.setVisible(true);
			updateHighScore();
			return;
		}

		if (game.isVictory()) {// player successfully typed all words
			wordLabel.setText("You Win!");
			inputField.setEnabled(false);
			restartButton.setVisible(true);
			highScoreLabel.setText("HighScore: " + Game.getHighScore());
			updateHighScore();
			return;
		}

		game.loadNextWord();
		wordLabel.setText("Word: " + game.getCurrentWord());
	}

	/*
	 * method to save highest score
	 */
	private void updateHighScore() {
		int currentScore = game.getPlayer().getScore();// get player's current score

		if (currentScore > Game.getHighScore()) {// if current score is higher
			Game.setHighScore(currentScore);// update current score to highest score
		}
		highScoreLabel.setText("High Score: " + Game.getHighScore());
	}
}
