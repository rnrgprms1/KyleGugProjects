package coms362.cards.sp_pickup;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.fiftytwo.PickupInitCmd;
import coms362.cards.fiftytwo.PickupRules;
import coms362.cards.fiftytwo.SetQuorumCmd;
import events.inbound.InitGameEvent;
import events.inbound.SetQuorumEvent;
import model.Quorum;

public class P52SPRules
extends PickupRules implements Rules
{
	private static final String kTitle="52 Card Solitaire Pickup";
	
	@Override
	public Move apply(InitGameEvent e, Table table, Player player){
		return new PickupInitCmd(table.getPlayerMap(), kTitle );
	}
	
	@Override
	public Move apply(SetQuorumEvent e, Table table, Player player) {
		return new SetQuorumCmd(new Quorum(1,1));
	}
	
	
}
