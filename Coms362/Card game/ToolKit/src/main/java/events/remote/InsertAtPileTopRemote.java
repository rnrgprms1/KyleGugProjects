package events.remote;

import coms362.cards.streams.Marshalls;
import model.Card;

public class InsertAtPileTopRemote implements Marshalls {
    private String pileName;
    private Card c;

    public InsertAtPileTopRemote(String pileName, Card c) {
        this.pileName = pileName;
        this.c = c;
    }

    public String marshall() {
        return String.format("%s.unshift(allCards[%d]);\n" +
                "%s.render();\n",
                pileName, c.getId(), pileName);
    }

    public String stringify() {
        return String.format("InsertAtPileBottomRemote p=%s, c=%d", pileName, c.getId());
    }
}