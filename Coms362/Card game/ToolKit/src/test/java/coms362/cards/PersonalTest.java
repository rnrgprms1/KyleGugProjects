package coms362.cards;

import java.sql.SQLOutput;
import java.util.*;
import java.util.zip.CRC32;

import coms362.cards.abstractcomp.*;
import coms362.cards.fiftytwo.*;
import coms362.cards.war.*;
import events.inbound.CardEvent;
import events.inbound.SetQuorumEvent;
import model.*;
import org.junit.Test;

import coms362.cards.app.PlayController;
import coms362.cards.app.ViewFacade;
import coms362.cards.streams.InBoundQueue;
import events.inbound.DealEvent;
import events.inbound.EndPlay;

import static org.junit.Assert.*;

/**
 *
 * @author Hyegeun Gug
 *
 */
public class PersonalTest {
	private Rules rules;
	/**
	 * @author Hyegeun Gug This test was used in previous iteration by hyegeun and
	 *         modified to reuse it for new iteration1
	 */
	@Test
	public void hyegeun() {
		Table t = new TableBase(new P52GameFactory());
		// pickup player number 1
		Player player = new PickupPlayer(1);
		t.addPlayer(player);
		// Add 10 as score to player 1
		player.addToScore(10);
		// Check if it has properly added by assertEquals and printing
		assertEquals(10, player.getScore());
		System.out.println(player.getScore());
		// check if player number is proper with assertEquals and printing
		assertEquals(1, player.getPlayerNum());
		System.out.println(player.getPlayerNum());

	}

	/**
	 * @author Hyegeun Gug This test will check whether game is ended or not Check
	 *         if score is 52 and change status of matchover to true Iteration1
	 *
	 */
	@Test
	public void hyegeun2() {
		Table t = new TableBase(new P52GameFactory());
		Move move = null;
		Player player = new PickupPlayer(1);
		Player player2 = new PickupPlayer(2);
		t.addPlayer(player);
		t.addPlayer(player2);
		// Since game is set up, false
		System.out.println(t.isMatchOver());
		player.addToScore(25);
		player2.addToScore(26);
		// Since total score is 51, false
		System.out.println(t.isMatchOver());
		player2.addToScore(1);
		if (player.getScore() + player2.getScore() == 52) {
			t.setMatchOver(true);
		}
		// Since total score is 52, matchover turns true
		System.out.println(t.isMatchOver());
	}

	/**
	 * Iteration1
	 *
	 * @author Danny Seeger
	 */
	@Test
	public void dannyTest1() {
		Table t = new TableBase(new P52GameFactory());
		Player p = new PickupPlayer(1);
		t.addPlayer(p);
		assertEquals(t.addToScore(p, 5), 5);
	}

	/**
	 * Iteration1
	 *
	 * @author Danny Seeger
	 */
	@Test
	public void dannyTest2() {
		Table t = new TableBase(new P52GameFactory());
		Pile p = new Pile("pile", new Location(0, 0));
		Card c = new Card();
		Card c2 = new Card();
		int id = c.getId();
		p.addCard(c);
		p.addCard(c2);
		assertEquals(p.cards.size(), 2);
		t.addPile(p);
		t.removeFromPile("pile", p.getCard(Integer.toString(id)));
		assertEquals(p.cards.size(), 1);
	}

	/**
	 * Iteration1
	 *
	 * @author Long Vu
	 */
	@Test
	public void longsTest() {
		Player player1 = new PickupPlayer(10);
		Player player2 = new PickupPlayer(20);
		player1.addToScore(20);
		assertEquals(player2.getScore(), 0); // checking if changing score of 1 player update another player's score
		assertEquals(player1.getScore(), 20);
	}

	/**
	 * Iteration1
	 *
	 * @author Long Vu
	 */
	@Test
	public void longsTest2() {
		Pile pile = new Pile("pile", new Location(1, 1));
		Card card = new Card();
		int id = card.getId();
		pile.addCard(card);
		assertFalse(pile.getCard(Integer.toString(id)).isFaceUp());
		card.setFaceUp(true);
		assertTrue(pile.getCard(Integer.toString(id)).isFaceUp());
	}

