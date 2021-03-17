package warzone.model;

import java.util.Random;

import warzone.view.GenericView;

public class AdvanceOrder implements Order {

	private Country d_fromCountry;
	private Country d_toCountry;
	private int d_numberOfArmies;
	private Player d_player;
	
	public AdvanceOrder(Player p_player, Country p_fromCountry, Country p_toCountry, int p_numberOfArmies) {
		d_player = p_player;
		d_fromCountry = p_fromCountry;
		d_toCountry = p_toCountry;
		d_numberOfArmies = p_numberOfArmies;
	}
	
	public Country getFromCountry() {
		return d_fromCountry;
	}

	public void setFromCountry(Country fromCountry) {
		this.d_fromCountry = fromCountry;
	}

	public Country getToCountry() {
		return d_toCountry;
	}

	public void setToCountry(Country toCountry) {
		this.d_toCountry = toCountry;
	}

	public int getNumberOfArmies() {
		return d_numberOfArmies;
	}

	public void setNumberOfArmies(int numberOfArmies) {
		this.d_numberOfArmies = numberOfArmies;
	}

	public Player getPlayer() {
		return d_player;
	}

	public void setPlayer(Player player) {
		this.d_player = player;
	}

	@Override
	public boolean execute() {
		
		//Check if fromCountry is owned by the current player
		if(!d_fromCountry.getOwner().equals(d_player)) {
			
			GenericView.printWarning("Could not perform the advance order moving " + d_numberOfArmies + " armies from " + 
					d_fromCountry.getCountryName() + " to " + d_toCountry.getCountryName() + " because " + d_player.getName() + " does not own " + d_fromCountry + ".");
			return false;
		}
		
		//Check if fromCountry and toCountry are neighbors
		if(d_fromCountry.getNeighbors().get(d_toCountry.getCountryID()) == null) {
			
			GenericView.printWarning("Could not perform the advance order moving " + d_numberOfArmies + " armies from " + 
					d_fromCountry.getCountryName() + " to " + d_toCountry.getCountryName() + " because they are not neighbors.");
			return false;
		}
		
		//Make sure that there are enough armies to advance
		if(d_fromCountry.getArmyNumber() < d_numberOfArmies) {
			
			d_numberOfArmies = d_fromCountry.getArmyNumber();
		}
		
		//If toCountry is owned by current player -> advance armies
		if(d_toCountry.getOwner().equals(d_player)) {
		
			//Move the armies
			d_fromCountry.setArmyNumber(d_fromCountry.getArmyNumber() - d_numberOfArmies);
			d_toCountry.setArmyNumber(d_toCountry.getArmyNumber() + d_numberOfArmies);
		}
		//Else toCountry is owned by opponent -> attack
		else {
			/*
			 * Then, each attacking army unit involved has 60% chances of killing one defending army. 
			 * At the same time, each defending army unit has 70% chances of killing one attacking army unit. 
			 * If all the defender's armies are eliminated, the attacker captures the territory. The attacking army units that survived the battle then occupy the conquered territory
			 */
			Random l_randomNumberGenerator = new Random();
			
			for(int i = 0; i < d_numberOfArmies; i++) {
				
				if(d_toCountry.getArmyNumber() == 0) {
					
				}
				
				//Attacking army has a 60% chance of killing a defending army
				if((l_randomNumberGenerator.nextInt(10) + 1) <= 6) { //random int between 1 and 10 (inclusive)
					
					//Kill defending army
					d_toCountry.setArmyNumber(d_toCountry.getArmyNumber() - 1);
				}
				
				//Defending army has a 70% chance of killing a defending army
				if((l_randomNumberGenerator.nextInt(10) + 1) <= 7) { //random int between 1 and 10 (inclusive)
					
					//Kill attacking army
					d_fromCountry.setArmyNumber(d_fromCountry.getArmyNumber() - 1);
				}
			}
			
		}
		
		return false;
	}

}
