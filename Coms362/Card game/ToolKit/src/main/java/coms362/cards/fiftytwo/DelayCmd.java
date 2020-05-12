package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import events.inbound.TimerEvent;
import events.remote.SetTimerRemote;
import model.Timer;

public class DelayCmd implements Move {
    private Player p;
    
    public DelayCmd(Player p) {
        this.p = p;
    }
    
    public void apply(Table table) {
        
    }

    public void apply(ViewFacade view) {
        view.send(new SetTimerRemote(new Timer("delay", TimerEvent.kId, p, 5000)));
    }
}