package warzone.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class WarzonePropertiesTest {

	@Test
	public void warzonePropertiesTest() {

		WarzoneProperties.getWarzoneProperties();
		
		System.out.println(WarzoneProperties.GAME_MAP_DIRECTORY);
		
		assertTrue(WarzoneProperties.GAME_MAP_DIRECTORY != null);
	}
}
