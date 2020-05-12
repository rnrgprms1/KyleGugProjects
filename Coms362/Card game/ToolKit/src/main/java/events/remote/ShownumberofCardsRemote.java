package events.remote;

import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.streams.Marshalls;
import model.Card;

public class ShownumberofCardsRemote implements Marshalls {
    private Card c;
    private Table t;
    public ShownumberofCardsRemote(Table t, Card c){
        this.c = c;
        this.t = t;

    }
    @Override
    public String marshall() {
        return String.format("%d",t.getPile("player1Pile").cards.size());
    }

    @Override
    public String stringify() {
        return String.format("%d",t.getPile("player1Pile").cards.size());
    }
}
