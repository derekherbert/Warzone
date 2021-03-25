package warzone.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test all test class of package warzone.model
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		AdvanceOrderTest.class,
		//AirliftOrder.class,
		BlockadeOrderTest.class,
		BombOrderTest.class,
		CountryTest.class,
		DeployOrderTest.class,
		NegotiateOrderTest.class,
		WarzonePropertiesTest.class
})
public class ModelTestSuite {

}
