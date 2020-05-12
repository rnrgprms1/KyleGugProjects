package model;

import coms362.cards.abstractcomp.Player;


/**
 * 
 * This class is the descriptive info that SetTimerRemote uses to 
 * execute a javascript setTimeout() command in the browser. 
 * The javascript timer will start as soon as received from the 
 * websocket, and run for 'time' milliseconds. When the timer runs out, 
 * an "evtName" event will be sent back to the application. 
 * 
 * If you have only one timer event of interest, you can use the 
 * pre-defined "timerevent" (see TimerEvent.kId) to connect the timer
 * to the pre-registered class TimerEvent. TimerEvent is already known
 * to the RulesDispatch hierarchy. 
 * 
 * See branch "timer" for an example usage. 
 * 
 * id is a unique "handle" to the timer object in the browser. It is 
 * assigned dynamically (and thus not predictable) 
 * and should generally not be used in code outside
 * of the browser. 
 *  
 * @author Robert Ward
 * @See related classes SetTimerRemote and TimerEvent
 */
public class Timer {
    private String id;
    private String evtName;
    private int time;
    private Player player;

    public Timer(String id, String evtName, Player player, int time) {
        this.id = id;
        this.evtName = evtName;
        this.player = player;
        this.time = time;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvtName() {
        return evtName;
    }

    public void setEvtName(String evtName) {
        this.evtName = evtName;
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
