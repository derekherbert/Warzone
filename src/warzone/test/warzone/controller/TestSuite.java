package warzone.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test all test class of package warzone.controller
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CommonControllerTest.class,
	ContinentControllerTest.class,CountryControllerTest.class,
	ErrorControllerTest.class,GameplayControllerTest.class,
	MapControllerTest.class,NeighborControllerTest.class,
	StartupControllerTest.class})
public class TestSuite {

}
