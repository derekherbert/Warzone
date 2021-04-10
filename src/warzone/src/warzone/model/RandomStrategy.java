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
	public Order createOrder() {
		Order l_order = null;
		
		if(!this.d_player.getIsAlive())
			return null;
		//deploy to random country
		if(this.d_player.getArmiesToDeploy()>0) {		
			int idx=rand.nextInt(this.d_player.getConqueredCountries().size());
			Country l_randomCountry=(Country) this.d_player.getConqueredCountries().values().toArray()[idx];
			l_order=new DeployOrder(this.d_player, l_randomCountry, this.d_player.getArmiesToDeploy());
			return l_order;
		}
		//attack or move army,choose a conquered country randomly,then choose a neighbor randomly,The neighbor country may is a enemy or own side.
		Collection<Country> l_conqueredCountries=this.d_player.getConqueredCountries().values();
		List<Object> l_conqueredCountriesList=Arrays.asList(l_conqueredCountries.toArray());
		Collections.shuffle(l_conqueredCountriesList);
		for(Object obj:l_conqueredCountriesList) {
			Country c=(Country)obj;
			if(c.getNeighbors().size()==0)
				continue;
			int idx=rand.nextInt(c.getNeighbors().size());
			l_order=new AdvanceOrder(this.d_player,c,(Country) c.getNeighbors().values().toArray()[idx],c.getArmyNumber()/2);
			return l_order;
		}
		
		return l_order;
	}
}
