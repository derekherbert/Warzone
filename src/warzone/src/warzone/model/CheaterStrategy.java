package warzone.model;
import java.util.List;


/**
 *	define of the CheaterStrategy
 */
public class CheaterStrategy extends PlayerStrategy {	
	
	/**
	 * constructor of CheaterStrategy
	 * @param p_player given Player
	 */
	CheaterStrategy(Player p_player){
		super(p_player);
	}
	
	/**
	 *  implementation of createOrder
	 * @return
	 */
	public Order createOrder() {
		GameContext l_gameContext=GameContext.getGameContext();
		
		for(Country l_c:l_gameContext.getCountries().values()) {
			if(l_c.getCountryState()==CountryState.Neutral) {
				l_c.setOwner(d_player);
				d_player.getConqueredCountries().put(l_c.getCountryID(),l_c);
				d_player.setArmyNumber(d_player.getArmyNumber()+l_c.getArmyNumber());
				d_player.setConqueredACountryThisTurn(true);
			}
		}
		//if has enemy neighbor double the army number
		for(Country l_c1:d_player.getConqueredCountries().values()) {
			boolean l_hasEnemyNeighbor=false;
			for(Country l_c2:l_c1.getNeighbors().values()) {
				if(l_c2.getOwner()!=d_player) {
					l_hasEnemyNeighbor=true;
					break;
				}
			}
			if(l_hasEnemyNeighbor) {
				l_c1.setArmyNumber(2*l_c1.getArmyNumber());
			}
		}
			
		return null;
	}
}
