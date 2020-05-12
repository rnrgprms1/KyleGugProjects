package coms362.cards.sp_pickup;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.View;
import coms362.cards.fiftytwo.P52GameFactory;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.streams.RemoteTableGateway;
import model.PlayerFactory;

public class P52SPGameFactory 
extends P52GameFactory implements GameFactory {

	@Override
	public Rules createRules() {
		return new P52SPRules();
	}

}
