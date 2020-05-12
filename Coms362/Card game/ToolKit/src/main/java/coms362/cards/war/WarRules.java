package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.fiftytwo.*;
import events.inbound.CardEvent;
import events.inbound.DealEvent;
import events.inbound.InitGameEvent;
import events.inbound.SetQuorumEvent;
import model.Card;
import model.Quorum;

public class WarRules extends PickupRules {
    @Override
    public Move apply(InitGameEvent e, Table table, Player player) {
        Player p1 = table.getPlayer((Integer) 1);

        return new WarInit(table.getPlayerMap());
    }

    @Override
    public Move apply(DealEvent e, Table table, Player player) {
        return new WarDeal(table, player);
    }

    /**
     * @author Daniel Seeger
     */
    @Override
    public Move apply(SetQuorumEvent e, Table table, Player player) {
        return new SetQuorumCmd(new Quorum(2, 2));
    }

    /**
     * @param e
     * @param table
     * @param player
     * @return
     * @author Hyegeun Gug
     */
    @Override
    public Move apply(CardEvent e, Table table, Player player) {
        if (player.getPlayerNum() == 1) {
            if (table.getPile("player1Pile").cards.containsKey(Integer.parseInt(e.getId()))) {
                return new WarCardMove(table.getPile("player1Pile").cards.get(Integer.parseInt(e.getId())), player);
            }

        }

        if (player.getPlayerNum() == 2) {
            if (table.getPile("player2Pile").cards.containsKey(Integer.parseInt(e.getId()))) {
                return new WarCardMove(table.getPile("player2Pile").cards.get(Integer.parseInt(e.getId())), player);
            }
        }

        return new WarDoNothing();

    }
}

