package coms362.cards.abstractcomp;

import events.inbound.Event;
import model.Party;

public interface Rules {

	Move eval(Event nextE, Table table, Player player);

}
