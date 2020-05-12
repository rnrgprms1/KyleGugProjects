package coms362.cards.app;

import java.util.Arrays;
import java.util.List;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.fiftytwo.P52GameFactory;
import coms362.cards.sp_pickup.P52SPGameFactory;
import coms362.cards.fiftytwo.P52SGameFactory;
import coms362.cards.war.WarFactory;


public class GameFactoryFactory {

	// TODO: create Enum for game ids. 
	String gameIds[] = {"PU52MP", "PU52", "WAR"};
	List<String> supported = Arrays.asList(gameIds);

	public GameFactory getGameFactory(String selector) {
		switch (selector) {
			case "PU52MP":
				return new P52GameFactory();
			case "PU52":
				return new P52SPGameFactory();
			/**
			 * @author Daniel Seeger
 			 */
			case "WAR":
				return new WarFactory();
			default:
				return null;
		}
	}
	/*	//Statment checking if it's single play or not
			if (selector.equals(gameIds[0])) {
				return new P52GameFactory();
			} else {
				return new P52SGameFactory();
			}
			*/

	public boolean isValidSelection(String gameId) {
		return supported.contains(gameId);
	}
}