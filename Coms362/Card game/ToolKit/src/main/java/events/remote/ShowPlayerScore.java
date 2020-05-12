package events.remote;

import coms362.cards.abstractcomp.Player;
import coms362.cards.streams.Marshalls;
import model.Location;

public class ShowPlayerScore implements Marshalls, FilterOnOwner {

	Player p;
	Location pos = new Location(500,500);
	
	public ShowPlayerScore( Player p, Location pos){
		this.p = p;
		if (pos != null) this.pos = pos;
	}
	
	@Override
	public String marshall() {
		return String.format("$('#current-player span')"
				+ ".text('Score: %d')",
				p.getScore());
	}

	@Override
	public String stringify() {
		return String.format("ShowPlayerScore %d %d", 
				p.getPlayerNum(), p.getScore());
	}

	@Override
	public boolean isOwnedBy(String viewSocketId) {
		return viewSocketId.contentEquals(p.getSocketId());
	}

}
