import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MoveTests {

	@Test
	// Ensures the player is able to move right
	public void testRight() {
		String input = "d";
		String methodOutput = CopsAndRobbers.move(input);
		String expected = "d";
		assertEquals(expected, methodOutput);
	}
	
	@Test
	// Ensures the player is able to move up
	public void testUp() {
		String input = "w";
		String methodOutput = CopsAndRobbers.move(input);
		String expected = "w";
		assertEquals(expected, methodOutput);
	}

	@Test
	// Ensures the player is able to move left
	public void testLeft() {
		String input = "a";
		String methodOutput = CopsAndRobbers.move(input);
		String expected = "a";
		assertEquals(expected, methodOutput);
	}

	@Test
	// Ensures the player is able to move down
	public void testDown() {
		String input = "s";
		String methodOutput = CopsAndRobbers.move(input);
		String expected = "s";
		assertEquals(expected, methodOutput);
	}
}
