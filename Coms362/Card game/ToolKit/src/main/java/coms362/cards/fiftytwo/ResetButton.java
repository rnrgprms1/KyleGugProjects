package coms362.cards.fiftytwo;

import events.inbound.DealEvent;
import model.Button;
import model.Location;


public class ResetButton extends Button {
    public static final String kSelector = "resetButton";

    public ResetButton(String label, Location location) {
        super(kSelector, DealEvent.kId, label, location);
    }

}
