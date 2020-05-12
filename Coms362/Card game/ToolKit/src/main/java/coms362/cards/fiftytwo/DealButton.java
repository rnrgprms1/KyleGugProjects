package coms362.cards.fiftytwo;

import events.inbound.DealEvent;
import model.Button;
import model.Location;


public class DealButton extends Button {
	public static final String kSelector = "dealButton";

	public DealButton(String label, Location location) {
		super(kSelector, DealEvent.kId, label, location);
	}	

}
