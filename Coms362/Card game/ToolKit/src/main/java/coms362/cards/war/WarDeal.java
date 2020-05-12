package coms362.cards.war;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.HideButtonRemote;
import events.remote.*;
import model.Card;
import model.Location;
import model.Pile;

/**
 * @author Daniel Seeger
 */
public class WarDeal implements Move {

    private Table table;
    private Player player;

    public WarDeal(Table table, Player player) {
        this.table = table;
        this.player = player;
    }

    public void apply(Table table) {
        // TODO Auto-generated method stub

    }

    public void apply(ViewFacade views) {

        try {
            String remoteId = views.getRemoteId(DealButton.kSelector);
            views.send(new HideButtonRemote(remoteId));
            Pile tableP1 = table.getPile("player1Pile");
            Pile tableP2 = table.getPile("player2Pile");
            views.send(new CreatePile(new Pile("player1Pile", new Location(250, 100))));
            views.send(new CreatePile(new Pile("player2Pile", new Location(250, 500))));
            for(Card c : tableP1.cards.values()){
                views.send(new CreateRemote(c));
//                views.send(new HideCardRemote(c));
                views.send(new InsertAtPileBottomRemote("player1Pile", c));
                views.send(new UpdateRemote(c));
            }
            for(Card c : tableP2.cards.values()){
                views.send(new CreateRemote(c));
//                views.send(new HideCardRemote(c));
                views.send(new InsertAtPileBottomRemote("player2Pile", c));
                views.send(new UpdateRemote(c));
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
