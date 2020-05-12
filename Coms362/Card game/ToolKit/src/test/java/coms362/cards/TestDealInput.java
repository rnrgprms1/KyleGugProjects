package coms362.cards;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import org.junit.Test;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.View;
import coms362.cards.app.PlayController;
import coms362.cards.app.ViewFacade;
import coms362.cards.fiftytwo.LoggingView;
import coms362.cards.fiftytwo.P52GameFactory;
import coms362.cards.fiftytwo.PickupInitCmd;
import coms362.cards.fiftytwo.PickupPlayer;
import coms362.cards.fiftytwo.PickupRules;
import coms362.cards.streams.InBoundQueue;
import events.inbound.DealEvent;
import events.inbound.EndPlay;
import model.TableBase;

public class TestDealInput {

	static final long expectedSig = 2809332483L;
	
	@Test	
	public void test() {
		//set up game and match resources to provision play loop 
		InBoundQueue inQ = new InBoundQueue();
		//pre-load the input stream with the input for this test
		inQ.add(new DealEvent());
		inQ.add(new EndPlay()); //artifice to stop the test
		
		ViewFacade views = new ViewFacade(null);
		//we keep a reference to the concrete type for later
		LoggingView  p1View = new LoggingView();
		views.add(p1View); 
		
		Map<Integer, Player> players = new HashMap<Integer,Player>();
		Player player =  new PickupPlayer(1) ;
		players.put( (Integer) 1, player);
		players.put( (Integer) 2, new PickupPlayer(2) );
		
		// initialize the local model for Pu52 match
		Table table = new TableBase(new P52GameFactory());
		Move move = new PickupInitCmd(players );
		move.apply(table);
		Rules rules = new PickupRules();
		
		PlayController mainloop = new PlayController(inQ, rules);
		mainloop.play(table, player, views);
		
		
		CRC32 sig = new CRC32();
		String log = p1View.getLog();
		System.out.println(log);
		sig.update(log.getBytes());
		long sValue = sig.getValue();
		System.out.println(sValue);
		
		assertEquals(expectedSig, sValue);
		
	}

}
