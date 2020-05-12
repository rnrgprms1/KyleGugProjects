package events.remote;

import coms362.cards.streams.Marshalls;
import model.Card;

/* This aliases the preferred class InsertAtPileTopRemote with identical functionality.
 * You should replace references to this class with references to the preferred class
 */

@Deprecated
public class AddToPileRemote extends InsertAtPileTopRemote implements Marshalls {

	String pileName;
	Card c;
	
	public AddToPileRemote(String pileName, Card c) {
		super(pileName, c);
	}

	public String stringify() {
		return String.format("AddToPileRemote p=%s, c=%d", pileName, c.getId());
	}

}
