import java.util.Scanner;
import java.lang.System;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class CopsAndRobbers {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";

	// Global variables for game
	// Initialized so junit tests can run
		// Actual maze
	static char[][] maze = new char[10][10];
		// Character position
	static int x = 5;
	static int y = 5;
		// Game state
	static boolean game = true;
		// Player score
	static int score = 0;
		// Player character
	static String character = "";


	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("format: java Maze [difficulty]");
			System.out.println("where [difficulty] is easy/hard");
			System.exit(0);
		}

		Scanner mScan = new Scanner("");
		File mazeFile = new File("filler.txt");
		mazeFile = chooseDifficulty(args[0]);

		try {
			mScan = new Scanner(mazeFile);
		}
		catch(FileNotFoundException e) {
			System.exit(0);
		}

		// gets number of rows & columns for 2d array creation
		int rowNumber = 0;
		int columnNumber = 0;
		String line;
		while(mScan.hasNextLine()) {
			line = mScan.nextLine();
			if(rowNumber == 0) columnNumber = line.length();
			rowNumber++;
		}

		// initialize 2d array for maze
		maze = new char[rowNumber][columnNumber];

		// Load maze a second time for copying into maze
		Scanner mScan2 = new Scanner("");
		try {
			mScan2 = new Scanner(mazeFile);
		}
		catch(FileNotFoundException e) {
			System.exit(0);
		}

		// Variables for the purpose of character
		x = 0;
		y = 0;

		// fills maze into 2d array
		int row = 0;
		//int column = 0;
		while(mScan2.hasNextLine()) {
			line = mScan2.nextLine();
			for(int i = 0; i < line.length(); i++) {
				maze[row][i] = line.charAt(i);
				if(line.charAt(i) == '\\') {
					x = row;
					y = i;
				}
			}
			row++;
		}
		mScan2.close();

		// welcome message
		System.out.println("\nWelcome to Cops and Robbers Simulator 9001!");

		// lets player choose a character to play (money to steal)
		System.out.println("What type of money are you stealing?");
		Scanner input = new Scanner(System.in);
		while (!character.equals("dollar") && !character.equals("cent") && !character.equals("yen")) {
			System.out.println("enter: dollar/cent/yen");
			character = input.nextLine();
		}
		chooseCharacter(character);

		System.out.println("You start to hear sirens blaring. Start moving! Press h for advice");

		// Score set
		if(args[0].equals("easy")) score = 80; // 59
		if(args[0].equals("hard")) score = 450; // 392


		String entry = "";

		// Game start
		while(game) {
			
			if(scoreDisplay(score).equals(ANSI_BLUE)) break;

			printMaze(rowNumber, columnNumber);

			input = new Scanner(System.in);
			entry = input.next();
			
			if(entry.equals("h")) displayHelp();
			else if(entry.equals("ff")) forfeit();
			else if(entry.equals("d") || entry.equals("a") || entry.equals("s") || entry.equals("w")) move(entry);

			
			else {
				// Decrement score if incorrect input is taken
				System.out.println("Stop fumbling around! The cops are catching up!");
				score--;
			}
			
		}

		System.exit(0);
	}

	// displays HELP
	public static void displayHelp() {
		System.out.println("SCORE is the amount of moves you have left before the cops catch up to you.");
		System.out.println("Enter ff to forfeit the goods");
		System.out.println("Enter w to move up");
		System.out.println("Enter a to move left");
		System.out.println("Enter d to move right");
		System.out.println("Enter s to move down");
	}

	// players chooses to give up
	public static void forfeit() {
		System.out.println("You decided to surrender to the cops.");
		System.out.println("GAME OVER");
		game = false;
	}

	// displays the maze
	public static void printMaze(int row, int col) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}

	// Handles score display
	// returns color that corresponds with player's score:
		// red < 10 || yellow < 20 || green > 20 || blue == 0
	public static String scoreDisplay(int score) {
		// Prints the score in different colors depending on how close the cops are
		if(score == 0) {
			System.out.println("\nYou were too slow! The cops caught up to you!");
			System.out.println("Score: " + ANSI_BLUE + score + ANSI_RESET);
			System.out.println("GAME OVER");
			return ANSI_BLUE;
		}
		else if(score < 10) {
			System.out.println("Score: " + ANSI_RED + score + ANSI_RESET);
			return ANSI_RED;
		}
		else if(score < 20) {
			System.out.println("Score: " + ANSI_YELLOW + score + ANSI_RESET);
			return ANSI_YELLOW;
		}
		else {
			System.out.println("Score: " + ANSI_GREEN + score + ANSI_RESET);
			return ANSI_GREEN;
		}
	}

	// Handles difficulty selection
	// returns requested file corresponding with difficulty (easy.txt/hard.txt)
	// returns easy.txt when input unsupported
	public static File chooseDifficulty(String difficulty) {
		if(difficulty.equals("easy")) return new File("easy.txt");
		else if(difficulty.equals("hard")) return new File("hard.txt");
		else {
			System.out.println("Incorrect input. Defaulting to easy difficulty.");
			return new File("easy.txt");
		}
	}

	// Handles character selection
	// returns 1/2/3 depending if the money being stolen is dollar/cent/yen
	// returns 0 on error
	public static int chooseCharacter(String character) {
		int ret = 0;
		if(character.equals("dollar")) {
			maze[x][y] = '$';
			ret = 1;
		}
		else if(character.equals("cent")) {
			maze[x][y] = '¢';
			ret = 2;
		}
		else if(character.equals("yen")) {
			maze[x][y] = '¥';
			ret = 3;
		}
		return ret;
	}

	// Handles character movement
	// returns w/a/s/d if the character successfully moves up/left/down/right
	// returns e on error
	public static String move(String entry) {
		if(entry.equals("d")) {
			if(maze[x][y + 1] == ' ') {
				// Update maze
				maze[x][y + 1] = maze[x][y];
				maze[x][y] = ' ';
				// Update character position
				y = y + 1;
			} else if(maze[x][y + 1] == '║') {
				scoreDisplay(score);
				System.out.println("Congratulations!\nYou've successfully escaped the cops with all of your money!");
				game = false;
			} else {
				System.out.println("You've hit a wall! The cops are catching up!");
			}
			score--;
			return "d"; // right
		}
		else if(entry.equals("a")) {
			if(maze[x][y - 1] == ' ') {
				// Update maze
				maze[x][y - 1] = maze[x][y];
				maze[x][y] = ' ';
				// Update character position
				y = y - 1;
			} else if(maze[x][y - 1] == '║') {
				scoreDisplay(score);
				System.out.println("Congratulations!\n You've successfully escaped the cops with all of your money!");
				game = false;
			} else {
				System.out.println("You've hit a wall! The cops are catching up!");
			}
			score--;
			return "a"; //left
		}
		else if(entry.equals("s")) {
			if(maze[x + 1][y] == ' ') {
				// Update maze
				maze[x + 1][y] = maze[x][y];
				maze[x][y] = ' ';
				// Update character position
				x = x + 1;
			} else if(maze[x + 1][y] == '═') {
				scoreDisplay(score);
				System.out.println("Congratulations!\n You've successfully escaped the cops with all of your money!");
				game = false;
			} else {
				System.out.println("You've hit a wall! The cops are catching up!");
			}
			score--;
			return "s";
		}
		else if(entry.equals("w")) {
			if(maze[x - 1][y] == ' ') {
				// Update maze
				maze[x - 1][y] = maze[x][y];
				maze[x][y] = ' ';
				// Update character position
				x = x - 1;
			} else if(maze[x - 1][y] == '═') {
				scoreDisplay(score);
				System.out.println("Congratulations!\n You've successfully escaped the cops with all of your money!");
				game = false;
			} else {
				System.out.println("You've hit a wall! The cops are catching up!");
			}
			score--;
			return "w";
		}
		// error 
		return "e";
	}
}
