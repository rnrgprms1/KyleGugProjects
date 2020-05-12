package coms362.events;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.GameController;
import events.inbound.Event;
import events.inbound.SysEvent;
import model.Game;

/**
 * Synthesized when an unsupported game is selected.
 * 
 * This event is always injected directly into the inbound queue, so 
 * it does not need to be registered for unmarshalling. 
 * @author Robert Ward
 *
 */
public class InvalidGameSelection implements Event, SysEvent {

	private String selection; 
	
	public InvalidGameSelection(String selection) {
		this.selection = selection;
	}

	@Override
	public void accept(GameController handler, Game game) {
		handler.apply(this,game);
	}
	
	
	@Override
	public Move dispatch(RulesDispatch rules, Table table, Player player) {
		// should never be called from within a running game, so this should
		// never be invoked. 
		return null;
	}
	
	public String getMsg(){
		return String.format(
			"Invalid Game Selection (%s).%nPlease restart the app and try again.",
			selection
		);
	}

}
