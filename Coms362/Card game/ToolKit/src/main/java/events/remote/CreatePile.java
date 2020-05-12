package events.remote;

import coms362.cards.streams.Marshalls;
import model.Location;
import model.Pile;

public class CreatePile implements Marshalls {
	
	private Pile p;
	
	public CreatePile( Pile p){
		this.p = p; 
	}
	
	public String marshall() {
		Location loc = p.getLocation();
		return String.format(
            "%s = new cards.Deck({faceUp:%b, x:%d, y:%d});",
			p.name,
			p.visible,
			loc.getX(),
			loc.getY()
		);
			
	}

    public String stringify() {
        return "CreatePile p=" + p.getName();
    }
}
