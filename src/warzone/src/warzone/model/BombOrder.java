package warzone.model;

import warzone.view.GenericView;

public class BombOrder implements Order{
	private int d_targetCountryId;
	private Country d_targetCountry;
	private Player d_player;
	
	public BombOrder(int p_targetCountryId) {
		this.d_targetCountryId = p_targetCountryId;
	}
	
    /**
     * set the player of the order
     * @param p_player the player
     */
    public void setPlayer(Player p_player){
        d_player = p_player;
    }

    /**
     * get the player of the order
     * @return the player
     */
    public Player getPlayer() {
        return  d_player;
    }

	@Override
	public void execute() {
        if(!valid()) return;
        d_targetCountry.setArmyNumber(d_targetCountry.getArmyNumber() / 2); 
        GenericView.printSuccess("Successfully to bomb the target country.");
        printOrder();
	}

	@Override
	public boolean valid() {
        //check if the player has a bomb card
        if(!d_player.getCards().contains(Card.BOMB)){
            GenericView.printError("Player " + d_player.getName() + " does not have a bomb card");
            return false;
        }
        //check whether the target country belongs to the player
        if(d_player.getConqueredCountries().containsKey(d_targetCountryId)){
            GenericView.printError("The player cannot destroy armies in his own country.");
            return false;
        }
        //check whether the target country is adjacent to one of the countries that belong to the player
        boolean l_isAdjacent = false;
        for (Integer l_conqueredCountryId : d_player.getConqueredCountries().keySet()) {
        	if (d_player.getConqueredCountries().get(l_conqueredCountryId).getNeighbors().containsKey(d_targetCountryId)) {
        		d_targetCountry = d_player.getConqueredCountries().get(l_conqueredCountryId).getNeighbors().get(d_targetCountryId);
        		l_isAdjacent = true;
        		break;
        	}
        }
        if (!l_isAdjacent) {
        	GenericView.printError("The target country is not adjacent to one of the countries that belong to the player.");
        	return false;
        }
        return true;
	}

	@Override
	public void printOrder() {
        GenericView.println("Bomb order issued by player " + this.d_player.getName());
        GenericView.println("destryed the armies in " + d_targetCountry.getCountryID() + " to " + d_targetCountry.getArmyNumber() + ".");
	}

}
