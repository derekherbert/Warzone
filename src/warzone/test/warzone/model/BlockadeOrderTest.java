package warzone.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for BlockadeOrder class
 */
public class BlockadeOrderTest {
	
	/**
	 * This method tests a valid order
	 */
	@Test
	public void validOrder() {
		Player l_player=new Player("player1");
		Country l_country=new Country(0,"country1");
		BlockadeOrder l_order=new BlockadeOrder(l_player, 0);
		assert(l_order.valid()==false);
		l_country.setOwner(l_player);
		l_player.getConqueredCountries().put(l_country.getCountryID(), l_country);
		assert(l_order.valid()==true);
		l_player.setIsAlive(false);
		assert(l_order.valid()==false);
	}
	
	/**
	 * Invalid order if target country not belong to player
	 */
	@Test
	public void invalidOrderIfTargetCountryNotBelongToPlayer() {
		Player l_player=new Player("player1");
		Country l_country=new Country(0,"country1");
		BlockadeOrder l_order=new BlockadeOrder(l_player, 0);
		assertFalse(l_order.valid());
	}
	
	/**
	 * Invalid order if player dead
	 */
	@Test
	public void invalidOrderIfPlayerDead() {
		Player l_player=new Player("player1");
		Country l_country=new Country(0,"country1");
		BlockadeOrder l_order=new BlockadeOrder(l_player, 0);
		l_country.setOwner(l_player);
		l_player.getConqueredCountries().put(l_country.getCountryID(), l_country);
		assert(l_order.valid());
		l_player.setIsAlive(false);
		assertFalse(l_order.valid());
	}
	
	/**
	 * blockade order will execute success
	 */
	@Test
	public void willBlockade() {
		Player l_player=new Player("player1");
		Country l_country=new Country(0,"country1");
		l_country.setArmyNumber(4);
		BlockadeOrder l_order=new BlockadeOrder(l_player, 0);
		l_country.setOwner(l_player);
		l_player.getConqueredCountries().put(l_country.getCountryID(), l_country);
		l_order.execute();
		assertEquals(l_country.getArmyNumber(),12);
	}
	
	
	/**
	 * check if success to generate the blockade order with right command
	 */
	@Test
	public void willCreateBlockadeOrder() {
		Player l_player = new Player("P1");
		Country l_country = new Country(1,"C1",0,0,null);
		l_country.setArmyNumber(5);
		l_country.setOwner(l_player);
		l_player.getConqueredCountries().put(l_country.getCountryID(), l_country);
		l_player.getCards().add(Card.BLOCKADE);
		Order l_order = l_player.conventOrder("blockade 1");
		assertNotEquals(l_order, null);
  }
  
  	
	/**
	 * This method tests the valid method of BlockadeOrder class
	 */
  @Test
	public void testValid() {
		d_player=new Player("player1");
		d_country=new Country(1,"country1");
		
		//act
		d_order=new BlockadeOrder(d_player, d_country);
		
		//assert
		assertFalse(d_order.valid());
	}
	
	/**
	 * This method tests the valid method of BlockadeOrder class
	 */
	@Test
	public void willFailedWhenNullCountry() {
		d_player=new Player("player1");
		
		//act
		d_order=new BlockadeOrder(d_player, null);
		
		//assert
		assertFalse(d_order.valid());
	}
	
	/**
	 * This method tests the valid method of BlockadeOrder class
	 */
	@Test
	public void willTrueWhenTargetCountryIsTheSameOwner() {
		d_player=new Player("player1");
		d_country=new Country(1,"country1");
		d_country.setOwner(d_player);
		
		//act
		d_order=new BlockadeOrder(d_player, d_country);
		
		//assert
		assertTrue(d_order.valid());
	}
	
	/**
	 * This method tests the valid method of BlockadeOrder class
	 */
	@Test
	public void willFailWhenTargetCountryIsDiplomacyInCurrentTurn() {
		d_player=new Player("player1");
		Player d_player2=new Player("player2");
		d_country=new Country(1,"country2");
		d_country.setOwner(d_player2);
		
		GameContext l_gameContext = GameContext.getGameContext();
		NegotiateOrder l_negotiateOrder = new NegotiateOrder(d_player, d_player2 );
		l_gameContext.addDiplomacyOrderToList(l_negotiateOrder);
		
		
		//act
		d_order=new BlockadeOrder(d_player, d_country);
		
		//assert
		assertFalse(d_order.valid());
	}
	
}
