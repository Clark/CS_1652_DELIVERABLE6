import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class CharacterTests {

	@Test
	public void testCharacterDollar() {
		boolean test = false;
		String input = "dollar";
		int methodOutput = CopsAndRobbers.chooseCharacter(input);
		int expected = 1;
		if(methodOutput == expected) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	public void testCharacterCent() {
		boolean test = false;
		String input = "cent";
		int methodOutput = CopsAndRobbers.chooseCharacter(input);
		int expected = 2;
		if(methodOutput == expected) {
			test = true;
		}
		assertTrue(test);
	}

	@Test
	public void testCharacterYen() {
		boolean test = false;
		String input = "yen";
		int methodOutput = CopsAndRobbers.chooseCharacter(input);
		int expected = 3;
		if(methodOutput == expected) {
			test = true;
		}
		assertTrue(test);
	}
}