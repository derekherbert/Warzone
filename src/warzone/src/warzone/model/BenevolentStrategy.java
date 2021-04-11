package warzone.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *	define of the BenevolentStrategy
 */
public class BenevolentStrategy extends PlayerStrategy {
	
	/**
	 * Armies to deploy
	 */
	private int d_armiesToDeploy;
	
	/**
	 * constructor of BenevolentStrategy
	 * @param p_player given Player
	 */
	BenevolentStrategy(Player p_player){
		super(p_player);
		d_armiesToDeploy=p_player.getArmiesToDeploy();
	}
	
	/**
	 * Get the conquered country which has minimum army number
	 * @return the weakest conquered country
	 */
	protected Country getWeakestConqueredCountry() {
		Country l_weakestCountry=null;			
		int l_minArmyNum=Integer.MAX_VALUE;
		for(Country l_c:d_player.getConqueredCountries().values()) {
			if(l_c.getArmyNumber()<l_minArmyNum) {
				l_minArmyNum=l_c.getArmyNumber();
				l_weakestCountry=l_c;
			}
		}
		return l_weakestCountry;
	}
	
	/**
	 * implementation of createOrder
	 * @return Order
	 */
	public Order createOrder() {
		Order l_order=null;
		if(!d_player.getIsAlive())
			return null;
		//deploy to weakest country
		if(d_armiesToDeploy>0) {
			Country l_weakestCountry=getWeakestConqueredCountry();			
			l_order = new DeployOrder(d_player, l_weakestCountry, d_armiesToDeploy);
			d_armiesToDeploy-=d_armiesToDeploy;
		}else {
			d_player.setHasFinisedIssueOrder(true);
		}
		return l_order;
	}
}
