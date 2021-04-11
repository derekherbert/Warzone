package warzone.model;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 *	define of the RandomStrategy
 */
public class RandomStrategy extends PlayerStrategy {	
	
	/**
	 * constructor of RandomStrategy
	 * @param p_player given Player
	 */
	RandomStrategy(Player p_player){
		super(p_player); 
	}
	
	/**
	 *  implementation of createOrder
	 * @return
	 */
	private static Random rand=new Random();
	
	/**
	 * Get a random country owned by the player
	 * @return random country owned by the player
	 */
	protected Country getRandomConqueredCountry() {
		int l_idx=rand.nextInt(d_player.getConqueredCountries().size());
		Country l_randomCountry=(Country) d_player.getConqueredCountries().values().toArray()[l_idx];
		return l_randomCountry;
	}
	
	/**
	 * Choose a neighbor country for current country randomly
	 * @param p_currentCountry Current country
	 * @return neighbor country if exist else null
	 */
	protected Country getRandomNeighbor(Country p_currentCountry) {
		if(p_currentCountry.getNeighbors().size()==0)
			return null;
		int l_idx=rand.nextInt(p_currentCountry.getNeighbors().size());
		Country l_randNeighbor=(Country) p_currentCountry.getNeighbors().values().toArray()[l_idx];
		return l_randNeighbor;
	}
	
	/**
	 *	Creates and order. 
	 *	The Random player can either deploy or advance, determined randomly. .
	 *	@return Order
	 */
	public Order createOrder() {
		Order l_order = null;		
		if(!d_player.getIsAlive())
			return null;
		if(rand.nextBoolean()) {
			l_order=new DeployOrder(d_player,getRandomConqueredCountry(),rand.nextInt(10));
		}else {
			Country l_randomConqueredCountry=getRandomConqueredCountry();
			Country l_randomNeighbor=getRandomNeighbor(l_randomConqueredCountry);
			if(l_randomNeighbor!=null) {
				int l_num=rand.nextInt(l_randomConqueredCountry.getArmyNumber()+5);
				l_order=new AdvanceOrder(d_player,l_randomConqueredCountry,l_randomNeighbor,l_num);
			}
		}
		return l_order;
	}
}
