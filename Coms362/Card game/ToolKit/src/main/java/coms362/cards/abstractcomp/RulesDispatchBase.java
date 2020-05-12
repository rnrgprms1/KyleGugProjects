package coms362.cards.abstractcomp;

import coms362.cards.app.ExitTestException;
import events.inbound.CardEvent;
import events.inbound.ConnectEvent;
import events.inbound.DealEvent;
import events.inbound.EndPlay;
import events.inbound.GameRestartEvent;
import events.inbound.InitGameEvent;
import events.inbound.NewPartyEvent;
import events.inbound.SelectGame;
import events.inbound.SetQuorumEvent;
import events.inbound.TimerEvent;

public class RulesDispatchBase implements RulesDispatch {

	@Override
	public Move apply(CardEvent e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}

	@Override
	public Move apply(DealEvent e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}

	@Override
	public Move apply(EndPlay e, Table table, Player player) {
		throw new ExitTestException("Exit on EndPlay Event");
	}

	@Override
	public Move apply(InitGameEvent e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}

	@Override
	public Move apply(SelectGame e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}

	@Override
	public Move apply(GameRestartEvent e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}

	@Override
	public Move apply(NewPartyEvent e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}
	
	@Override
	public Move apply(ConnectEvent e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}

	@Override
	public Move apply(SetQuorumEvent e, Table table, Player player) {
		throw new RuntimeException("Event not supported " + e.toString());
	}
	
	@Override
    public Move apply(TimerEvent e, Table table, Player player) {
        throw new RuntimeException("Event not supported " + e.toString());
    }

}
