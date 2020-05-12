package events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.socket.SocketEvent;

/**
 * Indicates a timer has expired.
 */
public class TimerEvent implements Event, EventFactory {
    public static final String kId = "timerevent";
        
    public static Event createEvent(SocketEvent sktEvent){
        return new TimerEvent(sktEvent.get("id").toString(), ""+sktEvent.getSocketId());        
    }
    
    private String id;
    private String socketId;
    
    public TimerEvent(String timerId, String socketId) {
        this.id = timerId;
        this.socketId = socketId;
    }   
    
    public String getId(){
        return id;
    }

    @Override
    public Move dispatch(RulesDispatch rules, Table table, Player player) {
        Player source = table.lookupPlayer(this.socketId);
        return rules.apply(this, table, source);
    }

}
