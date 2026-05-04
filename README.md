# Java Word Typing Game

A GUI-based typing game built with Java Swing where players type randomly selected words based on difficulty levels while managing lives and score.

## Features

* Three difficulty levels: Easy, Medium, Hard
* Random word selection from a file
* Lives system based on selected difficulty
* Score system based on word length
* Error highlighting for incorrect characters
* High score tracking (resets each session)
* Interactive GUI using Java Swing

## How It Works

The project is structured using multiple classes to separate responsibilities:

* Game – Controls the main game loop, selects words, checks user input, and updates score and lives
* Player – Stores player information such as nickname and score
* Stats – Manages difficulty settings (lives and total word count)
* FileRead – Loads words from a file and categorizes them by difficulty
* GUI – Handles user input, rendering, and screen updates using Java Swing

This structure separates game logic from the user interface, making the code easier to maintain and extend.



## How to Run

1. Clone the repository
2. Make sure "words.txt" is in the project directory
3. Run the Driver class

## Screenshot
<img width="482" height="187" alt="image" src="https://github.com/user-attachments/assets/1ac60e3a-f191-4fe8-a062-672176d0cdb0" />




## Future Improvements

* Add a timer system for each word
* Save high scores to a file for persistence
* Improve UI design and layout
* Add combo/streak-based scoring