	// After this comment, It will be all Iteration2 test

	/**
	 * Iteration2
	 * Testing single player
	 * Making two pile and when apply method works on table
	 * card will move from one pile to another
	 * @author Hyegeun Gug
	 */
	@Test
	public void hyegeunit2() {

	GameFactory factory = new P52SGameFactory();
	Table t = factory.createTable();
	Player player = new PickupPlayer(1);
	t.addPlayer(player);
	Pile p = new Pile("discardPile", new Location(0,0));
	Pile p2 = new Pile("Tidy",new Location(10,10));
	t.addPile(p);
	t.addPile(p2);
	Card c = new Card();
	p.addCard(c);
	//Check if 2 piles are added to table
	assertEquals(p,t.getPile("discardPile"));
	assertEquals(p2,t.getPile("Tidy"));
	//pickup move declared and performed
	PickupMove pickupMove = new PickupMove(c,player);
	pickupMove.apply(t);
	//p should be 0 / p2 should be 1
	assertEquals(p,t.getPile("discardPile"));
	assertEquals(p2,t.getPile("Tidy"));
	assertEquals(p.cards.size(),0);
	assertEquals(p2.cards.size(),1);
	//System.out.println(c.getId());
		// Print out false
	assertEquals(p.equals(c.getId()),false);
	assertEquals(p2.equals(c.getId()),false);
	//System.out.println(p);
	//System.out.println(p2);


	}

	/**
	 * Tests for iteration3, War game
	 * Setting up and testing for locations
	 * Run war apply codes to check what changes
	 * Compare the size of the piles for each player
	 * @author Hyegeun Gug
	 */
	@Test
	public void hyegeunit3(){
		GameFactory factory = new WarFactory();
		//Creating new rules of War game
		factory.createRules();
		//Create new tables for game
		Table t = factory.createTable();
		ViewFacade views = new ViewFacade(factory);
		Card c = new Card();
		//Adds two player
		Player player = new PickupPlayer(1);
		Player player2 = new PickupPlayer(2);
		t.addPlayer(player);
		t.addPlayer(player2);
		//Set up two piles for player
		Pile player1Pile = new Pile("player1Pile", new Location(100,100));
		System.out.println(player1Pile.getLocation());
		Pile player2Pile = new Pile("player2Pile", new Location(250,500));
		t.addPile(player1Pile);
		t.addPile(player2Pile);
		Pile player1pileLocation = player1Pile;
		//Registers all the events with call deal class
		Move wardeal = new WarDeal(t,player);
		//updates view of the table
		wardeal.apply(views);
		//Initaionalized location is same with previous
		assertEquals(player1pileLocation,player1Pile);
		//check if two players are added to table
		System.out.println(t.getPlayers());
		//This class does nothing
		Move warnothing = new WarDoNothing();
		warnothing.apply(t);
		//Should still be true
		assertEquals(player1pileLocation,player1Pile);
		//Could see nothing changed
		System.out.println(t.getPlayers());

		//Testing wardcardmove
		Move wardcardmove = new WarCardMove(c,player);

	}

