package events.remote;

import coms362.cards.abstractcomp.Player;

public interface FilterOnOwner {
	public boolean isOwnedBy(String viewSocketId);
}
