package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.View;
import coms362.cards.abstractcomp.ViewFactory;
import coms362.cards.streams.RemoteTableGateway;
import model.PlayerFactory;
import model.TableBase;

public class P52GameFactory implements GameFactory, PlayerFactory, ViewFactory {

	@Override
	public Rules createRules() {
		return new PickupRules();
	}

	@Override
	public Table createTable() {
		return new TableBase(this);
	}

	@Override
	public View createView(PartyRole role, Integer num, String socketId, RemoteTableGateway gw ) {
		return new P52PlayerView(num, socketId, gw);
	}

	@Override
	public Player createPlayer( Integer position, String socketId) {
		return new PickupPlayer(position, socketId);
	}

	@Override
	public PlayerFactory createPlayerFactory() {
		return this;
	}

}
