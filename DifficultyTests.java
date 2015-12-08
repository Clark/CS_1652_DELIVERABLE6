import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.File;

public class DifficultyTests {

	@Test
	public void testDifficultyEasy() {
		File methodOutput = CopsAndRobbers.chooseDifficulty("easy");
		File expected = new File("easy.txt");
		assertEquals(expected, methodOutput);
	}

	@Test
	public void testDifficultyHard() {
		File methodOutput = CopsAndRobbers.chooseDifficulty("hard");
		File expected = new File("hard.txt");
		assertEquals(expected, methodOutput);
	}

	@Test
	public void testDifficultyIncorrect() {
		File methodOutput = CopsAndRobbers.chooseDifficulty("blargh");
		File expected = new File("easy.txt");
		assertEquals(expected, methodOutput);
	}

}