package warzone.model;


/**
 * This class represents one advance order of the gameplay
 */
public class AdvanceOrder extends Order{

	
    AdvanceOrder() {
		this.d_orderType = OrderType.ADVANCE;
		this.d_gameContext = GameContext.getGameContext();  
    }

	
    /**
     * Override of excute
     */
    @Override
    public void execute() {

    }

    /**
     * Override of valid check
     * @return true if valid
     */
    @Override
    public boolean valid(){
		//check if DIPLOMACY 
		if(d_orderType == OrderType.ADVANCE || d_orderType == OrderType.AIRLIFT || d_orderType == OrderType.BOMB  ) {
			//todo: need to have 2 player's reference: self and target;
			//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			this.d_gameContext.isDiplomacyInCurrentTurn(null, null);
			
		}
		
        return true;
    }

    /**
     * override of print the order
     */
    @Override
    public void printOrder(){

    }
}
