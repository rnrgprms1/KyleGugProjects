package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import events.inbound.InitGameEvent;
import events.inbound.SetQuorumEvent;
import model.Quorum;

/**
 * single player rules
 */
public class P52SRules extends PickupRules {

    @Override
    public Move apply(InitGameEvent e, Table table, Player player) {
        Player p1 = table.getPlayer((Integer) 1);

        return new PSInit(table.getPlayerMap());
    }

    @Override
    public Move apply(SetQuorumEvent e, Table table, Player player) {
        return new SetQuorumCmd(new Quorum(1, 1));
    }
}