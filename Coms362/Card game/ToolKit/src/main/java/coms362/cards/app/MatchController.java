package coms362.cards.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.View;
import coms362.cards.abstractcomp.ViewFactory;
import coms362.cards.fiftytwo.P52PlayerView;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.fiftytwo.PickupInitCmd;
import coms362.cards.fiftytwo.PickupPlayer;
import coms362.cards.streams.InBoundQueue;
import coms362.cards.streams.RemoteTableGateway;
import events.inbound.Event;
import events.inbound.InitGameEvent;
import events.remote.SetGameTitleRemote;
import events.remote.SetupTable;
import model.Party;

public class MatchController {
	
	private RemoteTableGateway remote;
	private ViewFacade views; // empty
	private Table table;
	private Rules rules;
	private InBoundQueue inQ;
	private GameFactory factory;
	
	public MatchController(
			InBoundQueue inQ, 
			Table table, Rules rules, 
			RemoteTableGateway remote, 
			GameFactory factory
		)
	{
		this.inQ = inQ;
		this.table = table;
		this.rules = rules;	
		this.remote = remote;
		this.factory = factory;
		this.views = new ViewFacade((ViewFactory) factory);
	}

	public void start(){
		//this is match setup ... it depends on which game
		//was selected. We initialize for a new match of the
		//already selected game
		
		Event e = null;
		while (! table.partiesReady()){
			try {
			e = inQ.take(); // we are waiting for/looking for new connections
			Move cmd = rules.eval(e, table, table.getCurrentPlayer()  );
			cmd.apply(table);
			cmd.apply(views);
			} catch (ExitTestException ex){
				return;
			} catch (Exception ex){
				// TODO: add support for deferring premature game play events? 
				System.out.println("Match Controller exception "+ex.getMessage());
				System.out.println(" ... event = "+ e.toString());
			}
		}
	
		// prime the pump by injecting a generic InitGameEvent()
		Move initCmd = rules.eval(new InitGameEvent(), table, null);
		initCmd.apply(table);
		initCmd.apply(views);
		
		PlayController mainloop = new PlayController(inQ, rules);
		mainloop.play(table, table.getCurrentPlayer(), views);
			
	}
}
