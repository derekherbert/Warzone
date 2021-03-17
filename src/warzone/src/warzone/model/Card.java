package warzone.model;

/**
 * Used to abstract all the cards a player can use.
 *
 */
public interface Card {

	/**
	 * Performs the action of the card when played
	 * 
	 * @return true if the card is played successfully
	 */
	public boolean playCard();
}
