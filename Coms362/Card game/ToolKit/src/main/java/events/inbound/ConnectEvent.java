package events.inbound;

import java.util.Map;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.GameController;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.socket.SocketEvent;
import model.Game;
import model.Quorum;

/**
 * An external (inbound) evend. This event is Synthesized by the receiving webSocket
 * when a new connection is detected. 
 * 
 */
public class ConnectEvent implements SysEvent, Event {
	
	public static final String kId = "ConnectEvent";
	
	private Map<String,String> map; 
	private String socket = ""; 
	private int min = 0; 
	private int max = 0;
	private Quorum quorum = null; 
	private PartyRole role = PartyRole.unknown;
	private Integer position = -1;
	
	public static Event createEvent(SocketEvent sktEvent){
		return new ConnectEvent( sktEvent );		
	}
	
	public ConnectEvent(SocketEvent e) {
		this.map = e.map;
		this.socket = "" + e.getSocketId();
		System.out.println("Constructing Connect event for socket "+socket + " = "+this.map.toString());
		quorum = new Quorum(map);
		String pos; 
		if ((pos = map.get("player")) != null) {
			role = PartyRole.player;
			position = Integer.valueOf(pos);
		}
	}

	@Override
	public Move dispatch(RulesDispatch rules, Table table, Player player) {
		return rules.apply(this, table, player);
	}

	@Override
	public void accept(GameController handler, Game game) {
		handler.apply(this, game);		
	}
	
	public String getParam(String key){
		return map.get(key);
	}
	
	public String getSocketId(){
		return socket;
	}
	
	public PartyRole getRole(){
		return role;
	}
	
	public String toString(){
		return String.format("ConnectEvent: %s, %s", socket, map.toString());
	}
	
	public Integer getPosition(){
		return position;
	}

	public Quorum getQuorum() {
		return quorum;
	}
}
