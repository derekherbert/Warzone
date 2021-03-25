package warzone.state;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test all test class of package warzone.state
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	IssueOrderTest.class,
	MapEditorTest.class,
	OrderExecutionTest.class,
	ReinforcementTest.class,
	StartupTest.class
	})
public class StateTestSuite {

}