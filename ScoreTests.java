import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ScoreTests {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";

	@Test
	// Ensures scores > 0 && < 10 are displayed in red
	public void testRed() {
		int score = 5;
		String methodOutput = CopsAndRobbers.scoreDisplay(score);
		String expected = ANSI_RED;
		assertEquals(expected, methodOutput);
	}

	@Test
	// Ensures scores > 9 && < 20 are displayed in yellow
	public void testYellow() {
		int score = 15;
		String methodOutput = CopsAndRobbers.scoreDisplay(score);
		String expected = ANSI_YELLOW;
		assertEquals(expected, methodOutput);
	}

	@Test
	// Ensures scores > 20 are displayed in green
	public void testGreen() {
		int score = 55;
		String methodOutput = CopsAndRobbers.scoreDisplay(score);
		String expected = ANSI_GREEN;
		assertEquals(expected, methodOutput);
	}

	@Test
	// Ensures score when losing game is displayed in blue
	public void testBlue() {
		int score = 0;
		String methodOutput = CopsAndRobbers.scoreDisplay(score);
		String expected = ANSI_BLUE;
		assertEquals(expected, methodOutput);
	}
}