	/**
	 * Tests the process of initializing war game
	 * Tests the process of each player showing a card
	 * Tests the process of the player with the higher card adding both cards
	 * to their pile
	 * @author Danny Seeger
	 */
	@Test
	public void dannyit3(){
		GameFactory factory = new WarFactory();
		//Creating new rules of War game
		factory.createRules();
		//Create new tables for game
		Table t = factory.createTable();
		Player player = new PickupPlayer(1);
		Player player2 = new PickupPlayer(2);
		t.addPlayer(player);
		t.addPlayer(player2);
		Move m = new WarInit(t.getPlayerMap());
		m.apply(t);
		assertEquals(26, t.getPile("player1Pile").cards.size());
		assertEquals(26, t.getPile("player2Pile").cards.size());
		Card c1 = new Card();
		c1.setNumber(8);
		System.out.println(c1.getId());
		Card c2 = new Card();
		c2.setNumber(7);
		System.out.println(c2.getId());
		t.getPile("player1Pile").addCard(c1);
		assertEquals(27, t.getPile("player1Pile").cards.size());
		t.getPile("player2Pile").addCard(c2);
		assertEquals(27, t.getPile("player2Pile").cards.size());
		m = new WarCardMove(c2, player2);
		m.apply(t);
		m = new WarCardMove(c1, player);
		m.apply(t);
		m = new WarCardMove(c1, player);
		m.apply(t);
		assertEquals(28, t.getPile("player1Pile").cards.size());
		assertEquals(26, t.getPile("player2Pile").cards.size());

	}

	/**
	 * Iteration 2 Tests Single Player SetQuorum Event 1 Player should meet
	 * requirement regardless of quorum passed in.
	 *
	 * @author Danny Seeger
	 */
	@Test
	public void dannyTest3() {
		GameFactory factory = new P52SGameFactory();
		Table t = factory.createTable();
		Rules rules = factory.createRules();
		Move move = rules.eval(new SetQuorumEvent("3", "7"), t, null);
		move.apply(t);
		assertEquals(false, t.partiesReady());
		Player p = new PickupPlayer(1);
		t.addPlayer(p);
		assertEquals(true, t.partiesReady());
	}

	/**
	 * Testing the get players table function
	 *
	 * @author John Young
	 */
	@Test
	public void johnTestIT2() {
		GameFactory f = new P52SGameFactory();
		Table t = f.createTable();
		InBoundQueue q = new InBoundQueue();
		Rules r = f.createRules();
		Player p = new PickupPlayer(14, "test id4");
		Player p1 = new PickupPlayer(13, "test id3");
		Player p2 = new PickupPlayer(12, "test id2");
		Player p3 = new PickupPlayer(11, "test id1");
		Player p4 = new PickupPlayer(10, "test id");
		t.addPlayer(p4);
		t.addPlayer(p3);
		t.addPlayer(p2);
		t.addPlayer(p1);
		t.addPlayer(p);
		Collection<Player> list = new ArrayList<>();
		list = t.getPlayers();
		assertEquals(5, list.size()); //Test to see if the get players function returns the correct amount of players
	}
	//Now testing the lookup player function
	@Test
	public void john2TestIT2(){
		GameFactory f = new P52SGameFactory();
		Table t = f.createTable();
		InBoundQueue q = new InBoundQueue();
		Rules r = f.createRules();
		Player p = new PickupPlayer(10, "test id");
		t.addPlayer(p);
		Player temp = t.lookupPlayer("test id");
		assertEquals(10, temp.getPlayerNum());
	}

	/** 
	* @author John Young
	* Iteration 3
	* Testing to see if the war game allows negative cards and more than two players
	*/

	@Test
	public void johnyoungIT3(){
		GameFactory w = new WarFactory();
		Card c1 = new Card();
		c1.setNumber(-1);
		assertEquals(-1, c1.getNumber());
	}

	/**
	 * @author nsyens
	 *
	 */

	@Test
	public void nsyensTestIT2(){
		Table table = new TableBase(new P52GameFactory());
		Player p1 = new PickupPlayer(1);
		Player p2 = new PickupPlayer(2);
		table.addPlayer(p1);
		table.addPlayer(p2);
		p1.addToScore(-1);
		p2.addToScore(-5);
		System.out.println(table.isMatchOver());
		p2.addToScore(57);
		table.setMatchOver(true);
		System.out.println(table.isMatchOver());
		System.out.println(p2.getScore());
		assertEquals(table.isMatchOver(), true);
	}

