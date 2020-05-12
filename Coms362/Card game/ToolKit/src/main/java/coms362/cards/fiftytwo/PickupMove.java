package coms362.cards.fiftytwo;

import java.io.IOException;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import events.remote.HideCardRemote;
import events.remote.InsertAtPileTopRemote;
import events.remote.RemoveFromPileRemote;
import events.remote.ShowCardRemote;
import events.remote.ShowPlayerScore;
import model.Card;

public class PickupMove implements Move {
	
	private Card c;
	private Player p;
	
	public PickupMove(Card c, Player p){
		this.c = c;
		this.p = p; 
	}
	
	public void apply(Table table){
		table.removeFromPile("discardPile", c);
		table.addToPile("tidyPile", c);
		table.addToScore(p, 1);
	}
	
	public void apply(ViewFacade view){
		view.send(new HideCardRemote(c));
		view.send(new RemoveFromPileRemote("discardPile", c));
		view.send(new InsertAtPileTopRemote("tidyPile", c));
		view.send(new ShowCardRemote(c));
		view.send(new ShowPlayerScore(p, null));
	}	
}
