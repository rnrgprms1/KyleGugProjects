package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Player;
import coms362.cards.app.ViewFacade;
import events.remote.*;
import model.Location;
import model.Pile;

import java.util.Map;

/**
 * Single game player
 */
public class PSInit extends PickupInitCmd {

    public PSInit(Map<Integer, Player> players) {
        super(players);
    }

    @Override
    public void apply(ViewFacade view) {
        view.send(new SetupTable());
        view.send(new SetGameTitleRemote("52 Card Solitaire Pickup."));

        for (Player p : players.values()) {
            String role = (p.getPlayerNum() == 1) ? "Dealer" : "Player " + p.getPlayerNum();
            view.send(new SetBottomPlayerTextRemote(role, p));
        }

        view.send(new CreatePile(new Pile("discardPile", new Location(500, 359))));
        String id = "";
        DealButton dealButton = new DealButton("DEAL", new Location(0, 0));
        ResetButton resetButton = new ResetButton("Reset", new Location(500, 500));
        view.register(dealButton); //so we can find it later.
        view.register(resetButton);
        view.send(new CreateButtonRemote(dealButton));
        view.send(new CreateButtonRemote(resetButton));
        //view.send(new CreateButtonRemote("reset", "RestartGame", "Reset", new Location(500,0)));
        //view.send(new CreateButtonRemote(Integer.toString(getNextId()), "clear", "ClearTable", "Clear Table", new Location(500,0)));
    }
}