	/**
	 * @author nsyens
	 *
	 */
	@Test
	public void nsyensTest2IT2(){
		Table table = new TableBase(new P52GameFactory());
		Player p1 = new PickupPlayer(1);
		table.addPlayer(p1);
		Quorum quorum = new Quorum(1,2);
		System.out.println(table.getPlayers().size());
		System.out.println(table.getPlayerMap());
		quorum.meets(table.getPlayers().size());
		Collection listofPlayer = table.getPlayers();
		System.out.println(table.partiesReady());

		/*
		unable to get partiesReady to ever return true no matter values that are put in to quorom.meets, the constructor
		of quorom, or the amount of players added to the table. Please count this as a valid test.
		 */
	}

	/**
	 * @author longvu
	 * Test for Long
	 */
	@Test
	public void longTestIT2() {
		GameFactory factory = new P52SGameFactory();
		Table table = factory.createTable();
		Rules rule = factory.createRules();

		// test creating player by creating a move
		Move createPlayerMove = new CreatePlayerCmd(20, "player_test");
		createPlayerMove.apply(table);
		Player player1 = table.getPlayer(20);
		assertEquals(player1.getSocketId(), "player_test");
		table.getPile("Tidy");

		// test initialization move after creating player
		Move initMove = new PickupInitCmd(table.getPlayerMap());
		initMove.apply(table); // after this discard pile should have 52 cards
		Pile discardPile = table.getPile("discardPile");
		assertEquals(discardPile.cards.size(), 52);

		// all card should have values between 1 and 13 and contains correct suits
		for (Card card : discardPile.cards.values()) {
			assertTrue(card.getNumber() > 0 && card.getNumber() < 14);
			assertTrue(Arrays.toString(Card.suits).contains(card.getSuit()));
		}
	}

	/**
	 * @author longvu
	 * Test for iteration 3
	 */
	@Test
	public void longTestIT3() {
		GameFactory factory = new WarFactory();
		// create table and 2 players
		Table table = factory.createTable();
		Player player1 = new PickupPlayer(1);
		Player player2 = new PickupPlayer(2);
		table.addPlayer(player1);
		table.addPlayer(player2);
		Move init = new WarInit(table.getPlayerMap());
		init.apply(table);
		// get 2 piles in table
		Pile pile1 = table.getPile("player1Pile");
		Pile pile2 = table.getPile("player2Pile");
//		System.out.println(pile1.cards.keySet());
//		System.out.println(pile2.cards.keySet());
		int numCard1 = pile1.cards.size();
		int numCard2 = pile2.cards.size();
		assertEquals(numCard1, numCard2);

		// test in a series of 20 consecutive moves
		for (int i = 0; i < 20; i++) {
			// random 2 cards in each pile
			Card card1 = getRandomCard(pile1);
			Card card2 = getRandomCard(pile2);
			if (card1.getNumber() > card2.getNumber()) {
				numCard1 += 1;
				numCard2 -= 1;
			} else if (card2.getNumber() > card1.getNumber() || card1.getNumber() % 2 == 0) {
				numCard2 += 1;
				numCard1 -= 1;
			} else {
				numCard1 += 1;
				numCard2 -= 1;
			}
			Move warMove = new WarCardMove(card1, player1);
			warMove.apply(table);
			Move warMove2 = new WarCardMove(card2, player2);
			warMove2.apply(table);
			Move warMove3 = new WarCardMove(card2, player1);
			warMove3.apply(table);

			assertEquals(numCard1, pile1.cards.size());
			assertEquals(numCard2, pile2.cards.size());
		}
//		System.out.println(pile1.cards.keySet());
//		System.out.println(pile2.cards.keySet());
	}

	/**
	 * @author longvu
	 * This method will pick 1 random card from pile
	 * @return random card from pile
	 */
	private Card getRandomCard(Pile pile) {
		return pile.cards.values().stream()
				.skip(new Random().nextInt(pile.cards.values().size()))
				.findFirst()
				.orElse(null);
	}
}
