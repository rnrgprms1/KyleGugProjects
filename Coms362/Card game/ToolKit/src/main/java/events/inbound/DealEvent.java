package events.inbound;

import javax.xml.bind.UnmarshallerHandler;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.socket.SocketEvent;

public class DealEvent implements Event, EventFactory {
	
	public static final String kId = "dealevent";
	
	public static Event createEvent(SocketEvent sktEvent){
		return new DealEvent();		
	}
	
	@Override
	public Move dispatch(RulesDispatch rules, Table table, Player player) {
		return rules.apply(this, table, player);
	}
	
}
