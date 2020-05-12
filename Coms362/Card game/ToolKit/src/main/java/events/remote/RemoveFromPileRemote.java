package events.remote;

import coms362.cards.streams.Marshalls;
import model.Card;

public class RemoveFromPileRemote implements Marshalls {

    private Card c;
    private String pileName;

    public RemoveFromPileRemote(String pileName, Card c) {
        this.c = c;
        this.pileName = pileName;
    }

    public String marshall() {
        return String.format("%s.removeCard(allCards[%d]);\n" +
                "%s.render();\n",
                pileName, c.getId(), pileName);
    }

    public String stringify() {
        return "RemoveFromPileRemote card = " + c.getId();
    }
}

