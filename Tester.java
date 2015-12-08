import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class Tester {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(MoveTests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(result.getRunCount());
			System.out.println(failure.toString());
		}

		System.out.println(result.getRunCount() + " tests were run.");
		System.out.println(result.wasSuccessful());
	}
}