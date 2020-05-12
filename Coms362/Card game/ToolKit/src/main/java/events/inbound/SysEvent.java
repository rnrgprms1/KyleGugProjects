package events.inbound;

import java.util.List;

import coms362.cards.app.GameController;
import model.Game;
import model.Party;

public interface SysEvent extends Event {
	
	public void accept(GameController handler, Game game); 
}
